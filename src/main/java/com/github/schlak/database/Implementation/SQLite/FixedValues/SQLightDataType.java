package com.github.schlak.database.Implementation.SQLite.FixedValues;


import com.github.schlak.database.Definition.FixedValues.BasicDataType;

/**
 * Created by jonas on 15.03.17.
 */
public enum SQLightDataType {

    BLOB("BLOB"),
    INTEGER("INTEGER"),
    REAL("REAL"),
    TEXT("TEXT"),
    NULL("NULL");

    private String value;

    private SQLightDataType(String value) {
        this.value = value;
    }

    public SQLightDataType get(BasicDataType baseBaseDataType) {
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
