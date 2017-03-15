package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.BasicDataType;

/**
 * Created by Jonas Schlak on 26.10.2016.
 */
public abstract class ColumnDefinition {

    /**
     * The Column.
     */
    protected Column column;
    /**
     * The Type.
     */
    protected BasicDataType type;


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
    public abstract Column getColumnInstance();

    /**
     * Set the column.
     *
     * @param column the column
     * @return the column
     */
    public abstract ColumnDefinition setColumn(Column column);

    /**
     * Set the {@link BasicDataType} of the column.
     *
     * @param basicDataType the data type
     * @return the type
     */
    public abstract ColumnDefinition setType(BasicDataType basicDataType);
}
