package com.github.schlak.database.Implementation.MSSQL.GeneralObjects

import com.github.schlak.database.Definition.FixedValues.OrderByStrategy
import com.github.schlak.database.Definition.GeneralObjects.Column
import com.github.schlak.database.Definition.GeneralObjects.OrderByDefinition

/**
 * Created by Jonas Schlak on 21.03.2017.
 */
class MSSQLOrderByDefinition : OrderByDefinition() {

    override fun setOrderByStrategy(orderByStrategy: OrderByStrategy?) : OrderByDefinition {
        this.orderByStrategy = orderByStrategy
        return this
    }

    override fun getOrderByString(): String {
        return this.orderByString
    }

    override fun setColumn(column: Column?) : OrderByDefinition {
        this.column = column
        return this
    }
}