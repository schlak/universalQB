package com.github.schlak.database.Implementation.MySQL.StatmentBoxes;


import com.github.schlak.database.Definition.FixedValues.ConditionLinkType;
import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.PreparedStatementPart;
import com.github.schlak.database.Definition.StatementBoxes.DeleteBox;
import com.github.schlak.database.Definition.StatementBoxes.StatementBox;
import com.github.schlak.database.Exeptions.SQLAppendException;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MySQLConditionStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Queue;

/**
 * Created by Jonas Schlak on 24.01.17.
 */
public class MysqlDeleteBox extends DeleteBox {

    public MysqlDeleteBox(String tableName, ConditionStack conditionStackList) {
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

        MySQLConditionStack conditionStack = new MySQLConditionStack();
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
