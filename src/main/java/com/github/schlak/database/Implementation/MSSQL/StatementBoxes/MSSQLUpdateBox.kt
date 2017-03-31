package com.github.schlak.database.Implementation.MSSQL.StatementBoxes

import com.github.schlak.database.Definition.StatementBoxes.BasicUpdateBox
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.*

/**
 * Created by Jonas Schlak on 22.03.2017.
 */
class MSSQLUpdateBox : BasicUpdateBox() {

    override fun getStatement(connection: Connection): PreparedStatement {
        var isFirst = true
        val stringBuilder = StringBuilder("UPDATE " + this.tableName + " SET ")

        for (aValueAllocation in valueAllocationList) {

            if (!isFirst) stringBuilder.append(", ")
            stringBuilder.append(aValueAllocation.allocationString)
            isFirst = false
        }

        if (whereConditionStack.statementPreparationBox.queue.size != 0)
            stringBuilder.append(" WHERE ").append(whereConditionStack.conditionString)

        stringBuilder.append(";")

        return connection.prepareStatement(stringBuilder.toString())
    }

    override fun validate(): Boolean {
        return !(this.tableName == "" || valueAllocationList.size == 0)
    }

    override fun getParameterQueue(): Queue<String> {
        val parameterQueue = ArrayDeque<String>()

        valueAllocationList.forEach { aValueAllocation -> parameterQueue.add(aValueAllocation.value) }
        whereConditionStack.statementPreparationBox.queue.forEach { value -> parameterQueue.add(value) }

        return parameterQueue
    }

    override fun getPreparedStatementString(): String {
        var isFirst = true
        val stringBuilder = StringBuilder("UPDATE " + this.tableName + " SET ")

        for (aValueAllocation in valueAllocationList) {

            if (!isFirst) stringBuilder.append(", ")
            stringBuilder.append(aValueAllocation.column).append(" = ? ")
            isFirst = false
        }

        if (whereConditionStack.statementPreparationBox.queue.size != 0)
            stringBuilder.append(" WHERE ").append(whereConditionStack.statementPreparationBox.string)

        stringBuilder.append(";")

        return stringBuilder.toString()}
}