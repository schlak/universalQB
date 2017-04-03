package com.github.schlak.database.Implementation.MSSQL.FixedValues

import com.github.schlak.database.Definition.FixedValues.BasicQueryTypes

/**
 * Created by Jonas Schlak on 21.03.2017.
 */

enum class MSSQLQueryTypes {

    CREATE,
    UPDATE,
    DELETE,
    INSERT;

    companion object {
        fun get(basicQueryType: BasicQueryTypes): MSSQLQueryTypes {
            when (basicQueryType) {
                BasicQueryTypes.CREATE -> return CREATE;
                BasicQueryTypes.DELETE -> return DELETE;
                BasicQueryTypes.INSERT -> return INSERT;
                BasicQueryTypes.UPDATE -> return UPDATE;
            }
        }
    }

}