package com.schlak.Database.QuerryBuilder;

import com.schlak.Database.QuerryBuilder.Interface.IQuery;
import com.schlak.Database.QuerryBuilder.Others.AConditionStack;
import com.schlak.Database.QuerryBuilder.Others.AValueAllocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class ADBUpdateBuilder implements IQuery {

    /**
     * The Table name.
     */
    protected String tableName = null;

    /**
     * The Value allocation list.
     */
    protected List<AValueAllocation> valueAllocationList;
    /**
     * The Where condition stack.
     */
    protected AConditionStack whereConditionStack;

    /**
     * Instantiates a new Adb update builder.
     */
    public ADBUpdateBuilder() {
        this.valueAllocationList = new ArrayList<>();
    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Add value allocation adb update builder.
     *
     * @param iValueAllocation the value allocation
     * @return the adb update builder
     */
    public ADBUpdateBuilder addValueAllocation(AValueAllocation iValueAllocation) {
        valueAllocationList.add(iValueAllocation);
        return this;
    }

    /**
     * Add where condition adb update builder.
     *
     * @param iValueCondition the value condition
     * @return the adb update builder
     */
    public ADBUpdateBuilder addWhereCondition(AValueAllocation iValueCondition) {
        whereConditionStack.addCondition(iValueCondition);
        return this;
    }

    /**
     * Add where condition adb update builder.
     *
     * @param iConditionStack the condition stack
     * @return the adb update builder
     */
    public ADBUpdateBuilder addWhereCondition(AConditionStack iConditionStack) {
        whereConditionStack.addCondition(iConditionStack);
        return this;
    }

    /**
     * Sets table.
     *
     * @param table the table
     * @return the table
     */
    public ADBUpdateBuilder setTable(String table) {
        this.tableName = table;
        return this;
    }

       /*
    The possibility to set a limit of lines for an update is not planed
    at this point.
     */
}
