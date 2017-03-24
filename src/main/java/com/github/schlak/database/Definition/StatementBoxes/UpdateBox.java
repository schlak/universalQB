package com.github.schlak.database.Definition.StatementBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;

import java.util.List;

/**
 * Created by Jonas Schlak on 25.01.17.
 */
public abstract class UpdateBox extends StatementBox {

    /**
     * The Table name.
     */
    protected String tableName = null;

    /**
     * The Value allocation list.
     */
    protected List<ValueAllocation> valueAllocationList;
    /**
     * The Where condition stack.
     */
    protected ConditionStack whereConditionStack;

    /**
     * Instantiates a new Update box.
     *
     * @param tableName           the tableName name
     * @param valueAllocationList the value allocation list
     * @param whereConditionStack the where condition stack
     */
    public UpdateBox(String tableName, List<ValueAllocation> valueAllocationList,
                     ConditionStack whereConditionStack) {
        this.tableName = tableName;
        this.valueAllocationList = valueAllocationList;
        this.whereConditionStack = whereConditionStack;
    }

    @Override
    public Class getType() {
        return UpdateBox.class;
    }
}
