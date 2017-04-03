package com.github.schlak.database.Implementation.MySQL.FixedValues;


import com.github.schlak.database.Definition.FixedValues.BasicDataType;

/**
 * Created by jonas on 15.03.17.
 */
public enum MySQLDataType {

    BLOB("BLOB"),
    INTEGER("INTEGER"),
    REAL("REAL"),
    TEXT("TEXT"),
//    INT("INT"),
//    DOUBLE("DOUBLE"),
//    TINYINT("TINYINT"),
//    SMALLINT("SMALLINT"),
//    MEDIUMINT("MEDIUMINT"),
//    BIGINT("BIGINT"),
//    FLOAT("FLOAT"),
//    DECIMAL("DECIMAL"),
//    NUMERIC("NUMERIC"),
//    DATE("DATE"),
//    DATETIME("DATETIME"),
//    TIMESTAMP("TIMESTAMP"),
//    TIME("TIME"),
//    YEAR("YEAR"),
//    CHAR("CHAR"),
//    VARCHAR("VARCHAR"),
//    BINARY("BINARY"),
//    ENUM("ENUM"),
//    SET("SET")
 ;

    private String value;

    private MySQLDataType(String value) {
        this.value = value;
    }

    public static MySQLDataType get(BasicDataType baseBaseDataType) {
        switch (baseBaseDataType) {
            case REAL:
                return REAL;
            case INTEGER:
                return INTEGER;
            case TEXT:
                return TEXT;
        }
        return BLOB;
    }

    @Override
    public String toString() {
        return value;
    }
}
