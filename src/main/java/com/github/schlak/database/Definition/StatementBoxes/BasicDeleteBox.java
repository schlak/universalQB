package com.github.schlak.database.Definition.StatementBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Exeptions.SQLAppendException;
import com.github.schlak.database.ObjectRecycler;

import java.util.Queue;

/**
 * Created by Jonas Schlak on 24.01.17.
 */
public abstract class BasicDeleteBox extends StatementBox {


    protected String tableName;
    protected ConditionStack conditionStack;

    /**
     * Instantiates a new Delete box.
     */
    public BasicDeleteBox() {

    }

    public void init(String tableName, ConditionStack conditionStackList){
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
     * Gets tableName name.
     *
     * @return the tableName name
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
        return BasicDeleteBox.class;
    }

    @Override
    public Queue<String> getParameterQueue() {
        return this.conditionStack.getStatementPreparationBox().queue;
    }

    @Override
    public void clean() {
        if(this.conditionStack != null){
            ObjectRecycler.returnInstance(this.conditionStack);
        }

        this.tableName = "";
        this.conditionStack = null;
    }
}
