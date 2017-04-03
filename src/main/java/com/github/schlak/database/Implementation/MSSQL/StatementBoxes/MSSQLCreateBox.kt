package com.github.schlak.database.Implementation.MSSQL.StatementBoxes

import com.github.schlak.database.Definition.StatementBoxes.BasicCreateBox
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.*

/**
* Created by Jonas Schlak.
*/

class MSSQLCreateBox : BasicCreateBox() {


    override fun getStatement(connection: Connection): PreparedStatement {
        return connection.prepareStatement("CREATE TABLE " +
                this.tableName +
                this.getColumnDefinition() +
                ";")
    }

    override fun validate(): Boolean {
        return !(tableName == "" || columnDefinitionList.size == 0)
    }

    override fun getParameterQueue(): Queue<String> {
        return ArrayDeque()
    }

    override fun getPreparedStatementString(): String {
        return "CREATE TABLE " +
                this.tableName +
                this.getColumnDefinition() +
                ";"
    }

    private fun getColumnDefinition(): String {
        val stringBuilder = StringBuilder()
        var isFirst = true

        stringBuilder.append(" (")

        for (idbColumnDefinition in this.columnDefinitionList) {
            if (!isFirst) stringBuilder.append(", ")
            stringBuilder.append(idbColumnDefinition.definition)
            isFirst = false
        }


        stringBuilder.append(")")

        return stringBuilder.toString()
    }
}