package com.github.schlak.database.Implementation.MSSQL.StatementBuilder

import com.github.schlak.database.Definition.StatementBoxes.BasicDeleteBox
import com.github.schlak.database.Definition.Statements.BasicDeleteBuilder
import com.github.schlak.database.Implementation.MSSQL.GeneralObjects.MSSQLConditionStack
import com.github.schlak.database.Implementation.MSSQL.StatementBoxes.MSSQLDeleteBox
import com.github.schlak.database.ObjectRecycler

/**
 * Created by Jonas Schlak on 22.03.2017.
 */
class MSSQLDeleteBuilder : BasicDeleteBuilder(){
    override fun getStatementBox(): BasicDeleteBox {

        var box: MSSQLDeleteBox = ObjectRecycler.getInstance(MSSQLDeleteBox::class.java)
        box.init(table, whereConditionStack)

        return box
    }

    override fun clean() {
        super.clean()

        this.whereConditionStack = ObjectRecycler.getInstance(MSSQLConditionStack::class.java)
    }
}