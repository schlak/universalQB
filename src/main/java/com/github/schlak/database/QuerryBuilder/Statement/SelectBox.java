package com.github.schlak.database.QuerryBuilder.Statement;

import com.github.schlak.database.QuerryBuilder.Others.AConditionStack;
import com.github.schlak.database.QuerryBuilder.StatementBox;

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
    protected AConditionStack whereConditionStack;
    /**
     * The Having condition stack.
     */
    protected AConditionStack havingConditionStack;

    /**
     * Instantiates a new Select box.
     *
     * @param tableName                the table name
     * @param shownColumnList          the shown column list
     * @param orderAOderByColumnList   the order a oder by column list
     * @param groupingColumnList       the grouping column list
     * @param tableJoinInformationList the table join information list
     * @param whereConditionStack      the where condition stack
     * @param havingConditionStack     the having condition stack
     */
    public SelectBox(String tableName, String shownColumnList,
                     String orderAOderByColumnList, String groupingColumnList,
                     String tableJoinInformationList,
                     AConditionStack whereConditionStack,
                     AConditionStack havingConditionStack) {
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
