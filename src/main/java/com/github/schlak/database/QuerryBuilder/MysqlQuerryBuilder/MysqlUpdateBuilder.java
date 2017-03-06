package com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder;

import com.schlak.Database.QuerryBuilder.ADBUpdateBuilder;
import com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.MysqlStatement.MysqlUpdateBox;
import com.schlak.Database.QuerryBuilder.MysqlQuerryBuilder.Others.MysqlConditionStack;
import com.schlak.Database.QuerryBuilder.QueryBuildException;
import com.schlak.Database.QuerryBuilder.StatementBox;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlUpdateBuilder extends ADBUpdateBuilder {

    /**
     * Instantiates a new {@link MysqlUpdateBuilder}.
     */
    public MysqlUpdateBuilder() {
        super();
        this.whereConditionStack = new MysqlConditionStack();
    }

    private void validate() throws QueryBuildException {

        if (valueAllocationList.size() == 0)
            throw new QueryBuildException("No value allocation are set for the query");

        if (this.tableName == null)
            throw new QueryBuildException("No tableName is set for the query");
    }

    /**
     * The {@link MysqlUpdateBox} is the representation of the Statement in a form that you can't change the statement anymore.
     * This format is the only one, that is excepted by the {@link com.schlak.Database.QuerryBuilder.Manager.QueryManager}
     * to execute a query or to be able to get a prepared statement.
     *
     * @return the {@link MysqlUpdateBox}
     * @throws QueryBuildException if the validation fails.
     */
    @Override
    public StatementBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlUpdateBox(tableName, valueAllocationList,
                whereConditionStack);
    }
}
