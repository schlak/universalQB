package com.github.schlak.database.QuerryBuilder.MysqlQuerryBuilder.Others;

import com.github.schlak.database.DBOrderByStrategy;
import com.github.schlak.database.QuerryBuilder.Others.ADBColumn;
import com.github.schlak.database.QuerryBuilder.Others.AOrderByColumn;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlOrderByColumn extends AOrderByColumn {
    @Override
    public AOrderByColumn setColumn(ADBColumn iDBColumn) {
        this.column = iDBColumn;
        return this;
    }

    @Override
    public AOrderByColumn setDBOrderBYStrategy(DBOrderByStrategy orderBYStrategy) {
        this.dbOrderByStrategy = orderBYStrategy;
        return this;
    }

    @Override
    public String getOrderByString() {
        return this.column.getColumnString() + " " + this.dbOrderByStrategy.toString();
    }
}
