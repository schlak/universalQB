package com.github.schlak.database.Definition.Statements;

import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.GeneralOperations.AddWhereClause;
import com.github.schlak.database.Definition.GeneralOperations.SetTable;
import com.github.schlak.database.Definition.IQuery;
import com.github.schlak.database.Definition.StatementBoxes.DeleteBox;
import com.github.schlak.database.Exeptions.QueryBuildException;

/**
 * Created by Jonas Schlak.
 */
public abstract class BasicDeleteBuilder implements SetTable, AddWhereClause, IQuery {

    /**
     * The column definition list is used to store all {@link ColumnDefinition column definitions}. These will be used
     * to create the tableName.
     */
    protected String table = null;

    /**
     * The where condition stack is used to store the conditions for the query.
     * The field will contain {@link ValueAllocation value allocations} and other {@link ConditionStack condition stacks}
     * that should appear in the database statement to control which data sets where deleted.
     */
    protected ConditionStack whereConditionStack;

    /**
     * Instantiates a new {@link BasicDeleteBuilder delete builder}.
     */
    public BasicDeleteBuilder() {

    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Adding conditions to the {@link BasicDeleteBuilder#whereConditionStack where condition stack}.
     * These is the first of two method and excepts {@link ValueAllocation value allocations} that are
     * added to the {@link BasicDeleteBuilder#whereConditionStack where condition stack}.
     *
     * @param valueAllocation is a {@link ValueAllocation value allocation}
     */
    public void where(ValueAllocation valueAllocation) {
        whereConditionStack.addCondition(valueAllocation);
    }

    /**
     * Adding conditions to the {@link BasicDeleteBuilder#whereConditionStack where condition stack}.
     * These is the second of two method and excepts {@link ConditionStack condition stacks} that are
     * added to the {@link BasicDeleteBuilder#whereConditionStack where condition stack}.
     *
     * @param conditionStack is a {@link ConditionStack condition stack}
     */
    public void where(ConditionStack conditionStack) {
        whereConditionStack.addCondition(conditionStack);
    }

    /**
     * The method sets the given value as the tableName name.
     * <p>
     * Set tableName method grants the set access to the {@link BasicDeleteBuilder#table tableName name} variable and
     * offers a generified way setting the tableName name regardless of the database implementation.
     *
     * @param tableName that will be set to the local variable {@link BasicDeleteBuilder#table tableName name}
     */
    public void setTableName(String tableName) {
        this.table = tableName;
    }

    @Override
    public abstract DeleteBox getStatementBox() throws QueryBuildException;
}
