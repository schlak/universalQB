package com.github.schlak.database.QueryBuilder.Statement;

import com.github.schlak.database.QueryBuilder.Others.AConditionStack;
import com.github.schlak.database.QueryBuilder.Others.AValueAllocation;
import com.github.schlak.database.QueryBuilder.StatementBox;

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
    protected List<AValueAllocation> valueAllocationList;
    /**
     * The Where condition stack.
     */
    protected AConditionStack whereConditionStack;

    /**
     * Instantiates a new Update box.
     *
     * @param tableName           the table name
     * @param valueAllocationList the value allocation list
     * @param whereConditionStack the where condition stack
     */
    public UpdateBox(String tableName, List<AValueAllocation> valueAllocationList,
                     AConditionStack whereConditionStack) {
        this.tableName = tableName;
        this.valueAllocationList = valueAllocationList;
        this.whereConditionStack = whereConditionStack;
    }

    @Override
    public Class getType() {
        return UpdateBox.class;
    }
}
