package com.github.schlak.database.Connector;



import com.github.schlak.database.DBConnectionPool;
import com.github.schlak.database.QueryBuilder.Interface.IDBQueryBuilder;
import com.github.schlak.database.QueryBuilder.Manager.IDProvider;
import com.github.schlak.database.QueryBuilder.MysqlQueryBuilder.MysqlQueryBuilder;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Jonas Schlak on 18.10.2016.
 */
public class MySQLConnector extends AConnector implements IConnector {

    public static String DEFAULT = "MySQLConnector";
    public static HashMap<String, MySQLConnector> connectorHashMap = new HashMap<>();
    private IDProvider idProvider = null;

    private MySQLConnector() {

        StringBuilder sb = new StringBuilder();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //TODO load from config
        this.URL = "jdbc:mysql://";
        this.USER = "jonas";
        this.PASS = "123";
        this.DATABASE = "homeManagement";

        sb.append(this.URL);
        sb.append("localhost:32769");
        sb.append("/");
        sb.append(DATABASE);
        sb.append("?useSSL=false");

        this.URL = sb.toString();

    }

    private MySQLConnector(String host, String user, String pass, String database) {

        StringBuilder sb = new StringBuilder();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.URL = "jdbc:mysql://";
        this.USER = user;
        this.PASS = pass;
        this.DATABASE = database;

        sb.append(this.URL);
        sb.append(host);
        sb.append("/");
        sb.append(database);
        sb.append("?useSSL=false");

        this.URL = sb.toString();
    }

    public static IConnector getConnector(String host, String user, String pass, String database, String hashMapKey) {
        if (!connectorHashMap.containsKey(hashMapKey)) {
            connectorHashMap.put(hashMapKey, new MySQLConnector(host, user, pass, database));
        }
        return connectorHashMap.get(hashMapKey);
    }

    public static IConnector getConnector(String hashMapKey) {
        if (!connectorHashMap.containsKey(hashMapKey)) {
            connectorHashMap.put(hashMapKey, new MySQLConnector());
        }
        return connectorHashMap.get(hashMapKey);
    }

    public static IConnector getConnector() {
        if (!connectorHashMap.containsKey(DEFAULT)) {
            connectorHashMap.put(DEFAULT, new MySQLConnector());
        }
        return connectorHashMap.get(DEFAULT);
    }

    @Override
    public Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(this.URL, this.USER, this.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO Exception Handling
        }

        return connection;
    }

    @Override
    public IDBQueryBuilder getQueryBuilder(DBConnectionPool connectionPool) {
        return new MysqlQueryBuilder(connectionPool, this::getIdProvider);
    }

    @NotNull
    public IDProvider getIdProvider() {
        if (this.idProvider == null)
            this.idProvider = new IDProvider(this::getConnection);

        return this.idProvider;
    }

}
