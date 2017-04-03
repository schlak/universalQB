package com.github.schlak.database.Definition;


import com.github.schlak.database.Definition.StatementBoxes.StatementBox;
import com.github.schlak.database.Exeptions.QueryBuildException;

/**
 * Created by jschl on 16.01.2017.
 */
public interface IQuery extends Cleanable {

    /**
     * Returns the {@link StatementBox} of the query builder that is implementing the interface.
     *
     * @return the {@link StatementBox}
     * @throws QueryBuildException the {@link QueryBuildException}
     */
    StatementBox getStatementBox() throws QueryBuildException;

}