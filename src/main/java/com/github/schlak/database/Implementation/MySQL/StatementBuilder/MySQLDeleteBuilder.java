package com.github.schlak.database.Implementation.MySQL.StatementBuilder;

import com.github.schlak.database.Definition.Statements.BasicDeleteBuilder;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.MySQLConditionStack;
import com.github.schlak.database.Implementation.MySQL.StatmentBoxes.MysqlDeleteBox;
import com.github.schlak.database.ObjectRecycler;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLDeleteBuilder extends BasicDeleteBuilder {

    /**
     * Instantiates a new {@link MySQLDeleteBuilder}.
     */
    public MySQLDeleteBuilder() {
        clean();
    }


    private void validate() throws QueryBuildException {

        if (this.table == null)
            throw new QueryBuildException("No tableName is set for the query");
    }


    @Override
    public MysqlDeleteBox getStatementBox() throws QueryBuildException {
        validate();

        MysqlDeleteBox box = ObjectRecycler.getInstance(MysqlDeleteBox.class);
        box.init(table, whereConditionStack);

        return box;
    }

    @Override
    public void clean() {

        super.clean();
        whereConditionStack = ObjectRecycler.getInstance(MySQLConditionStack.class);
    }
}
