package com.github.schlak.database.Implementation.MySQL.StatementBuilder;

import com.github.schlak.database.Definition.StatementBoxes.StatementBox;
import com.github.schlak.database.Definition.Statements.BasicDelete;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MysqlConditionStack;
import com.github.schlak.database.Implementation.MySQL.StatmentBoxes.MysqlDeleteBox;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlDelete extends BasicDelete {

    /**
     * Instantiates a new {@link MysqlDelete}.
     */
    public MysqlDelete() {
        super();
        this.whereConditionStack = new MysqlConditionStack();
    }


    private void validate() throws QueryBuildException {

        if (this.table == null)
            throw new QueryBuildException("No table is set for the query");
    }


    @Override
    public StatementBox getStatementBox() throws QueryBuildException {
        validate();
        return new MysqlDeleteBox(table, whereConditionStack);
    }

}
