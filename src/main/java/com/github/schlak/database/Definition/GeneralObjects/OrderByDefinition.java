package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.OrderByStrategy;

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
    protected OrderByStrategy orderByStrategy;

    /**
     * Instantiates a new {@link OrderByDefinition} object.
     */
    public OrderByDefinition() {
        this.orderByStrategy = OrderByStrategy.ASC;
    }

    /**
     * Set the {@link Column}.
     *
     * @param column the {@link Column}
     * @return the {@link OrderByDefinition}
     */
    public abstract OrderByDefinition setColumn(Column column);

    /**
     * Set the {@link OrderByStrategy}.
     *
     * @param orderBYStrategy the {@link OrderByStrategy}
     * @return the {@link OrderByStrategy}
     */
    public abstract OrderByDefinition setOrderByStrategy(OrderByStrategy orderBYStrategy);

    /**
     * Returns the order by string.
     *
     * @return the order by string
     */
    public abstract String getOrderByString();
}
