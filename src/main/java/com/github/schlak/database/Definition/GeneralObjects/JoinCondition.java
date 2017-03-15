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
    public abstract JoinCondition setBaseTableColumn(Column baseTableColumn);

    /**
     * Sets join {@link Column}.
     *
     * @param joinTableColumn the join {@link Column}
     * @return the join {@link Column}
     */
    public abstract JoinCondition setJoinTableColumn(Column joinTableColumn);

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
