package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.Cleanable;
import com.github.schlak.database.Definition.FixedValues.BasicJoinType;
import com.github.schlak.database.ObjectRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class TableJoinInformation implements Cleanable {


    protected String baseTable;
    protected String tableToJoin;
    protected List<JoinCondition> joinCondition;
    protected BasicJoinType joinType;

    /**
     * Instantiates a new {@link TableJoinInformation}.
     */
    public TableJoinInformation() {
        this.clean();
    }

    /**
     * Gets join type string.
     *
     * @return the join type string
     */
    protected String getJoinType() {
        return this.joinType.toString();
    }

    /**
     * Sets join type.
     *
     * @param joinType the {@link BasicJoinType}
     */
    public abstract void setJoinType(BasicJoinType joinType);

    /**
     * Add join condition.
     *
     * @param baseTableColumn the base {@link Column}
     * @param joinTableColumn the join {@link Column}
     * @throws Exception the exception
     */
    public void addJoinCondition(Column baseTableColumn, Column joinTableColumn) throws Exception {
        if (this.tableToJoin.equals(joinTableColumn.getTableName()) || this.tableToJoin.equals(""))
            this.tableToJoin = joinTableColumn.getTableName();
        this.baseTable = baseTableColumn.getTableName();
        if (this.baseTable.equals(baseTableColumn.getTableName()) || this.baseTable.equals(""))
            this.baseTable = baseTableColumn.getTableName();
        else throw new Exception("Illegal tableName exception");

        JoinCondition joinCondition = this.getJoinConditionInstance();

        joinCondition.setBaseTableColumn(baseTableColumn);
        joinCondition.setJoinTableColumn(joinTableColumn);

        this.joinCondition.add(joinCondition);
    }

    /**
     * Add join condition.
     *
     * @param joinCondition the {@link JoinCondition}
     * @throws Exception the exception
     */
    public void addJoinCondition(JoinCondition joinCondition) throws Exception {
        if (joinCondition.getBaseTableName().equals(this.baseTable) || this.baseTable.equals(""))
            this.baseTable = joinCondition.getBaseTableName();
        else throw new Exception("Illegal tableName exception");
        if (joinCondition.getJoinTableName().equals(this.tableToJoin) || this.tableToJoin.equals(""))
            this.tableToJoin = joinCondition.getJoinTableName();
        else throw new Exception("Illegal tableName exception");

        this.joinCondition.add(joinCondition);
    }

    /**
     * Gets join string.
     *
     * @return the join string
     */
    public abstract String getJoinString();

    public abstract JoinCondition getJoinConditionInstance();

    @Override
    public void clean() {

        if (joinCondition != null){
            joinCondition.forEach(ObjectRecycler::returnInstance);
            joinCondition.clear();
        }else{
            this.joinCondition = new ArrayList<>();
        }

        this.setJoinType(BasicJoinType.LeftJoin);
        this.baseTable = "";
        this.tableToJoin = "";
    }
}
