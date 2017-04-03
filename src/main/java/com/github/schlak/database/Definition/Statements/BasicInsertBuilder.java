package com.github.schlak.database.Definition.Statements;

import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.Definition.GeneralOperations.SetTable;
import com.github.schlak.database.Definition.GeneralOperations.SetValue;
import com.github.schlak.database.Definition.IQuery;
import com.github.schlak.database.Definition.StatementBoxes.BasicInsertBox;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.ObjectRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class BasicInsertBuilder implements SetTable, SetValue, IQuery {

    protected String tableName;
    protected List<ValueAllocation> valueAllocationList;

    /**
     * Instantiates a new {@link BasicInsertBuilder insert builder}. To ensure that all variables are ready to use the
     * initialisation of the list, where the necessary {@link ValueAllocation value allocations}
     * are stored, is done here.
     */
    public BasicInsertBuilder() {
        this.valueAllocationList = new ArrayList<>();
    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Adding a {@link ValueAllocation value allocation} to the list. The added values will be inserted in the
     * defined tableName as a new data set. The method is generified to give all classes that inherent {@link BasicInsertBuilder this}
     * class the possibility of adding a {@link ValueAllocation value allocation} with the same method call.
     *
     * @param iValueAllocation the value allocation
     */
    public void set(ValueAllocation iValueAllocation) {
        this.valueAllocationList.add(iValueAllocation);
    }

    /**
     * The method sets the given value as the tableName name.
     * <p>
     * Set tableName method grants the set access to the {@link BasicInsertBuilder#tableName tableName name} variable and
     * offers a generified way setting the tableName name regardless of the database implementation.
     *
     * @param tableName that will be set to the local variable {@link BasicInsertBuilder#tableName tableName name}
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public abstract BasicInsertBox getStatementBox() throws QueryBuildException;

    @Override
    public void clean() {

        if (valueAllocationList != null) {
            valueAllocationList.forEach(ObjectRecycler::returnInstance);
            valueAllocationList.clear();
        }else{
            this.valueAllocationList = new ArrayList<>();
        }

        this.tableName = "";
    }
}
