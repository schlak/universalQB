package com.github.schlak.database.Implementation.MySQL.StatementBuilder;

import com.github.schlak.database.Definition.StatementBoxes.StatementBox;
import com.github.schlak.database.Definition.Statements.BasicUpdate;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MysqlConditionStack;
import com.github.schlak.database.Implementation.MySQL.StatmentBoxes.MysqlUpdateBox;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlUpdate extends BasicUpdate {

    /**
     * Instantiates a new {@link MysqlUpdate}.
     */
    public MysqlUpdate() {
        super();
        this.whereConditionStack = new MysqlConditionStack();
    }

    private void validate() throws QueryBuildException {

        if (valueAllocationList.size() == 0)
            throw new QueryBuildException("No value allocation are set for the query");

        if (this.table == null)
            throw new QueryBuildException("No table is set for the query");
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
    public StatementBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlUpdateBox(table, valueAllocationList,
                whereConditionStack);
    }
}
