package com.schlak.Database.QuerryBuilder.Manager;

import com.schlak.Database.DBConnectionPool;
import com.schlak.Database.DBOrderByStrategy;
import com.schlak.Database.DataType;
import com.schlak.Database.QuerryBuilder.ADBCreateBuilder;
import com.schlak.Database.QuerryBuilder.ADBInsertBuilder;
import com.schlak.Database.QuerryBuilder.ADBSelectBuilder;
import com.schlak.Database.QuerryBuilder.Interface.IDBQueryBuilder;
import com.schlak.Database.QuerryBuilder.Interface.IGetConnection;
import com.schlak.Database.QuerryBuilder.Others.ADBColumn;
import com.schlak.Database.QuerryBuilder.Others.ADBColumnDefinition;
import com.schlak.Database.QuerryBuilder.Others.AOrderByColumn;
import com.schlak.Database.QuerryBuilder.Others.AValueAllocation;
import com.schlak.Database.QuerryBuilder.QueryBuildException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Jonas Schlak on 27.01.17.
 */
public class IDProvider {

    private static String TABLE_NAME = "idReservationTable";
    private static String TABLE_COLUMN_NAME = "targetTable";
    private static String ID_COLUMN_NAME = "idCount";
    private static String INSTANCE_COLUMN_NAME = "instance";

    private static int TO_RESERVE = 20;
    private String instanceIdentifier;
    private HashMap<String, Stack<Integer>> idHashMap = new HashMap<>();
    private IGetConnection connector;

    public IDProvider(IGetConnection connector) {
        this.connector = connector;

        String hostname;
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        } catch (UnknownHostException ex) {
            hostname = ((Double) Math.random()).hashCode() + "";
        }

        instanceIdentifier = hostname;

        if (!tableExists()) {
            createTable();
        }

    }

    private void createTable() {
        IDBQueryBuilder queryBuilder = DBConnectionPool.getDefaultInstance().getQueryBuilder();
        ADBCreateBuilder createBuilder = queryBuilder.getCreateBuilder();

        ADBColumn tableColumn = queryBuilder.getNewDBColumnInstance();
        tableColumn.setColumnName(TABLE_COLUMN_NAME).setTableName(TABLE_NAME);

        ADBColumn instanceColumn = queryBuilder.getNewDBColumnInstance();
        instanceColumn.setColumnName(INSTANCE_COLUMN_NAME).setTableName(TABLE_NAME);

        ADBColumn idCountColumn = queryBuilder.getNewDBColumnInstance();
        idCountColumn.setColumnName(ID_COLUMN_NAME).setTableName(TABLE_NAME);

        ADBColumnDefinition tableColumnDefinition = createBuilder.getNewColumnDefinition();
        tableColumnDefinition.setColumn(tableColumn).setType(DataType.TEXT);

        ADBColumnDefinition instanceColumnDefinition = createBuilder.getNewColumnDefinition();
        instanceColumnDefinition.setColumn(instanceColumn).setType(DataType.TEXT);

        ADBColumnDefinition idCountColumnDefinition = createBuilder.getNewColumnDefinition();
        idCountColumnDefinition.setColumn(idCountColumn).setType(DataType.INTEGER);


        createBuilder.setTable(TABLE_NAME).
                addColumnDefinition(tableColumnDefinition).
                addColumnDefinition(instanceColumnDefinition).
                addColumnDefinition(idCountColumnDefinition);

        try {
            createBuilder.getStatementBox().getPreparedStatement(connector.getConnection()).execute();
        } catch (SQLException | QueryBuildException e) {
            e.printStackTrace();
        }
    }

    public synchronized int getNewID(String tableName) {
        if (!idHashMap.containsKey(tableName)) reserveIDs(tableName);
        if (idHashMap.get(tableName).size() == 0) reserveIDs(tableName);

        return idHashMap.get(tableName).pop();
    }

    private boolean tableExists() {

        IDBQueryBuilder queryBuilder = DBConnectionPool.getDefaultInstance().getQueryBuilder();
        ADBSelectBuilder selectBuilder = queryBuilder.getSelectBuilder();

        selectBuilder.setTable(TABLE_NAME);
        selectBuilder.setLimit(1);

        try {
            selectBuilder.getStatementBox().getPreparedStatement(connector.getConnection()).execute();
        } catch (SQLException e) {
            return false;
        } catch (QueryBuildException e) {
            e.printStackTrace();
        }
        return true;
    }


    private void reserveIDs(String tableName) {

        int lastReservedID;

        ResultSet resultSet = null;

        if (!idHashMap.containsKey(tableName))
            idHashMap.put(tableName, new Stack<>());

        Connection connection = connector.getConnection();
        IDBQueryBuilder queryBuilder = DBConnectionPool.getDefaultInstance().getQueryBuilder();

        ADBSelectBuilder selectBuilder = queryBuilder.getSelectBuilder();
        ADBInsertBuilder insertBuilder = queryBuilder.getInsertBuilder();

        ADBColumn tableColumn = queryBuilder.getNewDBColumnInstance();
        tableColumn.setColumnName(TABLE_COLUMN_NAME).setTableName(TABLE_NAME);

        AValueAllocation tableValueAllocation = queryBuilder.getNewValueAllocationInstance();
        tableValueAllocation.setColumn(tableColumn).setValue(tableName);

        ADBColumn idCountColumn = queryBuilder.getNewDBColumnInstance();
        idCountColumn.setColumnName(ID_COLUMN_NAME).setTableName(TABLE_NAME);

        AOrderByColumn tableOrderByColumn = queryBuilder.getNewOrderByColumnInstance();
        tableOrderByColumn.setColumn(idCountColumn).setDBOrderBYStrategy(DBOrderByStrategy.DESC);

        selectBuilder.setTable(TABLE_NAME).
                addShownColumns(idCountColumn).
                addWhereCondition(tableValueAllocation).
                setLimit(1).
                addOrderByColumn(tableOrderByColumn);

        try {
            resultSet = selectBuilder.getStatementBox().getPreparedStatement(connection).executeQuery();
        } catch (SQLException | QueryBuildException e) {
            e.printStackTrace();
        }

        try {
            if (resultSet == null)
                throw new SQLException();
            resultSet.next();
            lastReservedID = resultSet.getInt(1);
        } catch (SQLException e) {
            lastReservedID = 0;
//            e.printStackTrace();
        }

        ADBColumn instanceColumn = queryBuilder.getNewDBColumnInstance();
        instanceColumn.setColumnName(INSTANCE_COLUMN_NAME).setTableName(TABLE_NAME);

        AValueAllocation idValueAllocation = queryBuilder.getNewValueAllocationInstance();
        idValueAllocation.setColumn(idCountColumn).
                setValue(lastReservedID + TO_RESERVE + "");

        AValueAllocation instanceValueAllocation = queryBuilder.getNewValueAllocationInstance();
        instanceValueAllocation.setValue(this.instanceIdentifier).
                setColumn(instanceColumn);

        insertBuilder.
                setTable(TABLE_NAME).
                addValueAllocation(instanceValueAllocation).
                addValueAllocation(idValueAllocation).
                addValueAllocation(tableValueAllocation);

        try {
            insertBuilder.getStatementBox().getPreparedStatement(connection).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stack<Integer> integerStack = idHashMap.get(tableName);

        for (int i = (lastReservedID + 1); i < (lastReservedID + TO_RESERVE + 1); i++) {
            integerStack.push(i);
        }

    }

}
