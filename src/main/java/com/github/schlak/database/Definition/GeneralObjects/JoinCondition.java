package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.Cleanable;
import com.github.schlak.database.ObjectRecycler;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class JoinCondition implements Cleanable {


    protected Column baseColumn;
    protected Column joinColumn;

    /**
     * Instantiates a new {@link JoinCondition}.
     */
    public JoinCondition() {
        this.clean();
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
     * Returns the base tableName name.
     *
     * @return the base tableName name
     */
    public String getBaseTableName() {
        return baseColumn.tableName;
    }

    /**
     * Returns the join tableName name.
     *
     * @return the join tableName name
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

    @Override
    public void clean() {

        if (this.baseColumn != null)
            ObjectRecycler.returnInstance(this.baseColumn);
        if (this.joinColumn != null)
            ObjectRecycler.returnInstance(this.joinColumn);


        this.baseColumn = null;
        this.joinColumn = null;
    }
}
