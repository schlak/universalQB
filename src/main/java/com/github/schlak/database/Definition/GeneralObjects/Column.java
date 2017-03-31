package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.Cleanable;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class Column implements Cleanable {

    protected String tableName;
    protected String columnName;


    public Column(String tableName, String columnName){
        this.setTableName(tableName);
        this.setColumnName(columnName);
    }

    public Column(){
        this.clean();
    };

    /**
     * Get the column name.
     *
     * @return the column name
     */
    public String getColumnName() {
        return this.columnName;
    }

    /**
     * Set the column name.
     *
     * @param columnName the column name
     * @return the column name
     */
    public Column setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    /**
     * Get the tableName name.
     *
     * @return the tableName name
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Set the tableName name.
     *
     * @param tableName the tableName name
     * @return the tableName name
     */
    public Column setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    /**
     * Get the column string.
     *
     * @return the column string
     */
    public abstract String getColumnString();

    @Override
    public void clean() {
        this.columnName = "";
        this.tableName = "";
    }
}
