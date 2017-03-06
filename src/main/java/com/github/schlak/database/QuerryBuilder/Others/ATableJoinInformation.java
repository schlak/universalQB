package com.schlak.Database.QuerryBuilder.Others;

import com.schlak.Database.DBJoinVariants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class ATableJoinInformation {

    /**
     * The Base table.
     */
    protected String baseTable;
    /**
     * The Table to join.
     */
    protected String tableToJoin;
    /**
     * The {@link AJoinCondition}.
     */
    protected List<AJoinCondition> joinCondition;
    /**
     * The {@link DBJoinVariants}.
     */
    protected DBJoinVariants joinType;

    /**
     * Instantiates a new {@link ATableJoinInformation}.
     */
    public ATableJoinInformation(){
        this.tableToJoin = "";
        this.baseTable = "";
        this.joinCondition = new ArrayList<>();
        this.joinType = DBJoinVariants.Join;
    }

    /**
     * Gets join type string.
     *
     * @return the join type string
     */
    protected String getJoinType() {
        switch (this.joinType){
            case InnerJoin:
                return "INNER JOIN";
            case LeftJoin:
                return "LEFT JOIN";
            case OuterJoin:
                return "OUTER JOIN";
            case RightJoin:
                return "RIGHT JOIN";
            case Join:
                return "JOIN";
        }
        return "JOIN";
    }

    /**
     * Sets join type.
     *
     * @param joinType the {@link DBJoinVariants}
     */
    public abstract void setJoinType(DBJoinVariants joinType);

    /**
     * Add join condition.
     *
     * @param baseTableColumn the base {@link ADBColumn}
     * @param joinTableColumn the join {@link ADBColumn}
     * @throws Exception the exception
     */
    public abstract void addJoinCondition(ADBColumn baseTableColumn, ADBColumn joinTableColumn) throws Exception;

    /**
     * Add join condition.
     *
     * @param joinCondition the {@link AJoinCondition}
     * @throws Exception the exception
     */
    public abstract void addJoinCondition(AJoinCondition joinCondition) throws Exception;

    /**
     * Gets join string.
     *
     * @return the join string
     */
    public abstract String getJoinString();
}
