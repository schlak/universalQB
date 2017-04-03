package com.github.schlak.database.Implementation.MSSQL.GeneralObjects

import com.github.schlak.database.Definition.GeneralObjects.JoinCondition

/**
 * Created by Jonas Schlak on 21.03.2017.
 */

class MSSQLJoinCondition : JoinCondition(){

    override fun getJoinConditionString(): String {
        return baseColumn.columnString + " = " + joinColumn.columnString
    }

}