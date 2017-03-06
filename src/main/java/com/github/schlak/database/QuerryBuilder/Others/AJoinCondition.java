package com.schlak.Database.QuerryBuilder.Others;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class AJoinCondition {

    /**
     * The base column.
     */
    protected ADBColumn baseColumn;

    /**
     * The join column.
     */
    protected ADBColumn joinColumn;

    /**
     * Instantiates a new {@link AJoinCondition}.
     */
    public AJoinCondition() {
    }

    /**
     * Instantiates a new {@link AJoinCondition}.
     *
     * @param baseTableColumn the base {@link ADBColumn}
     * @param joinTableColumn the join {@link ADBColumn}
     */
    public AJoinCondition(ADBColumn baseTableColumn, ADBColumn joinTableColumn) {
        this.baseColumn = baseTableColumn;
        this.joinColumn = joinTableColumn;
    }

    /**
     * Sets base {@link ADBColumn}.
     *
     * @param baseTableColumn the base {@link ADBColumn}
     * @return the base {@link ADBColumn}
     */
    public abstract AJoinCondition setBaseTableColumn(ADBColumn baseTableColumn);

    /**
     * Sets join {@link ADBColumn}.
     *
     * @param joinTableColumn the join {@link ADBColumn}
     * @return the join {@link ADBColumn}
     */
    public abstract AJoinCondition setJoinTableColumn(ADBColumn joinTableColumn);

    /**
     * Returns the base table name.
     *
     * @return the base table name
     */
    public abstract String getBaseTableName();

    /**
     * Returns the join table name.
     *
     * @return the join table name
     */
    public abstract String getJoinTableName();

    /**
     * Returns the join condition string.
     *
     * @return the join condition string
     */
    public abstract String getJoinConditionString();
}
