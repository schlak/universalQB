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
    public ValueAllocation setValue(String value) {
        this.value = value;
        return this;
    }

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
     * @param column the {@link Column}
     * @return the {@link ValueAllocation}
     */
    public ValueAllocation setColumn(Column column) {
        this.column = column;
        return this;
    }

    public ValueAllocation setColumn(String table, String column) {
        Column columnInstance = this.getColumnInstance();

        columnInstance.setColumnName(column);
        columnInstance.setTableName(table);

        this.column = columnInstance;
        return this;
    }

    /**
     * Returns a new {@link Column} instance.
     *
     * @return the {@link Column} instance
     */
    public abstract Column getColumnInstance();

    /**
     * Get the allocation string.
     *
     * @return the allocation string
     */
    public abstract String getAllocationString();

}
