package com.github.schlak.database.Implementation.MySQL.StatmentBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.StatementBoxes.BasicSelectBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Queue;

/**
 * Created by Jonas Schlak on 24.01.17.
 */
public class MysqlSelectBox extends BasicSelectBox {

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {

        String whereCondition = this.whereConditionStack.getConditionString();
        if (!whereCondition.equals("")) whereCondition = "WHERE " + whereCondition;

        String havingCondition = this.havingConditionStack.getConditionString();
        if (!havingCondition.equals("")) whereCondition = "HAVING " + havingCondition;

        return connection.prepareStatement("SELECT " + this.shownColumnList +
                " FROM " + this.tableName + " " +
                this.tableJoinInformationList + " " +
                whereCondition + " " +
                this.groupingColumnList + " " +
                havingCondition + " " +
                this.orderAOderByColumnList + ";");
    }

    @Override
    public boolean validate() {
        return !(this.tableName.equals("") || this.shownColumnList.equals(""));
    }

    @Override
    public Queue<String> getParameterQueue() {
        Queue<String> parameterQueue = this.whereConditionStack.getStatementPreparationBox().queue;
        parameterQueue.addAll(this.havingConditionStack.getStatementPreparationBox().queue);

        return parameterQueue;
    }

    @Override
    public String getPreparedStatementString() {

        String whereCondition = this.whereConditionStack.getStatementPreparationBox().string;
        if (!whereCondition.equals("")) whereCondition = "WHERE " + whereCondition;

        String havingCondition = this.havingConditionStack.getStatementPreparationBox().string;
        if (!havingCondition.equals("")) whereCondition = "HAVING " + havingCondition;


        String sql = "SELECT " + this.shownColumnList +
                " FROM " + this.tableName + " " +
                this.tableJoinInformationList + " " +
                whereCondition + " " +
                this.groupingColumnList + " " +
                havingCondition + " " +
                this.orderAOderByColumnList + ";";
        return sql;
    }
}
