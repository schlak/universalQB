package com.github.schlak.database.Definition.Statements;

import com.github.schlak.database.Definition.FixedValues.BasicDataType;
import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Definition.GeneralOperations.AddColumnDefinitionClause;
import com.github.schlak.database.Definition.GeneralOperations.SetTable;
import com.github.schlak.database.Definition.IQuery;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MysqlColumnDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 26.10.2016.
 */
public abstract class BasicCreate implements SetTable, AddColumnDefinitionClause, IQuery {

    /**
     * The column definition list is used to store all {@link ColumnDefinition column definitions}. These will be used
     * to create the table.
     */
    protected List<ColumnDefinition> columnDefinitionList;
    /**
     * The table name field contains the name of the created table. It is used in this class to
     * generify the logic how a table name is set and stored in the create builder.
     */
    protected String table;

    /**
     * Instantiates a new {@link BasicCreate create builder}. To ensure that all variables are ready to use the
     * initialisation of the list, where the necessary {@link ColumnDefinition column definitions}
     * are stored, is done here.
     */
    public BasicCreate() {
        this.columnDefinitionList = new ArrayList<>();
    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Adding a column to the {@link BasicCreate create builder}.
     * <p>
     * The method is used to add an {@link ColumnDefinition column definition} to the {@link BasicCreate create builder}
     * which represents a column that will be created in the new table. The {@link ColumnDefinition column definition}
     * contains the name definition as well as the {@link BasicDataType data type} the column will
     * store.
     *
     * @param columnDefinition that should be add to the {@link BasicCreate}
     * @return the {@link BasicCreate actual instance} for chaining the method calls
     */
    public void addColumnDefinition(ColumnDefinition columnDefinition) {
        columnDefinitionList.add(columnDefinition);
    }

    /**
     * Getting a new instance of the column definition.
     * <p>
     * These method have to be implemented in the inheriting class to make sure that the right
     * column definition is used. This is necessary because the procedure of how a column is defined
     * is implemented in the column definition class like the {@link MysqlColumnDefinition MySQL column definition}
     * and varies setTable database implementation to database implementation.
     *
     * @return a new instance of the {@link ColumnDefinition column definition} class
     */
    public abstract ColumnDefinition getNewColumnDefinition();

    /**
     * The method sets the given value as the table name.
     * <p>
     * Set table method grants the set access to the {@link BasicCreate#table table name} variable and
     * offers a generified way setting the table name regardless of the database implementation.
     *
     * @param tableName that will be set to the local variable {@link BasicCreate#table table name}
     * @return the {@link BasicCreate actual instance} for chaining the method calls
     */
    public void setTable(String tableName) {
        this.table = tableName;
    }

}
