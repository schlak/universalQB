package com.github.schlak.database.Implementation.MSSQL.StatementBuilder

import com.github.schlak.database.Definition.StatementBoxes.BasicInsertBox
import com.github.schlak.database.Definition.Statements.BasicInsertBuilder
import com.github.schlak.database.Implementation.MSSQL.StatementBoxes.MSSQLInsertBox

/**
 * Created by Jonas Schlak on 22.03.2017.
 */
class MSSQLInsertBuilder : BasicInsertBuilder(){
    override fun getStatementBox(): BasicInsertBox {
        return MSSQLInsertBox(tableName,valueAllocationList)
    }
}