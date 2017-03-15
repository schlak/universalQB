package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.BasicJoinType;
import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.JoinCondition;
import com.github.schlak.database.Definition.GeneralObjects.TableJoinInformation;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlTableJoinInformation extends TableJoinInformation {

    public MysqlTableJoinInformation(Column baseTableColumn, Column joinTableColumn) throws Exception {
        this.addJoinCondition(baseTableColumn, joinTableColumn);
    }

    public MysqlTableJoinInformation(JoinCondition joinCondition) throws Exception {
        this.addJoinCondition(joinCondition);
    }

    public MysqlTableJoinInformation() {
    }

    @Override
    public void addJoinCondition(Column baseTableColumn, Column joinTableColumn) throws Exception {


        if (this.tableToJoin.equals(joinTableColumn.getTableName()) || this.tableToJoin.equals(""))
            this.tableToJoin = joinTableColumn.getTableName();
        this.baseTable = baseTableColumn.getTableName();
        if (this.baseTable.equals(baseTableColumn.getTableName()) || this.baseTable.equals(""))
            this.baseTable = baseTableColumn.getTableName();
        else throw new Exception("Illegal table exception");

        this.joinCondition.add(new MysqlJoinCondition(baseTableColumn, joinTableColumn));
    }

    @Override
    public void addJoinCondition(JoinCondition joinCondition) throws Exception {
        if (joinCondition.getBaseTableName().equals(this.baseTable) || this.baseTable.equals(""))
            this.baseTable = joinCondition.getBaseTableName();
        else throw new Exception("Illegal table exception");
        if (joinCondition.getJoinTableName().equals(this.tableToJoin) || this.tableToJoin.equals(""))
            this.tableToJoin = joinCondition.getJoinTableName();
        else throw new Exception("Illegal table exception");

        this.joinCondition.add(joinCondition);
    }

    @Override
    public String getJoinString() {
        String conditions = "";
        Boolean isFirst = true;

        for (JoinCondition joinCondition : this.joinCondition) {
            if (!isFirst) conditions = conditions.concat(" AND ");
            conditions = conditions.concat(joinCondition.toString());
            isFirst = false;
        }

        return this.getJoinType() + " " + tableToJoin + " ON " + conditions;
    }

    @Override
    public void setJoinType(BasicJoinType joinType) {
        this.joinType = joinType;
    }

}
