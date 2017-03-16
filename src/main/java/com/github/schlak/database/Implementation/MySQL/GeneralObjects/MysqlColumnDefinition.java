package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.BasicDataType;
import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Implementation.MySQL.FixedValues.MySQLDataType;

/**
 * Created by Jonas Schlak on 26.10.2016.
 */
public class MysqlColumnDefinition extends ColumnDefinition {

    private MySQLDataType type;

    @Override
    public String getDefinition() {
        return this.column.getColumnString() + " " + this.type;
    }

    @Deprecated
    @Override
    public Column getColumnInstance() {
        return null;
    }

    @Override
    public ColumnDefinition setType(BasicDataType basicDataType) {
        this.type = MySQLDataType.get(basicDataType);
        return this;
    }

    public ColumnDefinition setType(MySQLDataType mySQLDataType) {
        this.type = mySQLDataType;
        return this;
    }
}
