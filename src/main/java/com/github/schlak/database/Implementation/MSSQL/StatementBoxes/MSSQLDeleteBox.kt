package com.github.schlak.database.Implementation.MSSQL.StatementBoxes

import com.github.schlak.database.Definition.FixedValues.ConditionLinkType
import com.github.schlak.database.Definition.GeneralObjects.StatementPart
import com.github.schlak.database.Definition.StatementBoxes.BasicDeleteBox
import com.github.schlak.database.Definition.StatementBoxes.StatementBox
import com.github.schlak.database.Exeptions.SQLAppendException
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MySQLConditionStack
import java.sql.Connection
import java.sql.PreparedStatement

/**
 * Created by Jonas Schlak on 22.03.2017.
 */

class MSSQLDeleteBox : BasicDeleteBox() {

    override fun getStatement(connection: Connection): PreparedStatement {
        val wherePart : StatementPart = StatementPart()
        wherePart.setSqlPart("WHERE ")
        wherePart.setValue(conditionStack.conditionString)

        val sql = "DELETE " + this.tableName + " ${wherePart.string};"

        return connection.prepareStatement(sql)
    }

    override fun validate(): Boolean {
        return tableName != ""
    }

    override fun appendStatement(statementBox: StatementBox?) {

        if (!validateAppend(statementBox)) throw SQLAppendException()

        val conditionStack = MySQLConditionStack()
        conditionStack.setConditionLinkType(ConditionLinkType.OR)

        conditionStack.addCondition(this.conditionStack).addCondition((statementBox as BasicDeleteBox).conditionStack)

        this.conditionStack = conditionStack
    }

    override fun getPreparedStatementString(): String {
        val wherePart : StatementPart = StatementPart();
        val statementPart = this.conditionStack.statementPreparationBox;

        wherePart.setSqlPart("WHERE ");
        wherePart.setValue(statementPart.string);

        return "DELETE " + this.tableName + " ${wherePart.string};"
    }
}
