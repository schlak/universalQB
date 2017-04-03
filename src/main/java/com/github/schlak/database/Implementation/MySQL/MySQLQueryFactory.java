package com.github.schlak.database.Implementation.MySQL;

import com.github.schlak.database.Definition.GeneralObjects.*;
import com.github.schlak.database.Definition.Statements.*;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.*;
import com.github.schlak.database.Implementation.MySQL.StatementBuilder.*;
import com.github.schlak.database.Manager.IDProvider;
import com.github.schlak.database.Definition.IDProviderCallback;
import com.github.schlak.database.Definition.QueryFactory;
import com.github.schlak.database.ObjectRecycler;

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
        return ObjectRecycler.getInstance(MySQLSelectBuilder.class);
    }

    @Override
    public BasicUpdateBuilder getUpdateBuilder() {
        return ObjectRecycler.getInstance(MySQLUpdateBuilder.class);
    }

    @Override
    public BasicInsertBuilder getInsertBuilder() {
        return ObjectRecycler.getInstance(MySQLInsertBuilder.class);
    }

    @Override
    public BasicDeleteBuilder getDeleteBuilder() {
        return ObjectRecycler.getInstance(MySQLDeleteBuilder.class);
    }

    @Override
    public BasicCreateBuilder getCreateBuilder() {
        return ObjectRecycler.getInstance(MySQLCreateBuilder.class);
    }

    @Override
    public ConditionStack getNewConditionStackInstance() {
        return ObjectRecycler.getInstance(MySQLConditionStack.class);
    }

    @Override
    public Column getNewDBColumnInstance() {
        return ObjectRecycler.getInstance(MySQLColumn.class);
    }

    @Override
    public JoinCondition getNewJoinConditionInstance() {
        return ObjectRecycler.getInstance(MySQLJoinCondition.class);
    }

    @Override
    public OrderByDefinition getNewOrderByColumnInstance() {
        return ObjectRecycler.getInstance(MySQLOrderByDefinition.class);
    }

    @Override
    public TableJoinInformation getNewTableJoinInformationInstance() {
        return ObjectRecycler.getInstance(MySQLTableJoinInformation.class);
    }

    @Override
    public ValueAllocation getNewValueAllocationInstance() {
        return ObjectRecycler.getInstance(MySQLValueAllocation.class);
    }

    @Override
    public IDProvider getIdProvider() {
        if (idProvider == null)
            idProvider = idProviderCallback.getProvider();

        return this.idProvider;
    }
}
