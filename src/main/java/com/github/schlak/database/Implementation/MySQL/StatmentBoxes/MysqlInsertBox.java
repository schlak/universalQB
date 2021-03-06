package com.github.schlak.database.Implementation.MySQL.StatmentBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.StatementBoxes.BasicInsertBox;
import com.github.schlak.database.Definition.StatementBoxes.StatementBox;
import com.github.schlak.database.Exeptions.SQLAppendException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by Jonas Schlak on 25.01.17.
 */
public class MysqlInsertBox extends BasicInsertBox {

    public MysqlInsertBox() {
    }

    @Override
    public boolean validate() {
        return !(this.tableName.equals("") || valueAllocationList.size()==0);
    }

    @Override
    public boolean validateAppend(StatementBox statementBox) {
        return super.validateAppend(statementBox) && this.tableName.equals(((BasicInsertBox) statementBox).getTableName());
    }

    @Override
    public Queue<String> getParameterQueue() {

        Queue<String> parameterQueue = new ArrayDeque<>();

        valueAllocationList.forEach(list -> list.forEach(valueAllocation -> parameterQueue.add(valueAllocation.getValue())));

        return parameterQueue;
    }

    @Override
    public String getPreparedStatementString() {
        return "INSERT INTO " + this.tableName + " " +
                getColumnString() + " VALUES " + geValueAllocationString() + ";";
    }

    @Override
    public void appendStatement(StatementBox statementBox) throws SQLAppendException {

        if (!validateAppend(statementBox)) throw new SQLAppendException();

        this.valueAllocationList.addAll(((BasicInsertBox) statementBox).getValueAllocationList());
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(
                "INSERT INTO " + this.tableName + " " +
                        getColumnString() + " VALUES " + getValueString() + ";");
    }

    private String geValueAllocationString() {

        boolean isFirstExternal = true;
        boolean isFirstInternal = true;

        StringBuilder stringBuilder = new StringBuilder();


        for (List<ValueAllocation> list : valueAllocationList) {
            isFirstInternal = true;
            if (!isFirstExternal) stringBuilder.append(",");
            stringBuilder.append("(");
            for (int i = 0; i < list.size(); i++) {

                if (!isFirstInternal) stringBuilder.append(",");
                stringBuilder.append(" ? ");
                isFirstInternal = false;

            }
            stringBuilder.append(")");
            isFirstExternal = false;
        }

        return stringBuilder.toString();
    }

    private String getColumnString() {
        boolean isFirstExternal = true;
        boolean isFirstInternal = true;

        StringBuilder stringBuilder = new StringBuilder();

        for (List<ValueAllocation> list : valueAllocationList) {
            isFirstInternal = true;
            if (!isFirstExternal) stringBuilder.append(",");
            stringBuilder.append("(");
            for (ValueAllocation valueAllocation : list) {

                if (!isFirstInternal) stringBuilder.append(", ");
                stringBuilder.append(valueAllocation.getColumn());
                isFirstInternal = false;

            }
            stringBuilder.append(")");
            isFirstExternal = false;

            break;
        }

        return stringBuilder.toString();
    }

    private String getValueString() {
        boolean isFirstExternal = true;
        boolean isFirstInternal = true;

        StringBuilder stringBuilder = new StringBuilder();


        for (List<ValueAllocation> list : valueAllocationList) {

            if (!isFirstExternal) stringBuilder.append(",");
            stringBuilder.append("(");
            for (ValueAllocation valueAllocation : list) {

                if (!isFirstInternal) stringBuilder.append(", ");
                stringBuilder.append(valueAllocation.getValue());
                isFirstInternal = false;

            }
            stringBuilder.append(")");
            isFirstExternal = false;
        }

        return stringBuilder.toString();
    }

}
