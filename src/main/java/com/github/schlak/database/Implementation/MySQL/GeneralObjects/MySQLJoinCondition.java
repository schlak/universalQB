package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.JoinCondition;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLJoinCondition extends JoinCondition {

    public MySQLJoinCondition(Column baseTableColumn, Column joinTableColumn) {
        super(baseTableColumn, joinTableColumn);
    }

    public MySQLJoinCondition() {
        super();
    }

    @Override
    public String getJoinConditionString() {
        return this.toString();
    }

    public String toString(){
        return baseColumn.getColumnString() + " = " + joinColumn.getColumnString();
    }
}
