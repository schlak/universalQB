package com.github.schlak.database.Implementation.SQLite.FixedValues;


import com.github.schlak.database.Definition.FixedValues.QueryTypes;

/**
 * Created by jonas on 15.03.17.
 */
public enum SQLiteQueryTypes {

    CREATE, UPDATE, DELETE, INSERT;


    public static SQLiteQueryTypes get(QueryTypes queryTypes) {
        if (queryTypes == null) throw new NullPointerException();
        switch (queryTypes) {
            case CREATE:
                return CREATE;
            case UPDATE:
                return UPDATE;
            case DELETE:
                return DELETE;
            case INSERT:
                return INSERT;
        }
        return null;
    }

}
