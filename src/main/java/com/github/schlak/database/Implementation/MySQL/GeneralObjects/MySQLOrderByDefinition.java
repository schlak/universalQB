package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.OrderByStrategy;
import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.OrderByDefinition;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLOrderByDefinition extends OrderByDefinition {
    @Override
    public OrderByDefinition setColumn(Column column) {
        this.column = column;
        return this;
    }

    @Override
    public OrderByDefinition setOrderByStrategy(OrderByStrategy orderBYStrategy) {
        this.orderByStrategy = orderBYStrategy;
        return this;
    }

    @Override
    public String getOrderByString() {
        return this.column.getColumnString() + " " + this.orderByStrategy.toString();
    }
}
