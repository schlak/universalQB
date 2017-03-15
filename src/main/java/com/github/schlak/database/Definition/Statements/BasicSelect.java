package com.github.schlak.database.Definition.Statements;

import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.GeneralOperations.AddColumnToShow;
import com.github.schlak.database.Definition.GeneralOperations.AddLimitClause;
import com.github.schlak.database.Definition.GeneralOperations.AddWhereClause;
import com.github.schlak.database.Definition.GeneralOperations.SetTable;
import com.github.schlak.database.Definition.IQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonas on 15.03.17.
 */
public abstract class BasicSelect implements SetTable, AddColumnToShow,
        AddWhereClause, AddLimitClause,
        IQuery {
    /**
     * The list of columns that should be in the result.
     */
    protected List<Column> columnList;
    /**
     * The Table name.
     */
    protected String table;
    /**
     * The where condition stack.
     */
    protected ConditionStack whereConditionStack;
    /**
     * The limit of data sets the query will effect.
     * <p>
     * if the limit is set zero the code handel's the value as not set and ignores the limit while
     * generating the statement
     */
    protected int limit;

    public BasicSelect() {
        this.columnList = new ArrayList<>();

        this.limit = 0;
    }

    @Override
    public void column(Column column) {
        this.columnList.add(column);
    }

    @Override
    public void setTable(String tableName) {
        this.table = tableName;
    }

    @Override
    public void where(ConditionStack conditionStack) {
        whereConditionStack.addCondition(conditionStack);
    }

    @Override
    public void where(ValueAllocation valueAllocation) {
        whereConditionStack.addCondition(valueAllocation);
    }

    @Override
    public void limit(int limit) {
        if (limit < 0)
            this.limit = 0;
        this.limit = limit;
    }
}
