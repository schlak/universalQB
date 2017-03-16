package com.github.schlak.database.Implementation.MSSQL.FixedValues;


import com.github.schlak.database.Definition.FixedValues.BasicDataType;

/**
 * Created by jonas on 15.03.17.
 */
public enum MSSQLDataType {

    BLOB("varbinary(MAX)"),
    INTEGER("integer"),
    FLOAT("float"),
    TEXT("ntext");

    private String value;

    private MSSQLDataType(String value) {
        this.value = value;
    }

    public static MSSQLDataType get(BasicDataType baseBaseDataType) {
        switch (baseBaseDataType) {
            case REAL:
                return FLOAT;
            case INTEGER:
                return INTEGER;
            case TEXT:
                return TEXT;
        }
        return BLOB;
    }

}
