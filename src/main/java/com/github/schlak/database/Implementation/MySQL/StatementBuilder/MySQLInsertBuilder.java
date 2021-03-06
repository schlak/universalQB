package com.github.schlak.database.Implementation.MySQL.StatementBuilder;

import com.github.schlak.database.Definition.Statements.BasicInsertBuilder;
import com.github.schlak.database.Exeptions.QueryBuildException;
import com.github.schlak.database.Implementation.MySQL.StatmentBoxes.MysqlInsertBox;
import com.github.schlak.database.ObjectRecycler;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLInsertBuilder extends BasicInsertBuilder {


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
    public MysqlInsertBox getStatementBox() throws QueryBuildException {
        validate();

        MysqlInsertBox box = ObjectRecycler.getInstance(MysqlInsertBox.class);
        box.init(tableName, valueAllocationList);

        return box;
    }
}
