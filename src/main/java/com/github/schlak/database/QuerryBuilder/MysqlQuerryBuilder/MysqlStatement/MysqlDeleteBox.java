package com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.MysqlStatement;


import com.schlak.Database.ConditionLinkType;
import com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.Others.MysqlConditionStack;
import com.schlak.Database.QuerryBuilder.Others.AConditionStack;
import com.schlak.Database.QuerryBuilder.PreparedStatementPart;
import com.schlak.Database.QuerryBuilder.SQLAppendException;
import com.schlak.Database.QuerryBuilder.Statement.DeleteBox;
import com.schlak.Database.QuerryBuilder.StatementBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Queue;

/**
 * Created by Jonas Schlak on 24.01.17.
 */
public class MysqlDeleteBox extends DeleteBox {

    public MysqlDeleteBox(String tableName, AConditionStack conditionStackList) {
        super(tableName, conditionStackList);
    }

    @Override
    public PreparedStatement getStatement(Connection connection) throws SQLException {

        String sql = "DELETE " + this.tableName + " WHERE " + this.conditionStack.getConditionString() + ";";

        return connection.prepareStatement(sql);
    }

    @Override
    public void appendStatement(StatementBox statementBox) throws SQLAppendException {

        if (!validateAppend(statementBox)) throw new SQLAppendException();

        MysqlConditionStack conditionStack = new MysqlConditionStack();
        conditionStack.setConditionLinkType(ConditionLinkType.OR);

        conditionStack.addCondition(this.conditionStack).
                addCondition(((DeleteBox) statementBox).getConditionStack());

        this.conditionStack = conditionStack;


    }

    @Override
    public boolean validateAppend(StatementBox statementBox) {
        return super.validateAppend(statementBox);
    }

    @Override
    public boolean validate() {
        return !tableName.equals("");
    }

    @Override
    public String getPreparedStatementString(){
        PreparedStatementPart statementPart = this.conditionStack.getStatementPreparationBox();
        return "DELETE " + this.tableName + " WHERE " + statementPart.string + ";";
    }

    @Override
    public Queue<String> getParameterQueue(){
        return this.conditionStack.getStatementPreparationBox().queue;
    }
}
