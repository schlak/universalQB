package com.github.schlak.database.Implementation.MSSQL.StatementBoxes

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack
import com.github.schlak.database.Definition.StatementBoxes.SelectBox
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.*

/**
 * Created by Jonas Schlak on 22.03.2017.
 */
class MSSQLSelectBox(tableName: String, shownColumnList: String,
                     orderAOderByColumnList: String, groupingColumnList: String,
                     tableJoinInformationList: String,
                     whereConditionStack: ConditionStack,
                     havingConditionStack: ConditionStack) :
        SelectBox(tableName, shownColumnList, orderAOderByColumnList, groupingColumnList,
                tableJoinInformationList, whereConditionStack, havingConditionStack) {


    override fun getStatement(connection: Connection?): PreparedStatement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getParameterQueue(): Queue<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPreparedStatementString(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}