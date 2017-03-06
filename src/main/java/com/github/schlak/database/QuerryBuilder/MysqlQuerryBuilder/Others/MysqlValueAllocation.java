package com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.Others;

import com.schlak.Database.QuerryBuilder.Others.ADBColumn;
import com.schlak.Database.QuerryBuilder.Others.AValueAllocation;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlValueAllocation extends AValueAllocation {

    public MysqlValueAllocation() {
    }

    public MysqlValueAllocation(String table, String column, String value) {
        this.column = new MysqlColumn().setColumnName(column).setTableName(table);
        this.value = value;
    }

    @Override
    public AValueAllocation setColumn(ADBColumn dbColumn) {
        this.column = dbColumn;
        return this;
    }


    public AValueAllocation setColumn(String table, String column) {
        this.column = new MysqlColumn().setTableName(table).setColumnName(column);
        return this;
    }


    @Override
    public ADBColumn getColumnInstance() {
        return new MysqlColumn();
    }

    @Override
    public AValueAllocation setValue(String value) {
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
