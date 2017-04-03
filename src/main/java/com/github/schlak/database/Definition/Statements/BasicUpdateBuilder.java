package com.github.schlak.database.Definition.Statements;

import com.github.schlak.database.Definition.Cleanable;
import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.GeneralOperations.AddWhereClause;
import com.github.schlak.database.Definition.GeneralOperations.SetTable;
import com.github.schlak.database.Definition.GeneralOperations.SetValue;
import com.github.schlak.database.Definition.IQuery;
import com.github.schlak.database.Definition.StatementBoxes.BasicUpdateBox;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.ObjectRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak.
 */
public abstract class BasicUpdateBuilder implements SetTable, SetValue, AddWhereClause, IQuery, Cleanable{

    protected String table = "";
    protected List<ValueAllocation> valueAllocationList;
    protected ConditionStack whereConditionStack;

    /**
     * Instantiates a new Adb update builder.
     */
    public BasicUpdateBuilder() {
    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Add value allocation adb update builder.
     *
     * @param iValueAllocation the value allocation
     */
    public void set(ValueAllocation iValueAllocation) {
        valueAllocationList.add(iValueAllocation);
    }

    /**
     * Add where condition adb update builder.
     *
     * @param iValueCondition the value condition
     */
    public void where(ValueAllocation iValueCondition) {
        whereConditionStack.addCondition(iValueCondition);
    }

    /**
     * Add where condition adb update builder.
     *
     * @param iConditionStack the condition stack
     */
    public void where(ConditionStack iConditionStack) {
        whereConditionStack.addCondition(iConditionStack);
    }

    /**
     * Sets tableName.
     *
     * @param table the tableName
     */
    public void setTableName(String table) {
        this.table = table;
    }

       /*
    The possibility to set a limit of lines for an update is not planed
    at this point.
     */

    @Override
    public abstract BasicUpdateBox getStatementBox() throws QueryBuildException;

    @Override
    public void clean() {
        if(this.whereConditionStack != null){
            ObjectRecycler.returnInstance(this.whereConditionStack);
            whereConditionStack = null;
        }

        if (this.valueAllocationList != null) {
            this.valueAllocationList.forEach(ObjectRecycler::returnInstance);
            this.valueAllocationList.clear();
        } else {
            this.valueAllocationList = new ArrayList<>();
        }

        this.table = "";
    }
}
