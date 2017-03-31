package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.Cleanable;
import com.github.schlak.database.Definition.FixedValues.OrderByStrategy;
import com.github.schlak.database.ObjectRecycler;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class OrderByDefinition implements Cleanable{

    protected Column column;
    protected OrderByStrategy orderByStrategy;

    /**
     * Instantiates a new {@link OrderByDefinition} object.
     */
    public OrderByDefinition() {
        this.clean();
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

    @Override
    public void clean() {

        if (this.column != null)
            ObjectRecycler.returnInstance(this.column);

        this.column = null;
        this.setOrderByStrategy(OrderByStrategy.ASC);
    }
}
