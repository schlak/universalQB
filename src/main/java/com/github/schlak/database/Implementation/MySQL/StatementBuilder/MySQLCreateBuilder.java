package com.github.schlak.database.Implementation.MySQL.StatementBuilder;

import com.github.schlak.database.Definition.GeneralObjects.ColumnDefinition;
import com.github.schlak.database.Definition.StatementBoxes.BasicCreateBox;
import com.github.schlak.database.Definition.Statements.BasicCreateBuilder;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MySQLColumnDefinition;
import com.github.schlak.database.Implementation.MySQL.StatmentBoxes.MysqlCreateBox;

/**
 * Created by Jonas Schlak on 29.10.2016.
 */
public class MySQLCreateBuilder extends BasicCreateBuilder {

    @Override
    public ColumnDefinition getNewColumnDefinition() {
        return new MySQLColumnDefinition();
    }


    private void validate() throws QueryBuildException {

        if (columnDefinitionList.size() == 1)
            throw new QueryBuildException("No value allocation are set for the query");

        if (this.tableName == null)
            throw new QueryBuildException("No tableName is set for the query");
    }

    @Override
    public BasicCreateBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlCreateBox(columnDefinitionList, tableName);
    }

}
