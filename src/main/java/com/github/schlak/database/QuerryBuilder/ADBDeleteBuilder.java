package com.github.schlak.database.QuerryBuilder;

import com.github.schlak.database.QuerryBuilder.Interface.IQuery;
import com.github.schlak.database.QuerryBuilder.Others.AConditionStack;
import com.github.schlak.database.QuerryBuilder.Others.ADBColumnDefinition;
import com.github.schlak.database.QuerryBuilder.Others.AValueAllocation;

/**
 * Created by Jonas Schlak.
 */
public abstract class ADBDeleteBuilder implements IQuery {

    /**
     * The column definition list is used to store all {@link ADBColumnDefinition column definitions}. These will be used
     * to create the table.
     */
    protected String tableName = null;

    /**
     * The where condition stack is used to store the conditions for the query.
     * The field will contain {@link AValueAllocation value allocations} and other {@link AConditionStack condition stacks}
     * that should appear in the database statement to control which data sets where deleted.
     */
    protected AConditionStack whereConditionStack;

    /**
     * Instantiates a new {@link ADBDeleteBuilder delete builder}.
     */
    public ADBDeleteBuilder() {

    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Adding conditions to the {@link ADBDeleteBuilder#whereConditionStack where condition stack}.
     * These is the first of two method and excepts {@link AValueAllocation value allocations} that are
     * added to the {@link ADBDeleteBuilder#whereConditionStack where condition stack}.
     *
     * @param valueAllocation is a {@link AValueAllocation value allocation}
     * @return the {@link ADBDeleteBuilder actual instance} for chaining the method calls
     */
    public ADBDeleteBuilder addWhereCondition(AValueAllocation valueAllocation) {
        whereConditionStack.addCondition(valueAllocation);
        return this;
    }

    /**
     * Adding conditions to the {@link ADBDeleteBuilder#whereConditionStack where condition stack}.
     * These is the second of two method and excepts {@link AConditionStack condition stacks} that are
     * added to the {@link ADBDeleteBuilder#whereConditionStack where condition stack}.
     *
     * @param conditionStack is a {@link AConditionStack condition stack}
     * @return the {@link ADBDeleteBuilder actual instance} for chaining the method calls
     */
    public ADBDeleteBuilder addWhereCondition(AConditionStack conditionStack) {
        whereConditionStack.addCondition(conditionStack);
        return this;
    }

    /**
     * The method sets the given value as the table name.
     * <p>
     * Set table method grants the set access to the {@link ADBDeleteBuilder#tableName table name} variable and
     * offers a generified way setting the table name regardless of the database implementation.
     *
     * @param tableName that will be set to the local variable {@link ADBDeleteBuilder#tableName table name}
     * @return the {@link ADBDeleteBuilder actual instance} for chaining the method calls
     */
    public ADBDeleteBuilder setTable(String tableName) {
        this.tableName = tableName;
        return this;
    }
}
