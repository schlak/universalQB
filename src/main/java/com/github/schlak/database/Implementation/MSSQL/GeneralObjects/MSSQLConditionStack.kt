package com.github.schlak.database.Implementation.MSSQL.GeneralObjects

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack
import com.github.schlak.database.Definition.GeneralObjects.PreparedStatementPart
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation

/**
 * Created by Jonas Schlak on 21.03.2017.
 */
class MSSQLConditionStack : ConditionStack() {

    override fun getConditionString(): String {
        var returnString : String = ""
        var isFirst : Boolean = true

        for (valueAllocation : ValueAllocation in valueConditionList) {
            if (!isFirst)  returnString += (" " + this.conditionLinkType.toString() + " ")
            returnString += valueAllocation.toString()
            isFirst = false
        }

        if (valueConditionList.size == 0) isFirst = true


        for (conditionStack : ConditionStack in conditionStackList) {
            if (!isFirst) returnString += (" " + this.conditionLinkType.toString() + " ")
            returnString += (" ( " + conditionStack.toString() + " ) ")
            isFirst = false
        }

        return returnString
    }

    override fun getStatementPreparationBox(): PreparedStatementPart {

        val preparedStatementPart :  PreparedStatementPart  = PreparedStatementPart();

        var isFirst : Boolean = true

        for (valueAllocation : ValueAllocation in valueConditionList) {
            if (!isFirst) preparedStatementPart.string = preparedStatementPart.string + (" " + this.conditionLinkType.toString() + " ")
            preparedStatementPart.string = preparedStatementPart.string + (valueAllocation.column + " = ? ")
            preparedStatementPart.queue.add(valueAllocation.value)
            isFirst = false
        }

        for (conditionStack in conditionStackList) {
            preparedStatementPart.add(conditionStack.statementPreparationBox, conditionLinkType)
        }

        return preparedStatementPart
    }
}