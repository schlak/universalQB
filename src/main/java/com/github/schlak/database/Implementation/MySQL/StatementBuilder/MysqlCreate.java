package com.github.schlak.database.Implementation.MySQL.StatementBuilder;

import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Definition.StatementBoxes.StatementBox;
import com.github.schlak.database.Definition.Statements.BasicCreate;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MysqlColumnDefinition;
import com.github.schlak.database.Implementation.MySQL.StatmentBoxes.MysqlCreateBox;

/**
 * Created by Jonas Schlak on 29.10.2016.
 */
public class MysqlCreate extends BasicCreate {

    @Override
    public ColumnDefinition getNewColumnDefinition() {
        return new MysqlColumnDefinition();
    }


    private void validate() throws QueryBuildException {

        if (columnDefinitionList.size() == 1)
            throw new QueryBuildException("No value allocation are set for the query");

        if (this.table == null)
            throw new QueryBuildException("No table is set for the query");
    }

    @Override
    public StatementBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlCreateBox(columnDefinitionList, table);
    }
}
