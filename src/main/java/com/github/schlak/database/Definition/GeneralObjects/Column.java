package com.github.schlak.database.Definition.GeneralObjects;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class Column {

    /**
     * The Table name.
     */
    protected String tableName;
    /**
     * The Column name.
     */
    protected String columnName;

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
     * Get the table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Set the table name.
     *
     * @param tableName the table name
     * @return the table name
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
}
