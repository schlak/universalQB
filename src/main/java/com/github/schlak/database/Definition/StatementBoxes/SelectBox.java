package com.github.schlak.database.Definition.StatementBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;

/**
 * Created by Jonas Schlak on 24.01.17.
 */
public abstract class SelectBox extends StatementBox {

    /**
     * The Table name.
     */
    protected String tableName;

    /**
     * The Shown column list.
     */
    protected String shownColumnList;
    /**
     * The Order a oder by column list.
     */
    protected String orderAOderByColumnList;
    /**
     * The Grouping column list.
     */
    protected String groupingColumnList;
    /**
     * The Table join information list.
     */
    protected String tableJoinInformationList;
    /**
     * The Where condition stack.
     */
    protected ConditionStack whereConditionStack;
    /**
     * The Having condition stack.
     */
    protected ConditionStack havingConditionStack;

    /**
     * Instantiates a new Select box.
     *
     * @param tableName                the tableName name
     * @param shownColumnList          the shown column list
     * @param orderAOderByColumnList   the order a oder by column list
     * @param groupingColumnList       the grouping column list
     * @param tableJoinInformationList the tableName join information list
     * @param whereConditionStack      the where condition stack
     * @param havingConditionStack     the having condition stack
     */
    public SelectBox(String tableName, String shownColumnList,
                     String orderAOderByColumnList, String groupingColumnList,
                     String tableJoinInformationList,
                     ConditionStack whereConditionStack,
                     ConditionStack havingConditionStack) {
        this.tableName = tableName;
        this.shownColumnList = shownColumnList;
        this.orderAOderByColumnList = orderAOderByColumnList;
        this.groupingColumnList = groupingColumnList;
        this.tableJoinInformationList = tableJoinInformationList;
        this.whereConditionStack = whereConditionStack;
        this.havingConditionStack = havingConditionStack;
    }

    @Override
    public Class getType() {
        return SelectBox.class;
    }
}
