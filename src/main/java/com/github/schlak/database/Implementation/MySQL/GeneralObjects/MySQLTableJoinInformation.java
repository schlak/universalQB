package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.BasicJoinType;
import com.github.schlak.database.Definition.GeneralObjects.Column;
import com.github.schlak.database.Definition.GeneralObjects.JoinCondition;
import com.github.schlak.database.Definition.GeneralObjects.TableJoinInformation;
import com.github.schlak.database.ObjectRecycler;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLTableJoinInformation extends TableJoinInformation {

    public MySQLTableJoinInformation(Column baseTableColumn, Column joinTableColumn) throws Exception {
        this.addJoinCondition(baseTableColumn, joinTableColumn);
    }

    public MySQLTableJoinInformation(JoinCondition joinCondition) throws Exception {
        this.addJoinCondition(joinCondition);
    }

    public MySQLTableJoinInformation() {
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
        return ObjectRecycler.getInstance(MySQLJoinCondition.class);
    }

    @Override
    public void setJoinType(BasicJoinType joinType) {
        this.joinType = joinType;
    }

}
