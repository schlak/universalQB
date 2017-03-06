package com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.Others;

import com.schlak.Database.DBOrderByStrategy;
import com.schlak.Database.QuerryBuilder.Others.ADBColumn;
import com.schlak.Database.QuerryBuilder.Others.AOrderByColumn;

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
