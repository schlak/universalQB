package com.github.schlak.database.Implementation.MySQL.StatementBuilder;

import com.github.schlak.database.Definition.Statements.BasicUpdateBuilder;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MySQLConditionStack;
import com.github.schlak.database.Implementation.MySQL.StatmentBoxes.MysqlUpdateBox;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLUpdateBuilder extends BasicUpdateBuilder {

    /**
     * Instantiates a new {@link MySQLUpdateBuilder}.
     */
    public MySQLUpdateBuilder() {
        super();
        this.whereConditionStack = new MySQLConditionStack();
    }

    private void validate() throws QueryBuildException {

        if (valueAllocationList.size() == 0)
            throw new QueryBuildException("No value allocation are set for the query");

        if (this.table == null)
            throw new QueryBuildException("No tableName is set for the query");
    }

    /**
     * The {@link MysqlUpdateBox} is the representation of the Statement in a form that you can't change the statement anymore.
     * This format is the only one, that is excepted by the {@link com.github.schlak.database.Manager.QueryManager}
     * to execute a query or to be able to get a prepared statement.
     *
     * @return the {@link MysqlUpdateBox}
     * @throws QueryBuildException if the validation fails.
     */
    @Override
    public MysqlUpdateBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlUpdateBox(table, valueAllocationList,
                whereConditionStack);
    }
}
