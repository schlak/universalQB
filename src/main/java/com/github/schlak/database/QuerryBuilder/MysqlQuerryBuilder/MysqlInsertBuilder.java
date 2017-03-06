package com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder;

import com.schlak.Database.QuerryBuilder.ADBInsertBuilder;
import com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.MysqlStatement.MysqlInsertBox;
import com.schlak.Database.QuerryBuilder.QueryBuildException;
import com.schlak.Database.QuerryBuilder.StatementBox;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlInsertBuilder extends ADBInsertBuilder {


    /**
     * //TODO write comment
     *
     * @throws QueryBuildException
     */
    private void validate() throws QueryBuildException {

        if (valueAllocationList.size() == 0)
            throw new QueryBuildException("No value allocation are set for the query");

        if (this.tableName == null)
            throw new QueryBuildException("No tableName is set for the query");
    }

    @Override
    public StatementBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlInsertBox(tableName, valueAllocationList);
    }
}
