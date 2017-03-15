package com.github.schlak.database.Definition.StatementBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Exeptions.SQLAppendException;

/**
 * Created by Jonas Schlak on 24.01.17.
 */
public abstract class DeleteBox extends StatementBox {

    /**
     * The Table name.
     */
    protected String tableName;
    /**
     * The Condition stack.
     */
    protected ConditionStack conditionStack;

    /**
     * Instantiates a new Delete box.
     *
     * @param tableName          the table name
     * @param conditionStackList the condition stack list
     */
    public DeleteBox(String tableName, ConditionStack conditionStackList) {
        this.tableName = tableName;
        this.conditionStack = conditionStackList;
    }

    /**
     * Validates whether a statement could be appended to the actual one or not.
     * If the the given statement could be appended the return value will be <b>true</b>
     * else the value will be <b>false</b>.
     *
     * @param statementBox the statement box
     * @return the boolean
     */
    public boolean validateAppend(StatementBox statementBox) {
        return this.getClass() == statementBox.getClass();
    }

    /**
     * Gets condition stack.
     *
     * @return the condition stack
     */
    public ConditionStack getConditionStack() {
        return conditionStack;
    }

    /**
     * Gets table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Append statement.
     *
     * @param statementBox the statement box
     * @throws SQLAppendException the sql append exception
     */
    public abstract void appendStatement(StatementBox statementBox) throws SQLAppendException;

    @Override
    public Class getType() {
        return DeleteBox.class;
    }
}
