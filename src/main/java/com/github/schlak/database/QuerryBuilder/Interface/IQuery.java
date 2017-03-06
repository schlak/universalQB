package com.schlak.Database.QuerryBuilder.Interface;

import com.schlak.Database.QuerryBuilder.QueryBuildException;
import com.schlak.Database.QuerryBuilder.StatementBox;

/**
 * Created by jschl on 16.01.2017.
 */
public interface IQuery {

    /**
     * Returns the {@link StatementBox} of the query builder that is implementing the interface.
     *
     * @return the {@link StatementBox}
     * @throws QueryBuildException the {@link QueryBuildException}
     */
    StatementBox getStatementBox() throws QueryBuildException;

}