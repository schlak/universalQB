package com.github.schlak.database.Definition.StatementBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Exeptions.SQLAppendException;
import com.github.schlak.database.ObjectRecycler;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Jonas Schlak on 25.01.17.
 */
public abstract class BasicInsertBox extends StatementBox {

    protected String tableName;
    protected List<List<ValueAllocation>> valueAllocationList;

    /**
     * Instantiates a new Insert box.
     */
    public BasicInsertBox() {
        clean();
    }

    public void init(String tableName, List<ValueAllocation> valueAllocationList) {
        this.tableName = tableName;
        this.valueAllocationList.add(valueAllocationList);
    }

    /**
     * Validates whether a statement could be appended to the actual one or not.
     * If the the given statement could be appended the return value will be <b>true</b>
     * else the value will be <b>false</b>.
     *
     * @param statementBox the statement box
     * @return the boolean
     */
    public boolean validateAppend(StatementBox statementBox) {
        return this.getClass() == statementBox.getClass();
    }

    /**
     * Append a statement to the existing.
     *
     * @param statementBox the statement box
     * @throws SQLAppendException the sql append exception
     */
    public abstract void appendStatement(StatementBox statementBox) throws SQLAppendException;

    /**
     * Returns the tableName name.
     *
     * @return the tableName name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Returns the value allocation list.
     *
     * @return the value allocation list
     */
    public List<List<ValueAllocation>> getValueAllocationList() {
        return valueAllocationList;
    }

    @Override
    public Class getType() {
        return BasicInsertBox.class;
    }

    @Override
    public Queue<String> getParameterQueue() {
        Queue<String> parameterQueue = new ArrayDeque<>();

        valueAllocationList.forEach(list -> list.forEach(valueAllocation -> parameterQueue.add(valueAllocation.getValue())));

        return parameterQueue;
    }

    @Override
    public void clean() {

        if (valueAllocationList != null) {
            valueAllocationList.forEach(valueAllocations -> valueAllocations.forEach(ObjectRecycler::returnInstance));
            valueAllocationList.clear();
        }else{
            this.valueAllocationList = new ArrayList<>();
        }

        this.tableName = "";
    }
}
