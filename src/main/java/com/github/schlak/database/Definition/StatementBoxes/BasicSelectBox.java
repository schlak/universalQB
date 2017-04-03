package com.github.schlak.database.Definition.StatementBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.ObjectRecycler;

/**
 * Created by Jonas Schlak on 24.01.17.
 */
public abstract class BasicSelectBox extends StatementBox {

    protected String tableName;
    protected String shownColumnList;
    protected String orderAOderByColumnList;
    protected String groupingColumnList;
    protected String tableJoinInformationList;
    protected ConditionStack whereConditionStack;
    protected ConditionStack havingConditionStack;

    /**
     * Instantiates a new Select box.
     */
    public BasicSelectBox() {
        clean();
    }

    public void init(String tableName, String shownColumnList,
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
        return BasicSelectBox.class;
    }

    @Override
    public void clean() {

        if (whereConditionStack != null)
            ObjectRecycler.returnInstance(whereConditionStack);
        if (havingConditionStack != null)
            ObjectRecycler.returnInstance(havingConditionStack);

        this.tableName = "";
        this.shownColumnList = "";
        this.orderAOderByColumnList = "";
        this.groupingColumnList = "";
        this.tableJoinInformationList = "";
    }
}
