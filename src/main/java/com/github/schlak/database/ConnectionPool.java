package com.github.schlak.database;


import com.github.schlak.database.Connector.Connector;
import com.github.schlak.database.Implementation.MySQL.MySQLConnector;
import com.github.schlak.database.Implementation.MySQL.MySQLQueryFactory;
import com.github.schlak.database.Definition.QueryFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Jonas Schlak on 12.10.2016.
 * <p>
 * The {@link ConnectionPool} the class, that manages the connection pools and is able to
 * handel a pool with multiple connections. Also the database type can change setTableName pool to pool.
 * The database type differs on the {@link Connector} that is passed by creating a new connection pool.
 */
public class ConnectionPool {

    /**
     * The constant DEFAULT_INSTANCE.
     */
    private static String DEFAULT_INSTANCE;
    private static final int DEFAULT_TIMEOUT = 5;
    private static HashMap<String, ConnectionPool> connectionPoolHashMap = new HashMap<>();
    private Connector connector;
    private QueryFactory queryBuilder;
    private Stack<Connection> connectionStack;


    /**
     * The method is used to get an iModelCallback of the connection pool identified by a string that can be passed
     * as a parameter.
     *
     * @param databaseIdentifier {@link String} the given string is used to identify and select an iModelCallback that should be returned
     * @return {@link ConnectionPool} iModelCallback that where selected
     */
    public static ConnectionPool getInstance(String databaseIdentifier) {
        return connectionPoolHashMap.get(databaseIdentifier);

    }

    /**
     * Returns the iModelCallback of the {@link ConnectionPool} that is created using the connector. The iModelCallback
     * get added to the hash map by using the instantKeyString as the identifying key.
     *
     * @param connector        {@link Connector} the connector
     */
    public static void setup(Connector connector) {
        if (connectionPoolHashMap.size() == 0)
            ConnectionPool.DEFAULT_INSTANCE = connector.getDatabaseIdentifier();

        if (!connectionPoolHashMap.containsKey(connector.getDatabaseIdentifier())) {
            connectionPoolHashMap.put(
                    connector.getDatabaseIdentifier(),
                    new ConnectionPool(connector));
        }
    }

    /**
     * Returns the iModelCallback of the {@link ConnectionPool} that is created using the connector. The iModelCallback
     * get added to the hash map by using the instantKeyString as the identifying key.
     *
     * @param connector {@link Connector} the connector
     */
    public static void setupDefault(Connector connector) {

        ConnectionPool.DEFAULT_INSTANCE = connector.getDatabaseIdentifier();

        ConnectionPool.setup(connector);

    }

    /**
     * Returns the default iModelCallback of the {@link ConnectionPool}.
     *
     * @return the db connection pool
     */
    public static ConnectionPool getDefaultInstance() {
        return connectionPoolHashMap.get(DEFAULT_INSTANCE);
    }

    /**
     * The initialisation of the connection pool takes place in here.
     * Also the query builder got referenced because the builder depends on the
     * database and thus on the {@link Connector} which is database specific. #
     * For example the {@link MySQLConnector} will return an iModelCallback of the
     * {@link MySQLQueryFactory}.
     *
     * @param connector ,{@link Connector} is used to get the connections setTableName the database
     */
    private ConnectionPool(Connector connector) {
        this.connector = connector;
        connectionStack = new Stack<>();
        this.queryBuilder = connector.getQueryBuilder();
    }

    /**
     * Returns the query builder.
     *
     * @return {@link QueryFactory} the query builder
     */
    public QueryFactory getQueryFactory() {
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
            if (connection.isValid(ConnectionPool.DEFAULT_TIMEOUT)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * The method refills the internal connection list with new connections setTableName
     * the getConnection method of the connector.
     */
    private void refillConnections() {

        int sizeOfHashMap = connectionPoolHashMap.size();

        for (int i = sizeOfHashMap; i < 5; i++) {
            connectionStack.push(this.connector.getConnection());
        }

    }
}
