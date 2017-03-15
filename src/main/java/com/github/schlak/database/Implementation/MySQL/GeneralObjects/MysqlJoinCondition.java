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
    public JoinCondition setBaseTableColumn(Column baseTableColumn) {
        this.baseColumn = baseTableColumn;
        return this;
    }

    @Override
    public JoinCondition setJoinTableColumn(Column joinTableColumn) {
        this.joinColumn = joinTableColumn;
        return this;
    }

    @Override
    public String getBaseTableName() {
        if (this.baseColumn != null)
            return baseColumn.getTableName();
        else return null;
}

    @Override
    public String getJoinTableName() {
        if (this.joinColumn != null)
            return joinColumn.getTableName();
        else return null;
    }

    @Override
    public String getJoinConditionString() {
        return this.toString();
    }

    public String toString(){
        return baseColumn.getColumnString() + " = " + joinColumn.getColumnString();
    }
}
