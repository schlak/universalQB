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
    public JoinCondition getJoinConditionInstance() {
        return new MysqlJoinCondition();
    }

    @Override
    public void setJoinType(BasicJoinType joinType) {
        this.joinType = joinType;
    }

}
