package com.github.schlak.database.Definition.StatementBoxes;

import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;

import java.util.List;

/**
 * Created by Jonas Schlak on 25.01.17.
 */
public abstract class BasicCreateBox extends StatementBox {

    /**
     * The Column definition list.
     */
    protected List<ColumnDefinition> columnDefinitionList;
    /**
     * The Table name.
     */
    protected String tableName;

    /**
     * Instantiates a new Create box.
     *
     * @param columnDefinitionList the column definition list
     * @param tableName            the table name
     */
    public BasicCreateBox(List<ColumnDefinition> columnDefinitionList, String tableName) {
        this.columnDefinitionList = columnDefinitionList;
        this.tableName = tableName;
    }

    @Override
    public Class getType() {
        return BasicCreateBox.class;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!obj.getClass().equals(BasicCreateBox.class))
            return false;

        BasicCreateBox createBox = (BasicCreateBox) obj;

        return createBox.tableName.equals(this.tableName);
    }
}
