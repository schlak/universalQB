package com.schlak.Database.QuerryBuilder;

import com.schlak.Database.QuerryBuilder.Interface.IQuery;
import com.schlak.Database.QuerryBuilder.Others.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class ADBSelectBuilder implements IQuery {

    /**
     * The Table name.
     */
    protected String tableName = null;

    /**
     * The Table join information list.
     */
    protected List<ATableJoinInformation> tableJoinInformationList;
    /**
     * The Shown column list.
     */
    protected List<ADBColumn> shownColumnList;
    /**
     * The Where condition stack.
     */
    protected AConditionStack whereConditionStack;
    /**
     * The Grouping column list.
     */
    protected List<ADBColumn> groupingColumnList;
    /**
     * The Having condition stack.
     */
    protected AConditionStack havingConditionStack;
    /**
     * The Order a oder by column list.
     */
    protected List<AOrderByColumn> orderAOrderByColumnList;

    /**
     * The limit of data sets the query will effect.
     *
     * if the limit is set zero the code handel's the value as not set and ignores the limit while
     * generating the statement
     */
    protected int limit;

    /**
     * Instantiates a new Adb select builder.
     */
    public ADBSelectBuilder() {

        this.tableJoinInformationList = new ArrayList<>();
        this.shownColumnList = new ArrayList<>();
        this.groupingColumnList = new ArrayList<>();
        this.orderAOrderByColumnList = new ArrayList<>();

        this.limit = 0;

    }

    /*methods to set parameters, that are necessary to build the query*/

    /**
     * Add table join adb select builder.
     *
     * @param tableJoinInformation the table join information
     * @return the adb select builder
     */
    public ADBSelectBuilder addTableJoin(ATableJoinInformation tableJoinInformation){
        tableJoinInformationList.add(tableJoinInformation);
        return this;
    }

    /**
     * Add shown columns adb select builder.
     *
     * @param aDBColumn the a db column
     * @return the adb select builder
     */
    public ADBSelectBuilder addShownColumns(ADBColumn aDBColumn){
        shownColumnList.add(aDBColumn);
        return this;
    }

    /**
     * Add where condition adb select builder.
     *
     * @param iValueCondition the value condition
     * @return the adb select builder
     */
    public ADBSelectBuilder addWhereCondition(AValueAllocation iValueCondition){
        whereConditionStack.addCondition(iValueCondition);
        return this;
    }

    /**
     * Add where condition adb select builder.
     *
     * @param iConditionStack the condition stack
     * @return the adb select builder
     */
    public ADBSelectBuilder addWhereCondition(AConditionStack iConditionStack){
        whereConditionStack.addCondition(iConditionStack);
        return this;
    }

    /**
     * Add grouping column adb select builder.
     *
     * @param aDBColumn the a db column
     * @return the adb select builder
     */
    public ADBSelectBuilder addGroupingColumn(ADBColumn aDBColumn){
        groupingColumnList.add(aDBColumn);
        return this;
    }

    /**
     * Add having condition adb select builder.
     *
     * @param iValueCondition the value condition
     * @return the adb select builder
     */
    public ADBSelectBuilder addHavingCondition(AValueAllocation iValueCondition){
        havingConditionStack.addCondition(iValueCondition);
        return this;
    }

    /**
     * Add having condition adb select builder.
     *
     * @param iConditionStack the condition stack
     * @return the adb select builder
     */
    public ADBSelectBuilder addHavingCondition(AConditionStack iConditionStack) {
        havingConditionStack.addCondition(iConditionStack);
        return this;
    }

    /**
     * Add order by column adb select builder.
     *
     * @param iOrderByColumn the order by column
     * @return the adb select builder
     */
    public ADBSelectBuilder addOrderByColumn(AOrderByColumn iOrderByColumn) {
        orderAOrderByColumnList.add(iOrderByColumn);
        return this;
    }

    /**
     * Set table adb select builder.
     *
     * @param table the table
     * @return the adb select builder
     */
    public ADBSelectBuilder setTable(String table){
        this.tableName = table;
        return this;
    }

    /**
     * Sets limit.
     *
     * @param limit the limit
     * @return the limit
     */
    public ADBSelectBuilder setLimit(int limit) {
        if (limit < 0)
            this.limit = 0;
        this.limit = limit;
        return this;
    }

}
