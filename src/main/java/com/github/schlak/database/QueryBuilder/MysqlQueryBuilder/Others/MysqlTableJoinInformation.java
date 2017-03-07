package com.github.schlak.database.QueryBuilder.MysqlQueryBuilder.Others;

import com.github.schlak.database.DBJoinVariants;
import com.github.schlak.database.QueryBuilder.Others.ADBColumn;
import com.github.schlak.database.QueryBuilder.Others.AJoinCondition;
import com.github.schlak.database.QueryBuilder.Others.ATableJoinInformation;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlTableJoinInformation extends ATableJoinInformation {

    public MysqlTableJoinInformation(ADBColumn baseTableColumn, ADBColumn joinTableColumn) throws Exception {
        this.addJoinCondition(baseTableColumn, joinTableColumn);
    }

    public MysqlTableJoinInformation(AJoinCondition joinCondition) throws Exception {
        this.addJoinCondition(joinCondition);
    }

    public MysqlTableJoinInformation() {
    }

    @Override
    public void addJoinCondition(ADBColumn baseTableColumn, ADBColumn joinTableColumn) throws Exception {


        if (this.tableToJoin.equals(joinTableColumn.getTableName()) || this.tableToJoin.equals(""))
            this.tableToJoin = joinTableColumn.getTableName();
        this.baseTable = baseTableColumn.getTableName();
        if (this.baseTable.equals(baseTableColumn.getTableName()) || this.baseTable.equals(""))
            this.baseTable = baseTableColumn.getTableName();
        else throw new Exception("Illegal tableName exception");

        this.joinCondition.add(new MysqlJoinCondition(baseTableColumn, joinTableColumn));
    }

    @Override
    public void addJoinCondition(AJoinCondition joinCondition) throws Exception {
        if (joinCondition.getBaseTableName().equals(this.baseTable) || this.baseTable.equals(""))
            this.baseTable = joinCondition.getBaseTableName();
        else throw new Exception("Illegal tableName exception");
        if (joinCondition.getJoinTableName().equals(this.tableToJoin) || this.tableToJoin.equals(""))
            this.tableToJoin = joinCondition.getJoinTableName();
        else throw new Exception("Illegal tableName exception");

        this.joinCondition.add(joinCondition);
    }

    @Override
    public String getJoinString() {
        String conditions = "";
        Boolean isFirst = true;

        for (AJoinCondition joinCondition : this.joinCondition) {
            if (!isFirst) conditions = conditions.concat(" AND ");
            conditions = conditions.concat(joinCondition.toString());
            isFirst = false;
        }

        return this.getJoinType() + " " + tableToJoin + " ON " + conditions;
    }

    @Override
    public void setJoinType(DBJoinVariants joinType) {
        this.joinType = joinType;
    }

}
