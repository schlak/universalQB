package com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.Others;

import com.schlak.Database.QuerryBuilder.Others.ADBColumn;
import com.schlak.Database.QuerryBuilder.Others.AJoinCondition;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlJoinCondition extends AJoinCondition {

    public MysqlJoinCondition(ADBColumn baseTableColumn, ADBColumn joinTableColumn) {
        super(baseTableColumn, joinTableColumn);
    }

    public MysqlJoinCondition() {
        super();
    }

    @Override
    public AJoinCondition setBaseTableColumn(ADBColumn baseTableColumn) {
        this.baseColumn = baseTableColumn;
        return this;
    }

    @Override
    public AJoinCondition setJoinTableColumn(ADBColumn joinTableColumn) {
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
