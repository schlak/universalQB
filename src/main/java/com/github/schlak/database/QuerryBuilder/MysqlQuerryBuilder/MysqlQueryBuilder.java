package com.github.schlak.database.QuerryBuilder.MysqlQuerryBuilder;

import com.github.schlak.database.DBConnectionPool;
import com.github.schlak.database.QuerryBuilder.*;
import com.github.schlak.database.QuerryBuilder.Interface.IDBQueryBuilder;
import com.github.schlak.database.QuerryBuilder.Interface.IDProviderCallback;
import com.github.schlak.database.QuerryBuilder.Manager.IDProvider;
import com.github.schlak.database.QuerryBuilder.MysqlQuerryBuilder.Others.*;
import com.github.schlak.database.QuerryBuilder.Others.*;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlQueryBuilder implements IDBQueryBuilder {

    /**
     * The Connection pool.
     */
    DBConnectionPool connectionPool;
    private IDProvider idProvider = null;
    private IDProviderCallback idProviderCallback;

    /**
     * Instantiates a new {@link MysqlQueryBuilder}.
     *
     * @param connectionPool     the connection pool
     * @param idProviderCallback the id provider callback
     */
    public MysqlQueryBuilder(DBConnectionPool connectionPool, IDProviderCallback idProviderCallback) {
        this.idProviderCallback = idProviderCallback;
        this.connectionPool = connectionPool;
    }

    @Override
    public ADBSelectBuilder getSelectBuilder() {
        return new MysqlSelectBuilder();
    }

    @Override
    public ADBUpdateBuilder getUpdateBuilder() {
        return new MysqlUpdateBuilder();
    }

    @Override
    public ADBInsertBuilder getInsertBuilder() {
        return new MysqlInsertBuilder();
    }

    @Override
    public ADBDeleteBuilder getDeleteBuilder() {
        return new MysqlDeleteBuilder();
    }

    @Override
    public AConditionStack getNewConditionStackInstance() {
        return new MysqlConditionStack();
    }

    @Override
    public ADBColumn getNewDBColumnInstance() {
        return new MysqlColumn();
    }

    @Override
    public AJoinCondition getNewJoinConditionInstance() {
        return new MysqlJoinCondition();
    }

    @Override
    public AOrderByColumn getNewOrderByColumnInstance() {
        return new MysqlOrderByColumn();
    }

    @Override
    public ATableJoinInformation getNewTableJoinInformationInstance() {
        return new MysqlTableJoinInformation();
    }

    @Override
    public AValueAllocation getNewValueAllocationInstance() {
        return new MysqlValueAllocation();
    }

    @Override
    public ADBCreateBuilder getCreateBuilder() {
        return new MysqlCreateBuilder();
    }

    @Override
    public IDProvider getIdProvider() {
        if (idProvider == null)
            idProvider = idProviderCallback.getProvider();

        return this.idProvider;
    }
}
