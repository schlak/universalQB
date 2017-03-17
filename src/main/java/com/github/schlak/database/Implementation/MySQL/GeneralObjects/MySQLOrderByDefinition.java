package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.DBOrderByStrategy;
import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.OrderByDefinition;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLOrderByDefinition extends OrderByDefinition {
    @Override
    public OrderByDefinition setColumn(Column iDBColumn) {
        this.column = iDBColumn;
        return this;
    }

    @Override
    public OrderByDefinition setDBOrderBYStrategy(DBOrderByStrategy orderBYStrategy) {
        this.dbOrderByStrategy = orderBYStrategy;
        return this;
    }

    @Override
    public String getOrderByString() {
        return this.column.getColumnString() + " " + this.dbOrderByStrategy.toString();
    }
}
