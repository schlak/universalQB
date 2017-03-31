package com.github.schlak.database.Definition.Statements;

import com.github.schlak.database.Definition.Cleanable;
import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.GeneralOperations.AddColumnToShow;
import com.github.schlak.database.Definition.GeneralOperations.AddLimitClause;
import com.github.schlak.database.Definition.GeneralOperations.AddWhereClause;
import com.github.schlak.database.Definition.GeneralOperations.SetTable;
import com.github.schlak.database.Definition.IQuery;
import com.github.schlak.database.Definition.StatementBoxes.BasicSelectBox;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.ObjectRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonas on 15.03.17.
 */
public abstract class BasicSelectBuilder implements SetTable, AddColumnToShow,
        AddWhereClause, AddLimitClause,
        IQuery, Cleanable{

    protected List<Column> columnList;
    protected String table;
    protected ConditionStack whereConditionStack;
    /**
     * The limit of data sets the query will effect.
     * <p>
     * if the limit is set zero the code handel's the value as not set and ignores the limit while
     * generating the statement
     */
    protected int limit;

    public BasicSelectBuilder() {
        this.clean();
    }

    @Override
    public void column(Column column) {
        this.columnList.add(column);
    }

    public void setTableName(String tableName) {
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

    @Override
    public abstract BasicSelectBox getStatementBox() throws QueryBuildException;

    @Override
    public void clean() {

        if (this.columnList != null){
            this.columnList.forEach(ObjectRecycler::returnInstance);
            this.columnList.clear();
        }

        if(this.whereConditionStack != null){
            ObjectRecycler.returnInstance(this.whereConditionStack);
            whereConditionStack = null;
        }

        this.table = "";
        this.limit = 0;
    }

}
