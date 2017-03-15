package com.github.schlak.database.Definition.Statements;

import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.IQuery;

/**
 * Created by Jonas Schlak.
 */
public abstract class BasicDelete implements IQuery {

    /**
     * The column definition list is used to store all {@link ColumnDefinition column definitions}. These will be used
     * to create the table.
     */
    protected String table = null;

    /**
     * The where condition stack is used to store the conditions for the query.
     * The field will contain {@link ValueAllocation value allocations} and other {@link ConditionStack condition stacks}
     * that should appear in the database statement to control which data sets where deleted.
     */
    protected ConditionStack whereConditionStack;

    /**
     * Instantiates a new {@link BasicDelete delete builder}.
     */
    public BasicDelete() {

    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Adding conditions to the {@link BasicDelete#whereConditionStack where condition stack}.
     * These is the first of two method and excepts {@link ValueAllocation value allocations} that are
     * added to the {@link BasicDelete#whereConditionStack where condition stack}.
     *
     * @param valueAllocation is a {@link ValueAllocation value allocation}
     * @return the {@link BasicDelete actual instance} for chaining the method calls
     */
    public void where(ValueAllocation valueAllocation) {
        whereConditionStack.addCondition(valueAllocation);
    }

    /**
     * Adding conditions to the {@link BasicDelete#whereConditionStack where condition stack}.
     * These is the second of two method and excepts {@link ConditionStack condition stacks} that are
     * added to the {@link BasicDelete#whereConditionStack where condition stack}.
     *
     * @param conditionStack is a {@link ConditionStack condition stack}
     * @return the {@link BasicDelete actual instance} for chaining the method calls
     */
    public void where(ConditionStack conditionStack) {
        whereConditionStack.addCondition(conditionStack);
    }

    /**
     * The method sets the given value as the table name.
     * <p>
     * Set table method grants the set access to the {@link BasicDelete#table table name} variable and
     * offers a generified way setting the table name regardless of the database implementation.
     *
     * @param tableName that will be set to the local variable {@link BasicDelete#table table name}
     * @return the {@link BasicDelete actual instance} for chaining the method calls
     */
    public void from(String tableName) {
        this.table = tableName;
    }
}
