package com.github.schlak.database.Implementation.MySQL;

import com.github.schlak.database.Definition.GeneralObjects.*;
import com.github.schlak.database.Definition.Statements.*;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.*;
import com.github.schlak.database.Implementation.MySQL.StatementBuilder.*;
import com.github.schlak.database.Manager.IDProvider;
import com.github.schlak.database.Definition.IDProviderCallback;
import com.github.schlak.database.Definition.QueryFactory;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLQueryFactory implements QueryFactory {

    /**
     * The Connection pool.
     */
    private IDProvider idProvider = null;
    private IDProviderCallback idProviderCallback;

    /**
     * Instantiates a new {@link MySQLQueryFactory}.
     *
     * @param idProviderCallback the id provider callback
     */
    public MySQLQueryFactory(IDProviderCallback idProviderCallback) {
        this.idProviderCallback = idProviderCallback;
    }

    @Override
    public BasicSelectBuilder getSelectBuilder() {
        return new MySQLSelectBuilder();
    }

    @Override
    public BasicUpdateBuilder getUpdateBuilder() {
        return new MySQLUpdateBuilder();
    }

    @Override
    public BasicInsertBuilder getInsertBuilder() {
        return new MySQLInsertBuilder();
    }

    @Override
    public BasicDeleteBuilder getDeleteBuilder() {
        return new MySQLDeleteBuilder();
    }

    @Override
    public BasicCreateBuilder getCreateBuilder() {
        return new MySQLCreateBuilder();
    }

    @Override
    public ConditionStack getNewConditionStackInstance() {
        return new MySQLConditionStack();
    }

    @Override
    public Column getNewDBColumnInstance() {
        return new MySQLColumn();
    }

    @Override
    public JoinCondition getNewJoinConditionInstance() {
        return new MySQLJoinCondition();
    }

    @Override
    public OrderByDefinition getNewOrderByColumnInstance() {
        return new MySQLOrderByDefinition();
    }

    @Override
    public TableJoinInformation getNewTableJoinInformationInstance() {
        return new MySQLTableJoinInformation();
    }

    @Override
    public ValueAllocation getNewValueAllocationInstance() {
        return new MySQLValueAllocation();
    }

    @Override
    public IDProvider getIdProvider() {
        if (idProvider == null)
            idProvider = idProviderCallback.getProvider();

        return this.idProvider;
    }
}
