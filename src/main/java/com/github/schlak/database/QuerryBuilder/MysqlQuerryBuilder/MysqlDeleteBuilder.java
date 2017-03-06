package com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder;

import com.schlak.Database.QuerryBuilder.ADBDeleteBuilder;
import com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.MysqlStatement.MysqlDeleteBox;
import com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.Others.MysqlConditionStack;
import com.schlak.Database.QuerryBuilder.QueryBuildException;
import com.schlak.Database.QuerryBuilder.StatementBox;

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
