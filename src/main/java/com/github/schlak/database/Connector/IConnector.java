package com.schlak.Database.Connector;

import com.schlak.Database.DBConnectionPool;
import com.schlak.Database.QuerryBuilder.Interface.IDBQueryBuilder;

import java.sql.Connection;

/**
 * Created by Jonas Schlak on 27.10.2016.
 */
public interface IConnector {

    Connection getConnection();

    IDBQueryBuilder getQueryBuilder(DBConnectionPool connectionPool);
}
