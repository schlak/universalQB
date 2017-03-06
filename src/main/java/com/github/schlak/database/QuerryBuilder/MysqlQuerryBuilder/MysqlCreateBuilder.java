package com.github.schlak.database.QuerryBuilder.MysqlQuerryBuilder;

import com.github.schlak.database.QuerryBuilder.ADBCreateBuilder;
import com.github.schlak.database.QuerryBuilder.MysqlQuerryBuilder.MysqlStatement.MysqlCreateBox;
import com.github.schlak.database.QuerryBuilder.MysqlQuerryBuilder.Others.MysqlColumnDefinition;
import com.github.schlak.database.QuerryBuilder.Others.ADBColumnDefinition;
import com.github.schlak.database.QuerryBuilder.QueryBuildException;
import com.github.schlak.database.QuerryBuilder.StatementBox;

/**
 * Created by Jonas Schlak on 29.10.2016.
 */
public class MysqlCreateBuilder extends ADBCreateBuilder {

    @Override
    public ADBColumnDefinition getNewColumnDefinition() {
        return new MysqlColumnDefinition();
    }


    private void validate() throws QueryBuildException {

        if (columnDefinitionList.size() == 1)
            throw new QueryBuildException("No value allocation are set for the query");

        if (this.tableName == null)
            throw new QueryBuildException("No tableName is set for the query");
    }

    @Override
    public StatementBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlCreateBox(columnDefinitionList,tableName);
    }
}
