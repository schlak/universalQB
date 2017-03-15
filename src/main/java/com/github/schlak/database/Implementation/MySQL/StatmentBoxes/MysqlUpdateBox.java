package com.github.schlak.database.Implementation.MySQL.StatmentBoxes;


import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.StatementBoxes.UpdateBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by Jonas Schlak on 25.01.17.
 */
public class MysqlUpdateBox extends UpdateBox {

    public MysqlUpdateBox(String tableName, List<ValueAllocation> valueAllocationList, ConditionStack whereConditionStack) {
        super(tableName, valueAllocationList, whereConditionStack);
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {

        boolean isFirst = true;
        StringBuilder stringBuilder = new StringBuilder("UPDATE " + this.tableName + " SET ");

        for (ValueAllocation aValueAllocation : valueAllocationList) {

            if (!isFirst) stringBuilder.append(", ");
            stringBuilder.append(aValueAllocation.getAllocationString());
            isFirst = false;
        }

        if (whereConditionStack.getStatementPreparationBox().queue.size() != 0)
            stringBuilder.append(" WHERE ").
                    append(whereConditionStack.getConditionString());

        stringBuilder.append(";");

        return connection.prepareStatement(stringBuilder.toString());
    }

    @Override
    public boolean validate() {
        return !(this.tableName.equals("") || valueAllocationList.size() == 0);
    }

    @Override
    public Queue<String> getParameterQueue() {

        Queue<String> parameterQueue = new ArrayDeque<>();

        valueAllocationList.forEach(aValueAllocation -> parameterQueue.add(aValueAllocation.getValue()));
        whereConditionStack.getStatementPreparationBox().queue.forEach(parameterQueue::add);

        return parameterQueue;
    }

    @Override
    public String getPreparedStatementString() {

        boolean isFirst = true;
        StringBuilder stringBuilder = new StringBuilder("UPDATE " + this.tableName + " SET ");

        for (ValueAllocation aValueAllocation : valueAllocationList) {

            if (!isFirst) stringBuilder.append(", ");
            stringBuilder.append(aValueAllocation.getColumn()).
                    append(" = ? ");
            isFirst = false;
        }

        if (whereConditionStack.getStatementPreparationBox().queue.size() != 0)
            stringBuilder.append(" WHERE ").
                    append(whereConditionStack.getStatementPreparationBox().string);

        stringBuilder.append(";");

        return stringBuilder.toString();
    }
}
