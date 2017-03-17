package com.github.schlak.database.Definition.Statements;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.GeneralOperations.AddWhereClause;
import com.github.schlak.database.Definition.GeneralOperations.SetTable;
import com.github.schlak.database.Definition.GeneralOperations.SetValue;
import com.github.schlak.database.Definition.IQuery;
import com.github.schlak.database.Definition.StatementBoxes.UpdateBox;
import com.github.schlak.database.Exeptions.QueryBuildException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class BasicUpdateBuilder implements SetTable, SetValue, AddWhereClause, IQuery {

    /**
     * The Table name.
     */
    protected String table = null;

    /**
     * The Value allocation list.
     */
    protected List<ValueAllocation> valueAllocationList;
    /**
     * The Where condition stack.
     */
    protected ConditionStack whereConditionStack;

    /**
     * Instantiates a new Adb update builder.
     */
    public BasicUpdateBuilder() {
        this.valueAllocationList = new ArrayList<>();
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
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(String table) {
        this.table = table;
    }

       /*
    The possibility to set a limit of lines for an update is not planed
    at this point.
     */

    @Override
    public abstract UpdateBox getStatementBox() throws QueryBuildException;
}
