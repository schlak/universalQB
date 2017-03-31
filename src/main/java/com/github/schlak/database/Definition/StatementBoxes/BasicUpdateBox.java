package com.github.schlak.database.Definition.StatementBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.ObjectRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 25.01.17.
 */
public abstract class BasicUpdateBox extends StatementBox {

    /**
     * The Table name.
     */
    protected String tableName = null;
    protected List<ValueAllocation> valueAllocationList;
    protected ConditionStack whereConditionStack;

    /**
     * Instantiates a new Update box.
     */

    public BasicUpdateBox() {
    }

    public void init(String tableName, List<ValueAllocation> valueAllocationList,
                     ConditionStack whereConditionStack) {
        this.tableName = tableName;
        this.valueAllocationList = valueAllocationList;
        this.whereConditionStack = whereConditionStack;
    }

    @Override
    public Class getType() {
        return BasicUpdateBox.class;
    }

    @Override
    public void clean() {

        if (this.valueAllocationList != null) {
            this.valueAllocationList.forEach(ObjectRecycler::returnInstance);
            this.valueAllocationList.clear();
        } else {
            this.valueAllocationList = new ArrayList<>();
        }

        if (this.whereConditionStack != null)
            ObjectRecycler.returnInstance(this.whereConditionStack);

        this.whereConditionStack = null;

        this.tableName = "";
    }
}
