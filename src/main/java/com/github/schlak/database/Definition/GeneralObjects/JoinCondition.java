package com.github.schlak.database.Definition.GeneralObjects;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class JoinCondition {

    /**
     * The base column.
     */
    protected Column baseColumn;

    /**
     * The join column.
     */
    protected Column joinColumn;

    /**
     * Instantiates a new {@link JoinCondition}.
     */
    public JoinCondition() {
    }

    /**
     * Instantiates a new {@link JoinCondition}.
     *
     * @param baseTableColumn the base {@link Column}
     * @param joinTableColumn the join {@link Column}
     */
    public JoinCondition(Column baseTableColumn, Column joinTableColumn) {
        this.baseColumn = baseTableColumn;
        this.joinColumn = joinTableColumn;
    }

    /**
     * Sets base {@link Column}.
     *
     * @param baseTableColumn the base {@link Column}
     * @return the base {@link Column}
     */
    public JoinCondition setBaseTableColumn(Column baseTableColumn) {
        this.baseColumn = baseTableColumn;
        return this;
    }

    /**
     * Sets join {@link Column}.
     *
     * @param joinTableColumn the join {@link Column}
     * @return the join {@link Column}
     */
    public JoinCondition setJoinTableColumn(Column joinTableColumn) {
        this.joinColumn = joinTableColumn;
        return this;
    }

    /**
     * Returns the base table name.
     *
     * @return the base table name
     */
    public String getBaseTableName() {
        return baseColumn.tableName;
    }

    /**
     * Returns the join table name.
     *
     * @return the join table name
     */
    public String getJoinTableName() {
        return this.joinColumn.tableName;
    }

    /**
     * Returns the join condition string.
     *
     * @return the join condition string
     */
    public abstract String getJoinConditionString();
}
