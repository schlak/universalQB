package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlValueAllocation extends ValueAllocation {

    public MysqlValueAllocation() {
    }

    public MysqlValueAllocation(String table, String column, String value) {
        this.column = new MysqlColumn().setColumnName(column).setTableName(table);
        this.value = value;
    }

    @Override
    public Column getColumnInstance() {
        return new MysqlColumn();
    }

    @Override
    public String getAllocationString() {
        return this.column.getColumnString() + "=" + this.value;
    }

    @Deprecated
    public String toString() {
        return this.column.getColumnString() + "='" + this.value + "'";
    }
}
