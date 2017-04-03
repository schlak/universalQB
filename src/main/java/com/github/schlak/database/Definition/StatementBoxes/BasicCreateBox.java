package com.github.schlak.database.Definition.StatementBoxes;

import com.github.schlak.database.Definition.Cleanable;
import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.ObjectRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 25.01.17.
 */
public abstract class BasicCreateBox extends StatementBox {

    protected List<ColumnDefinition> columnDefinitionList;
    protected String tableName;

    /**
     * Instantiates a new Create box.
     */
    public BasicCreateBox() {

    }

    public void init(List<ColumnDefinition> columnDefinitionList, String tableName){
        this.tableName = tableName;
        this.columnDefinitionList = columnDefinitionList;
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

    @Override
    public void clean() {
        if (this.columnDefinitionList != null){
            this.columnDefinitionList.forEach(ObjectRecycler::returnInstance);
            this.columnDefinitionList.clear();
        }else{
            this.columnDefinitionList = new ArrayList<>();
        }

        this.tableName = "";
    }
}
