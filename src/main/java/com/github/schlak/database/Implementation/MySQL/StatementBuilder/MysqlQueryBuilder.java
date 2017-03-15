package com.github.schlak.database.Implementation.MySQL.StatementBuilder;

import com.github.schlak.database.DBConnectionPool;
import com.github.schlak.database.Definition.GeneralObjects.*;
import com.github.schlak.database.Definition.Statements.*;
import com.github.schlak.database.Implementation.MySQL.GeneralObjects.*;
import com.github.schlak.database.Manager.IDProvider;
import com.github.schlak.database.QueryBuilder.Interface.IDBQueryBuilder;
import com.github.schlak.database.QueryBuilder.Interface.IDProviderCallback;

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
    public BasicSelect getSelectBuilder() {
        return new MysqlSelect();
    }

    @Override
    public BasicUpdate getUpdateBuilder() {
        return new MysqlUpdate();
    }

    @Override
    public BasicInsert getInsertBuilder() {
        return new MysqlInsert();
    }

    @Override
    public BasicDelete getDeleteBuilder() {
        return new MysqlDelete();
    }

    @Override
    public ConditionStack getNewConditionStackInstance() {
        return new MysqlConditionStack();
    }

    @Override
    public Column getNewDBColumnInstance() {
        return new MysqlColumn();
    }

    @Override
    public JoinCondition getNewJoinConditionInstance() {
        return new MysqlJoinCondition();
    }

    @Override
    public OrderByDefinition getNewOrderByColumnInstance() {
        return new MysqlOrderByDefinition();
    }

    @Override
    public TableJoinInformation getNewTableJoinInformationInstance() {
        return new MysqlTableJoinInformation();
    }

    @Override
    public ValueAllocation getNewValueAllocationInstance() {
        return new MysqlValueAllocation();
    }

    @Override
    public BasicCreate getCreateBuilder() {
        return new MysqlCreate();
    }

    @Override
    public IDProvider getIdProvider() {
        if (idProvider == null)
            idProvider = idProviderCallback.getProvider();

        return this.idProvider;
    }
}
