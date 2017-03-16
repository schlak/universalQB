package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.JoinCondition;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlJoinCondition extends JoinCondition {

    public MysqlJoinCondition(Column baseTableColumn, Column joinTableColumn) {
        super(baseTableColumn, joinTableColumn);
    }

    public MysqlJoinCondition() {
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
