package com.github.schlak.database.Implementation.MSSQL.GeneralObjects

import com.github.schlak.database.Definition.FixedValues.BasicJoinType
import com.github.schlak.database.Definition.GeneralObjects.JoinCondition
import com.github.schlak.database.Definition.GeneralObjects.TableJoinInformation

/**
 * Created by Jonas Schlak on 21.03.2017.
 */
class MSSQLTableJoinInformation : TableJoinInformation(){

    override fun setJoinType(joinType: BasicJoinType?) {
        this.joinType = joinType
    }

    override fun getJoinString(): String {
        var conditions = ""
        var isFirst: Boolean = true

        for (joinCondition in this.joinCondition) {
            if (!isFirst) conditions += " AND "

            conditions += joinCondition.toString()
            isFirst = false
        }

        return this.getJoinType() + " " + tableToJoin + " ON " + conditions
    }

    override fun getJoinConditionInstance(): JoinCondition {
        return MSSQLJoinCondition()
    }
}