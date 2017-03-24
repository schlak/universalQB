package com.github.schlak.database.Implementation.MSSQL.GeneralObjects

import com.github.schlak.database.Definition.GeneralObjects.Column

/**
 * Created by Jonas Schlak on 21.03.2017.
 */

class MSSQLColumn : Column() {

    override fun getColumnString(): String {
        return tableName + "." + columnName;
    }

}