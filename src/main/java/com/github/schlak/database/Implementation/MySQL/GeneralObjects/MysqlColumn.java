package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.GeneralObjects.Column;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlColumn extends Column {

    public MysqlColumn(String tableName, String columnName) {
        this.setTableName(tableName);
        this.setColumnName(columnName);
    }

    public MysqlColumn() {
    }

    @Override
    public String getColumnString() {
        return this.tableName + "." + this.columnName;
    }

}
