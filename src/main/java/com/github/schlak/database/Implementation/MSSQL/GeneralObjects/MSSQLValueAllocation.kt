package com.github.schlak.database.Implementation.MSSQL.GeneralObjects

import com.github.schlak.database.Definition.GeneralObjects.Column
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation

/**
 * Created by Jonas Schlak on 21.03.2017.
 */
class MSSQLValueAllocation : ValueAllocation(){

    override fun getColumnInstance(): Column {
        return MSSQLColumn()
    }

    override fun getAllocationString(): String {
        return return this.column.columnString + "='" + this.value + "'"
    }
}