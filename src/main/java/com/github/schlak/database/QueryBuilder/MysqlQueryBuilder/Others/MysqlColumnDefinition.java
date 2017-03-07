package com.github.schlak.database.QueryBuilder.MysqlQueryBuilder.Others;

import com.github.schlak.database.DataType;
import com.github.schlak.database.QueryBuilder.Others.ADBColumn;
import com.github.schlak.database.QueryBuilder.Others.ADBColumnDefinition;

/**
 * Created by Jonas Schlak on 26.10.2016.
 */
public class MysqlColumnDefinition extends ADBColumnDefinition {


    @Override
    public String getDefinition() {
        return this.column.getColumnString() + " " + this.type;
    }

    @Deprecated
    @Override
    public ADBColumn getColumnInstance() {
        return null;
    }

    @Override
    public ADBColumnDefinition setColumn(ADBColumn column) {
        this.column = column;
        return this;
    }

    @Override
    public ADBColumnDefinition setType(DataType dataType) {
        this.type = dataType;
        return this;
    }
}
