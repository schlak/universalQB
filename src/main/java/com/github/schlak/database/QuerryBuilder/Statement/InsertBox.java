package com.github.schlak.database.QuerryBuilder.Statement;

import com.github.schlak.database.QuerryBuilder.Others.AValueAllocation;
import com.github.schlak.database.QuerryBuilder.SQLAppendException;
import com.github.schlak.database.QuerryBuilder.StatementBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 25.01.17.
 */
public abstract class InsertBox extends StatementBox {

    /**
     * The Table name.
     */
    protected String tableName;
    /**
     * The Value allocation list.
     */
    protected List<List<AValueAllocation>> valueAllocationList;

    /**
     * Instantiates a new Insert box.
     *
     * @param tableName           the table name
     * @param valueAllocationList the value allocation list
     */
    public InsertBox(String tableName, List<AValueAllocation> valueAllocationList) {
        this.tableName = tableName;
        this.valueAllocationList = new ArrayList<>();
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
     * Returns the table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Returns the value allocation list.
     *
     * @return the value allocation list
     */
    public List<List<AValueAllocation>> getValueAllocationList() {
        return valueAllocationList;
    }

    @Override
    public Class getType() {
        return InsertBox.class;
    }
}
