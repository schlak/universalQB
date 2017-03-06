package com.schlak.Database.QuerryBuilder.Others;

import com.schlak.Database.DataType;

/**
 * Created by Jonas Schlak on 26.10.2016.
 */
public abstract class ADBColumnDefinition {

    /**
     * The Column.
     */
    protected ADBColumn column;
    /**
     * The Type.
     */
    protected DataType type;


    /**
     * Get the definition.
     *
     * @return the definition
     */
    public abstract String getDefinition();

    /**
     * Get a new column instance.
     *
     * @return the column instance
     */
    @Deprecated
    public abstract ADBColumn getColumnInstance();

    /**
     * Set the column.
     *
     * @param column the column
     * @return the column
     */
    public abstract ADBColumnDefinition setColumn(ADBColumn column);

    /**
     * Set the {@link DataType} of the column.
     *
     * @param dataType the data type
     * @return the type
     */
    public abstract ADBColumnDefinition setType(DataType dataType);
}
