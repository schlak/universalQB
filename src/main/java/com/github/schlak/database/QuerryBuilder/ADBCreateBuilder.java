package com.github.schlak.database.QuerryBuilder;

import com.github.schlak.database.QuerryBuilder.Interface.IQuery;
import com.github.schlak.database.QuerryBuilder.Others.ADBColumnDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 26.10.2016.
 */
public abstract class ADBCreateBuilder implements IQuery {

    /**
     * The column definition list is used to store all {@link ADBColumnDefinition column definitions}. These will be used
     * to create the table.
     */
    protected List<ADBColumnDefinition> columnDefinitionList;
    /**
     * The table name field contains the name of the created table. It is used in this class to
     * generify the logic how a table name is set and stored in the create builder.
     */
    protected String tableName;

    /**
     * Instantiates a new {@link ADBCreateBuilder create builder}. To ensure that all variables are ready to use the
     * initialisation of the list, where the necessary {@link ADBColumnDefinition column definitions}
     * are stored, is done here.
     */
    public ADBCreateBuilder() {
        this.columnDefinitionList = new ArrayList<>();
    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Adding a column to the {@link ADBCreateBuilder create builder}.
     * <p>
     * The method is used to add an {@link ADBColumnDefinition column definition} to the {@link ADBCreateBuilder create builder}
     * which represents a column that will be created in the new table. The {@link ADBColumnDefinition column definition}
     * contains the name definition as well as the {@link com.github.schlak.database.DataType data type} the column will
     * store.
     *
     * @param columnDefinition that should be add to the {@link ADBCreateBuilder}
     * @return the {@link ADBCreateBuilder actual instance} for chaining the method calls
     */
    public ADBCreateBuilder addColumnDefinition(ADBColumnDefinition columnDefinition) {
        columnDefinitionList.add(columnDefinition);
        return this;
    }

    /**
     * Getting a new instance of the column definition.
     * <p>
     * These method have to be implemented in the inheriting class to make sure that the right
     * column definition is used. This is necessary because the procedure of how a column is defined
     * is implemented in the column definition class like the {@link com.github.schlak.database.QuerryBuilder.MysqlQuerryBuilder.Others.MysqlColumnDefinition MySQL column definition}
     * and varies from database implementation to database implementation.
     *
     * @return a new instance of the {@link ADBColumnDefinition column definition} class
     */
    public abstract ADBColumnDefinition getNewColumnDefinition();

    /**
     * The method sets the given value as the table name.
     * <p>
     * Set table method grants the set access to the {@link ADBCreateBuilder#tableName table name} variable and
     * offers a generified way setting the table name regardless of the database implementation.
     *
     * @param tableName that will be set to the local variable {@link ADBCreateBuilder#tableName table name}
     * @return the {@link ADBCreateBuilder actual instance} for chaining the method calls
     */
    public ADBCreateBuilder setTable(String tableName) {
        this.tableName = tableName;
        return this;
    }

}
