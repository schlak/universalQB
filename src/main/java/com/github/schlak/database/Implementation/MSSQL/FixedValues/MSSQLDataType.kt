package com.github.schlak.database.Implementation.MSSQL.FixedValues

import com.github.schlak.database.Definition.FixedValues.BasicDataType

/**
 * Created by Jonas Schlak on 21.03.2017.
 */
enum class MSSQLDataType(val value : String) {

    BLOB("varbinary(MAX)"),
    INTEGER("integer"),
    FLOAT("float"),
    TEXT("ntext");

    companion object{
        fun get(baseBaseDataType : BasicDataType): MSSQLDataType {

            when (baseBaseDataType){
                BasicDataType.REAL -> return FLOAT;
                BasicDataType.INTEGER -> return INTEGER;
                BasicDataType.TEXT -> return  TEXT;
                else -> {
                    return BLOB;
                }
            }
        }
    }
}