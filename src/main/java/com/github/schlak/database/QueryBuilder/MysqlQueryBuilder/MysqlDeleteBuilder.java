package com.github.schlak.database.QueryBuilder.MysqlQueryBuilder;

import com.github.schlak.database.QueryBuilder.ADBDeleteBuilder;
import com.github.schlak.database.QueryBuilder.MysqlQueryBuilder.MysqlStatement.MysqlDeleteBox;
import com.github.schlak.database.QueryBuilder.MysqlQueryBuilder.Others.MysqlConditionStack;
import com.github.schlak.database.QueryBuilder.QueryBuildException;
import com.github.schlak.database.QueryBuilder.StatementBox;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlDeleteBuilder extends ADBDeleteBuilder {

    /**
     * Instantiates a new {@link MysqlDeleteBuilder}.
     */
    public MysqlDeleteBuilder() {
        super();
        this.whereConditionStack = new MysqlConditionStack();
    }


    private void validate() throws QueryBuildException {

        if (this.tableName == null)
            throw new QueryBuildException("No tableName is set for the query");
    }


    @Override
    public StatementBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlDeleteBox(tableName, whereConditionStack);
    }

}
