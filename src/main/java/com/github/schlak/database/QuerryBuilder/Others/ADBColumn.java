package com.github.schlak.database.QuerryBuilder.Others;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class ADBColumn {

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
    public abstract String getColumnName();

    /**
     * Set the column name.
     *
     * @param columnName the column name
     * @return the column name
     */
    public abstract ADBColumn setColumnName(String columnName);

    /**
     * Get the table name.
     *
     * @return the table name
     */
    public abstract String getTableName();

    /**
     * Set the table name.
     *
     * @param tableName the table name
     * @return the table name
     */
    public abstract ADBColumn setTableName(String tableName);

    /**
     * Get the column string.
     *
     * @return the column string
     */
    public abstract String getColumnString();

    @Override
    public String toString() {
        return super.toString();
    }
}
