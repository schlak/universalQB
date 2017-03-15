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
    public ValueAllocation setColumn(Column dbColumn) {
        this.column = dbColumn;
        return this;
    }


    public ValueAllocation setColumn(String table, String column) {
        this.column = new MysqlColumn().setTableName(table).setColumnName(column);
        return this;
    }


    @Override
    public Column getColumnInstance() {
        return new MysqlColumn();
    }

    @Override
    public ValueAllocation setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String getAllocationString() {
        return this.column.getColumnString() + "=" + this.value;
    }

    public String toString() {
        return this.column.getColumnString() + "='" + this.value + "'";
    }
}
