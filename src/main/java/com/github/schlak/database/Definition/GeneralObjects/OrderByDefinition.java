package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.DBOrderByStrategy;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class OrderByDefinition {

    /**
     * The Column.
     */
    protected Column column;
    /**
     * The Db order by strategy.
     */
    protected DBOrderByStrategy dbOrderByStrategy;

    /**
     * Instantiates a new {@link OrderByDefinition} object.
     */
    public OrderByDefinition() {
        this.dbOrderByStrategy = DBOrderByStrategy.ASC;
    }

    /**
     * Set the {@link Column}.
     *
     * @param iDBColumn the {@link Column}
     * @return the {@link OrderByDefinition}
     */
    public abstract OrderByDefinition setColumn(Column iDBColumn);

    /**
     * Set the {@link DBOrderByStrategy}.
     *
     * @param orderBYStrategy the {@link DBOrderByStrategy}
     * @return the {@link DBOrderByStrategy}
     */
    public abstract OrderByDefinition setDBOrderBYStrategy(DBOrderByStrategy orderBYStrategy);

    /**
     * Returns the order by string.
     *
     * @return the order by string
     */
    public abstract String getOrderByString();
}
