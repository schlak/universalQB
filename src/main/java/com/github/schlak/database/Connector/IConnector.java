package com.github.schlak.database.Connector;

import com.github.schlak.database.DBConnectionPool;
import com.github.schlak.database.QueryBuilder.Interface.IDBQueryBuilder;

import java.sql.Connection;

/**
 * Created by Jonas Schlak on 27.10.2016.
 */
public interface IConnector {

    Connection getConnection();

    IDBQueryBuilder getQueryBuilder(DBConnectionPool connectionPool);
}
