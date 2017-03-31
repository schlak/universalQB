package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.Cleanable;
import com.github.schlak.database.Definition.FixedValues.BasicDataType;
import com.github.schlak.database.ObjectRecycler;

/**
 * Created by Jonas Schlak on 26.10.2016.
 */
public abstract class ColumnDefinition implements Cleanable{

    protected Column column;
    protected BasicDataType type;

    public ColumnDefinition() {
        this.clean();
    }

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
    public ColumnDefinition setColumn(Column column) {
        this.column = column;
        return this;
    }

    /**
     * Set the {@link BasicDataType} of the column.
     *
     * @param basicDataType the data type
     * @return the type
     */
    public abstract ColumnDefinition setType(BasicDataType basicDataType);

    @Override
    public void clean() {
        if (column != null)
            ObjectRecycler.returnInstance(column);

        column = null;
        this.setType(BasicDataType.BLOB);
    }
}
