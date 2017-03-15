package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.BasicJoinType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class TableJoinInformation {

    /**
     * The Base table.
     */
    protected String baseTable;
    /**
     * The Table to join.
     */
    protected String tableToJoin;
    /**
     * The {@link JoinCondition}.
     */
    protected List<JoinCondition> joinCondition;
    /**
     * The {@link BasicJoinType}.
     */
    protected BasicJoinType joinType;

    /**
     * Instantiates a new {@link TableJoinInformation}.
     */
    public TableJoinInformation() {
        this.tableToJoin = "";
        this.baseTable = "";
        this.joinCondition = new ArrayList<>();
        this.joinType = BasicJoinType.LeftJoin;
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
    public abstract void addJoinCondition(Column baseTableColumn, Column joinTableColumn) throws Exception;

    /**
     * Add join condition.
     *
     * @param joinCondition the {@link JoinCondition}
     * @throws Exception the exception
     */
    public abstract void addJoinCondition(JoinCondition joinCondition) throws Exception;

    /**
     * Gets join string.
     *
     * @return the join string
     */
    public abstract String getJoinString();
}
