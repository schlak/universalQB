package com.github.schlak.database.QuerryBuilder.MysqlQuerryBuilder.Others;

import com.github.schlak.database.QuerryBuilder.Others.ADBColumn;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlColumn extends ADBColumn {

    public MysqlColumn(String tableName, String columnName) {
        this.setTableName(tableName);
        this.setColumnName(columnName);
    }

    public MysqlColumn() {
    }

    @Override
    public String getColumnName() {
        return this.columnName;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public ADBColumn setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    @Override
    public ADBColumn setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getColumnString() {
        return this.tableName + "." + this.columnName;
    }

}
