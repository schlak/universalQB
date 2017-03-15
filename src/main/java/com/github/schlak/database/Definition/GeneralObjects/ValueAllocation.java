package com.github.schlak.database.Definition.GeneralObjects;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class ValueAllocation {

    /**
     * The Column.
     */
    protected Column column;
    /**
     * The Value.
     */
    protected String value;

    /**
     * Instantiates a new {@link ValueAllocation}.
     */
    public ValueAllocation() {
    }

    /**
     * Returns a new {@link Column} instance.
     *
     * @return the {@link Column} instance
     */
    public abstract Column getColumnInstance();

    /**
     * Returns the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value.
     *
     * @param value the value
     * @return the value
     */
    public abstract ValueAllocation setValue(String value);

    /**
     * Get the column.
     *
     * @return the column
     */
    public String getColumn() {
        return this.column.getColumnName();
    }

    /**
     * Set the {@link Column}.
     *
     * @param dbColumn the {@link Column}
     * @return the {@link ValueAllocation}
     */
    public abstract ValueAllocation setColumn(Column dbColumn);

    /**
     * Get the allocation string.
     *
     * @return the allocation string
     */
    public abstract String getAllocationString();

}
