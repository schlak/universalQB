package com.github.schlak.database.Implementation.MSSQL.StatementBuilder

import com.github.schlak.database.Definition.StatementBoxes.DeleteBox
import com.github.schlak.database.Definition.Statements.BasicDeleteBuilder
import com.github.schlak.database.Implementation.MSSQL.StatementBoxes.MSSQLDeleteBox

/**
 * Created by Jonas Schlak on 22.03.2017.
 */
class MSSQLDeleteBuilder : BasicDeleteBuilder(){
    override fun getStatementBox(): DeleteBox {
        return MSSQLDeleteBox(table,whereConditionStack)
    }
}