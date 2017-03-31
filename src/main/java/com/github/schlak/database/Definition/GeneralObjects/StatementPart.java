package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.Cleanable;

/**
 * Created by Jonas Schlak on 22.03.2017.
 */
public class StatementPart implements Cleanable{

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

    @Override
    public void clean() {
        this.sqlPart = "";
        this.value = "";
    }
}
