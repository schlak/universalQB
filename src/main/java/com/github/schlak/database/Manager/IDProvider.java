package com.github.schlak.database.Manager;

import com.github.schlak.database.ConnectionPool;
import com.github.schlak.database.Definition.FixedValues.BasicDataType;
import com.github.schlak.database.Definition.FixedValues.DBOrderByStrategy;
import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Definition.GeneralObjects.OrderByDefinition;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.Statements.BasicCreateBuilder;
import com.github.schlak.database.Definition.Statements.BasicInsertBuilder;
import com.github.schlak.database.Definition.Statements.BasicSelectBuilder;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.Definition.QueryFactory;

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

    private ConnectionPool connectionPool;


    public IDProvider(String connectionPoolIdentifier) {

        this.connectionPool = ConnectionPool.getInstance(connectionPoolIdentifier);

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
        QueryFactory queryBuilder = connectionPool.getQueryFactory();
        BasicCreateBuilder createBuilder = queryBuilder.getCreateBuilder();

        Column tableColumn = queryBuilder.getNewDBColumnInstance();
        tableColumn.setColumnName(TABLE_COLUMN_NAME).setTableName(TABLE_NAME);

        Column instanceColumn = queryBuilder.getNewDBColumnInstance();
        instanceColumn.setColumnName(INSTANCE_COLUMN_NAME).setTableName(TABLE_NAME);

        Column idCountColumn = queryBuilder.getNewDBColumnInstance();
        idCountColumn.setColumnName(ID_COLUMN_NAME).setTableName(TABLE_NAME);

        ColumnDefinition tableColumnDefinition = createBuilder.getNewColumnDefinition();
        tableColumnDefinition.setColumn(tableColumn).setType(BasicDataType.TEXT);

        ColumnDefinition instanceColumnDefinition = createBuilder.getNewColumnDefinition();
        instanceColumnDefinition.setColumn(instanceColumn).setType(BasicDataType.TEXT);

        ColumnDefinition idCountColumnDefinition = createBuilder.getNewColumnDefinition();
        idCountColumnDefinition.setColumn(idCountColumn).setType(BasicDataType.INTEGER);


        createBuilder.setTable(TABLE_NAME);
        createBuilder.addColumnDefinition(tableColumnDefinition);
        createBuilder.addColumnDefinition(instanceColumnDefinition);
        createBuilder.addColumnDefinition(idCountColumnDefinition);

        Connection connection = connectionPool.getConnection();

        try {
            createBuilder.getStatementBox().getPreparedStatement(connection).execute();
        } catch (SQLException | QueryBuildException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    public synchronized int getNewID(String tableName) {
        if (!idHashMap.containsKey(tableName)) reserveIDs(tableName);
        if (idHashMap.get(tableName).size() == 0) reserveIDs(tableName);

        return idHashMap.get(tableName).pop();
    }

    private boolean tableExists() {

        QueryFactory queryBuilder = connectionPool.getQueryFactory();
        BasicSelectBuilder selectBuilder = queryBuilder.getSelectBuilder();

        selectBuilder.setTable(TABLE_NAME);
        selectBuilder.limit(1);

        Connection connection = connectionPool.getConnection();

        try {
            selectBuilder.getStatementBox().getPreparedStatement(connection).execute();
        } catch (SQLException e) {
            return false;
        } catch (QueryBuildException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return true;
    }


    private void reserveIDs(String tableName) {

        int lastReservedID = 0;

        ResultSet resultSet = null;

        if (!idHashMap.containsKey(tableName))
            idHashMap.put(tableName, new Stack<>());

        Connection connection = connectionPool.getConnection();
        QueryFactory queryBuilder = ConnectionPool.getDefaultInstance().getQueryFactory();

        BasicSelectBuilder selectBuilder = queryBuilder.getSelectBuilder();
        BasicInsertBuilder insertBuilder = queryBuilder.getInsertBuilder();

        Column tableColumn = queryBuilder.getNewDBColumnInstance();
        tableColumn.setColumnName(TABLE_COLUMN_NAME).setTableName(TABLE_NAME);

        ValueAllocation tableValueAllocation = queryBuilder.getNewValueAllocationInstance();
        tableValueAllocation.setColumn(tableColumn).setValue(tableName);

        Column idCountColumn = queryBuilder.getNewDBColumnInstance();
        idCountColumn.setColumnName(ID_COLUMN_NAME).setTableName(TABLE_NAME);

        OrderByDefinition tableOrderByColumn = queryBuilder.getNewOrderByColumnInstance();
        tableOrderByColumn.setColumn(idCountColumn).setDBOrderBYStrategy(DBOrderByStrategy.DESC);

        selectBuilder.setTable(TABLE_NAME);
        selectBuilder.column(idCountColumn);
        selectBuilder.where(tableValueAllocation);

        try {
            resultSet = selectBuilder.getStatementBox().getPreparedStatement(connection).executeQuery();
        } catch (SQLException | QueryBuildException e) {
            e.printStackTrace();
        }

        try {
            if (resultSet == null)
                throw new SQLException();
            while (resultSet.next()) {
                if (resultSet.getInt(1) > lastReservedID)
                    lastReservedID = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            lastReservedID = 0;
        }

        Column instanceColumn = queryBuilder.getNewDBColumnInstance();
        instanceColumn.setColumnName(INSTANCE_COLUMN_NAME).setTableName(TABLE_NAME);

        ValueAllocation idValueAllocation = queryBuilder.getNewValueAllocationInstance();
        idValueAllocation.setColumn(idCountColumn).
                setValue(lastReservedID + TO_RESERVE + "");

        ValueAllocation instanceValueAllocation = queryBuilder.getNewValueAllocationInstance();
        instanceValueAllocation.setValue(this.instanceIdentifier).
                setColumn(instanceColumn);

        insertBuilder.setTable(TABLE_NAME);
        insertBuilder.set(instanceValueAllocation);
        insertBuilder.set(idValueAllocation);
        insertBuilder.set(tableValueAllocation);

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
