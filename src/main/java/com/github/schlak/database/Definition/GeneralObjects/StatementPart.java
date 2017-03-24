package com.github.schlak.database.Definition.GeneralObjects;

/**
 * Created by Jonas Schlak on 22.03.2017.
 */
public class StatementPart {

    String sqlPart;
    String value;

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getString(){
        return value.equals("") ? "" : sqlPart.concat(" ").concat(value);
    }
}
