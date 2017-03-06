package com.github.schlak.database.QuerryBuilder.Others;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class AValueAllocation {

    /**
     * The Column.
     */
    protected ADBColumn column;
    /**
     * The Value.
     */
    protected String value;

    /**
     * Instantiates a new {@link AValueAllocation}.
     */
    public AValueAllocation() {
    }

    /**
     * Returns a new {@link ADBColumn} instance.
     *
     * @return the {@link ADBColumn} instance
     */
    public abstract ADBColumn getColumnInstance();

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
    public abstract AValueAllocation setValue(String value);

    /**
     * Get the column.
     *
     * @return the column
     */
    public String getColumn() {
        return this.column.getColumnName();
    }

    /**
     * Set the {@link ADBColumn}.
     *
     * @param dbColumn the {@link ADBColumn}
     * @return the {@link AValueAllocation}
     */
    public abstract AValueAllocation setColumn(ADBColumn dbColumn);

    /**
     * Get the allocation string.
     *
     * @return the allocation string
     */
    public abstract String getAllocationString();

}
