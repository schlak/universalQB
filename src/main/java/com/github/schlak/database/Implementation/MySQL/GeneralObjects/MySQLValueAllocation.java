package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;
import com.github.schlak.database.ObjectRecycler;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLValueAllocation extends ValueAllocation {

    public MySQLValueAllocation() {
    }

    @Override
    public Column getColumnInstance() {
        return ObjectRecycler.getInstance(MySQLColumn.class);
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
