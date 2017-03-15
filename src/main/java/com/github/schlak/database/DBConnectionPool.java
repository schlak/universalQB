package com.github.schlak.database;



import com.github.schlak.database.Connector.IConnector;
import com.github.schlak.database.Implementation.MySQL.StatementBuilder.MysqlQueryBuilder;
import com.github.schlak.database.QueryBuilder.Interface.IDBQueryBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Jonas Schlak on 12.10.2016.
 * <p>
 * The {@link DBConnectionPool} the class, that manages the connection pools and is able to
 * handel a pool with multiple connections. Also the database type can change setTable pool to pool.
 * The database type differs on the {@link IConnector} that is passed by creating a new connection pool.
 */
public class DBConnectionPool {

    /**
     * The constant DB_HOST.
     */
    @Deprecated
    public static final String DB_HOST = "localhost";
    /**
     * The constant DATABASE_NAME.
     */
    @Deprecated
    public static final String DATABASE_NAME = "homeManagement";
    /**
     * The constant DEFAULT_INSTANCE.
     */
    public static final String DEFAULT_INSTANCE = "DE.SCHLAK.DATABASE.DB_CONNECTION";
    private static final int DEFAULT_TIMEOUT = 5;
    private static HashMap<String, DBConnectionPool> dbConnectionPoolHashMap = new HashMap<>();
    private IConnector connector;
    private IDBQueryBuilder queryBuilder;
    private Stack<Connection> connectionStack;


    /**
     * The initialisation of the connection pool takes place in here.
     * Also the query builder got referenced because the builder depends on the
     * database and thus on the {@link IConnector} which is database specific. #
     * For example the {@link com.github.schlak.database.Connector.MySQLConnector} will return an iModelCallback of the
     * {@link MysqlQueryBuilder}.
     *
     * @param connector ,{@link IConnector} is used to get the connections setTable the database
     */
    private DBConnectionPool(IConnector connector) {
        this.connector = connector;
        connectionStack = new Stack<>();
        this.queryBuilder = connector.getQueryBuilder(this);
    }

    /**
     * The method is used to get an iModelCallback of the connection pool identified by a string that can be passed
     * as a parameter.
     *
     * @param instantKeyString {@link String} the given string is used to identify and select an                                       iModelCallback that should be returned
     * @return {@link DBConnectionPool} iModelCallback that where selected
     */
    public static DBConnectionPool getInstance(String instantKeyString) {
        return dbConnectionPoolHashMap.get(instantKeyString);

    }

    /**
     * Returns the iModelCallback of the {@link DBConnectionPool} that is created using the connector. The iModelCallback
     * get added to the hash map by using the instantKeyString as the identifying key.
     *
     * @param connector        {@link IConnector} the connector
     * @param instantKeyString {@link String} the instant key string
     * @return the iModelCallback     {@link DBConnectionPool} the connection pool iModelCallback
     */
    public static DBConnectionPool getInstance(IConnector connector, String instantKeyString) {
        if (!dbConnectionPoolHashMap.containsKey(instantKeyString)) {
            dbConnectionPoolHashMap.put(
                    instantKeyString,
                    new DBConnectionPool(connector));
        }
        return dbConnectionPoolHashMap.get(instantKeyString);
    }

    /**
     * Returns the default iModelCallback of the {@link DBConnectionPool}.
     *
     * @return the db connection pool
     */
    public static DBConnectionPool getDefaultInstance() {
        return dbConnectionPoolHashMap.get(DEFAULT_INSTANCE);
    }

    /**
     * Returns the query builder.
     *
     * @return {@link IDBQueryBuilder} the query builder
     */
    public IDBQueryBuilder getQueryBuilder() {
        return this.queryBuilder;
    }

    /**
     * Returns a connection.
     * Before a connection got returned the method checks if the connection is valid;
     *
     * @return {@link Connection} the connection
     */
    public Connection getConnection() {

        if (connectionStack.isEmpty()) {
            this.refillConnections();
        }
        Connection connection = connectionStack.peek();

        while (!this.isConnectionValid(connection)) {

            if (connectionStack.isEmpty()) {
                this.refillConnections();
            }

            connection = connectionStack.peek();
        }

        return connection;

    }

    /**
     * Return the connection to the connection pool.
     *
     * @param connection the connection
     */
    public void returnConnection(Connection connection) {

        if (isConnectionValid(connection)) {
            connectionStack.push(connection);
        }
    }


    /**
     * The method checks if the validity of a given connection.
     *
     * @param connection {@link Connection} that should be validated.
     * @return {@link Boolean} that is returned indicating weather the connection is valid.
     */
    private boolean isConnectionValid(Connection connection) {
        try {
            if (connection.isValid(DBConnectionPool.DEFAULT_TIMEOUT)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * The method refills the internal connection list with new connections setTable
     * the getConnection method of the connector.
     */
    private void refillConnections() {

        int sizeOfHashMap = dbConnectionPoolHashMap.size();

        for (int i = sizeOfHashMap; i < 5; i++) {
            connectionStack.push(this.connector.getConnection());
        }

    }
}
