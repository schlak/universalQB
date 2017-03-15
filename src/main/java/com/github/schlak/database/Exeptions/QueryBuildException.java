package com.github.schlak.database.Exeptions;

/**
 * Created by Jonas Schlak on 08.11.2016.
 * <p/>
 * The query build exception is used to be thrown if the validation before building a query fails.
 */
public class QueryBuildException extends Exception {
    public QueryBuildException(String s) {
        super(s);
    }
}
