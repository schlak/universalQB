package com.github.schlak.database.Implementation.MSSQL.GeneralObjects

import com.github.schlak.database.Definition.FixedValues.BasicDataType
import com.github.schlak.database.Definition.GeneralObjects.Column
import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition

/**
 * Created by Jonas Schlak on 21.03.2017.
 */
class MSSQLColumnDefinition : ColumnDefinition() {

    override fun getDefinition(): String {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getColumnInstance(): Column {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setType(basicDataType: BasicDataType?): ColumnDefinition {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}