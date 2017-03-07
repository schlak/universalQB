package com.github.schlak.database.QueryBuilder.MysqlQueryBuilder;

import com.github.schlak.database.QueryBuilder.ADBCreateBuilder;
import com.github.schlak.database.QueryBuilder.MysqlQueryBuilder.MysqlStatement.MysqlCreateBox;
import com.github.schlak.database.QueryBuilder.MysqlQueryBuilder.Others.MysqlColumnDefinition;
import com.github.schlak.database.QueryBuilder.Others.ADBColumnDefinition;
import com.github.schlak.database.QueryBuilder.QueryBuildException;
import com.github.schlak.database.QueryBuilder.StatementBox;

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
