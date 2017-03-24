package com.github.schlak.database.Implementation.MSSQL.StatementBoxes

import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation
import com.github.schlak.database.Definition.StatementBoxes.InsertBox
import com.github.schlak.database.Definition.StatementBoxes.StatementBox
import com.github.schlak.database.Exeptions.SQLAppendException
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.*

/**
 * Created by Jonas Schlak on 22.03.2017.
 */
class MSSQLInsertBox(tableName : String, valueAllocationList : List<ValueAllocation>) : InsertBox(tableName,valueAllocationList) {
    override fun getStatement(connection: Connection): PreparedStatement {
        return connection.prepareStatement(
                "INSERT INTO " + this.tableName + " " +
                        getColumnString() + " VALUES " + getValueString() + ";")
    }

    override fun validate(): Boolean {
        return !(this.tableName == "" || valueAllocationList.size == 0)
    }

    override fun appendStatement(statementBox: StatementBox?) {
        if (!validateAppend(statementBox)) throw SQLAppendException()

        this.valueAllocationList.addAll((statementBox as InsertBox).valueAllocationList)
    }

    override fun getPreparedStatementString(): String {
        return "INSERT INTO " + this.tableName + " " +
                getColumnString() + " VALUES " + geValueAllocationString() + ";"
    }

    override fun validateAppend(statementBox: StatementBox?): Boolean {
        return super.validateAppend(statementBox) && this.tableName == (statementBox as InsertBox).tableName
    }

    private fun geValueAllocationString(): String {

        var isFirstExternal = true
        var isFirstInternal = true

        val stringBuilder = StringBuilder()


        for (list in valueAllocationList) {
            isFirstInternal = true
            if (!isFirstExternal) stringBuilder.append(",")
            stringBuilder.append("(")
            for (i in list.indices) {

                if (!isFirstInternal) stringBuilder.append(",")
                stringBuilder.append(" ? ")
                isFirstInternal = false

            }
            stringBuilder.append(")")
            isFirstExternal = false
        }

        return stringBuilder.toString()
    }

    private fun getColumnString(): String {
        var isFirstExternal = true
        var isFirstInternal = true

        val stringBuilder = StringBuilder()

        for (list in valueAllocationList) {
            isFirstInternal = true
            if (!isFirstExternal) stringBuilder.append(",")
            stringBuilder.append("(")
            for (valueAllocation in list) {

                if (!isFirstInternal) stringBuilder.append(", ")
                stringBuilder.append(valueAllocation.column)
                isFirstInternal = false

            }
            stringBuilder.append(")")
            isFirstExternal = false

            break
        }

        return stringBuilder.toString()
    }

    private fun getValueString(): String {
        var isFirstExternal = true
        var isFirstInternal = true

        val stringBuilder = StringBuilder()


        for (list in valueAllocationList) {

            if (!isFirstExternal) stringBuilder.append(",")
            stringBuilder.append("(")
            for (valueAllocation in list) {

                if (!isFirstInternal) stringBuilder.append(", ")
                stringBuilder.append(valueAllocation.value)
                isFirstInternal = false

            }
            stringBuilder.append(")")
            isFirstExternal = false
        }

        return stringBuilder.toString()
    }
}