package com.github.schlak.database.Implementation.MSSQL.StatementBuilder

import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition
import com.github.schlak.database.Definition.StatementBoxes.BasicCreateBox
import com.github.schlak.database.Definition.Statements.BasicCreateBuilder
import com.github.schlak.database.Implementation.MSSQL.GeneralObjects.MSSQLColumnDefinition
import com.github.schlak.database.Implementation.MySQL.StatmentBoxes.MysqlCreateBox
import com.github.schlak.database.ObjectRecycler

/**
 * Created by Jonas Schlak on 21.03.2017.
 */

class MSSQLCreateBuilder : BasicCreateBuilder(){
    override fun getNewColumnDefinition(): ColumnDefinition {
        return MSSQLColumnDefinition()
    }

    override fun getStatementBox(): BasicCreateBox {

        val box = ObjectRecycler.getInstance(MysqlCreateBox::class.java)
        box.init(columnDefinitionList, tableName)

        return box
    }

}