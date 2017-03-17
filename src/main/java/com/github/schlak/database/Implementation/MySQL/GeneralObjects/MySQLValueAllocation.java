package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLValueAllocation extends ValueAllocation {

    public MySQLValueAllocation() {
    }

    public MySQLValueAllocation(String table, String column, String value) {
        this.column = new MySQLColumn().setColumnName(column).setTableName(table);
        this.value = value;
    }

    @Override
    public Column getColumnInstance() {
        return new MySQLColumn();
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
