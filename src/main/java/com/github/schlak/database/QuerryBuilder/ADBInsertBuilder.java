package com.github.schlak.database.QuerryBuilder;

import com.github.schlak.database.QuerryBuilder.Interface.IQuery;
import com.github.schlak.database.QuerryBuilder.Others.ADBColumnDefinition;
import com.github.schlak.database.QuerryBuilder.Others.AValueAllocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class ADBInsertBuilder implements IQuery {

    /**
     * The table name field contains the name of the created table. It is used in this class to
     * generify the logic how a table name is set and stored in the create builder.
     */
    protected String tableName;

    /**
     * The value allocation list is used to store all {@link AValueAllocation value allocations}. These will be
     * inserted as a neu data set into the defined table.
     */
    protected List<AValueAllocation> valueAllocationList;

    /**
     * Instantiates a new {@link ADBInsertBuilder insert builder}. To ensure that all variables are ready to use the
     * initialisation of the list, where the necessary {@link AValueAllocation value allocations}
     * are stored, is done here.
     */
    public ADBInsertBuilder() {
        this.valueAllocationList = new ArrayList<>();
    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Adding a {@link AValueAllocation value allocation} to the list. The added values will be inserted in the
     * defined table as a new data set. The method is generified to give all classes that inherent {@link ADBInsertBuilder this}
     * class the possibility of adding a {@link AValueAllocation value allocation} with the same method call.
     *
     * @param iValueAllocation the value allocation
     * @return the {@link ADBInsertBuilder actual instance} for chaining the method calls
     */
    public ADBInsertBuilder addValueAllocation(AValueAllocation iValueAllocation) {
        this.valueAllocationList.add(iValueAllocation);
        return this;
    }

    /**
     * The method sets the given value as the table name.
     * <p>
     * Set table method grants the set access to the {@link ADBInsertBuilder#tableName table name} variable and
     * offers a generified way setting the table name regardless of the database implementation.
     *
     * @param tableName that will be set to the local variable {@link ADBInsertBuilder#tableName table name}
     * @return the {@link ADBInsertBuilder actual instance} for chaining the method calls
     */
    public ADBInsertBuilder setTable(String tableName) {
        this.tableName = tableName;
        return this;
    }

}
