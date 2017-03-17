package com.github.schlak.database.Implementation.MySQL;


import com.github.schlak.database.Connector.Connector;
import com.github.schlak.database.Manager.IDProvider;
import com.github.schlak.database.QueryBuilder.Interface.QueryFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Jonas Schlak on 18.10.2016.
 */
public class MySQLConnector extends Connector {

    @Override
    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            connection = DriverManager.getConnection(this.getURL(), this.user, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    @Override
    public QueryFactory getQueryBuilder() {
        return new MySQLQueryFactory(() -> new IDProvider(getDatabaseIdentifier()));
    }

    @Override
    public String getDatabaseIdentifier() {
        return "MYSQL";
    }

    @Override
    protected String getURL_PREFIX() {
        return "jdbc:mysql://";
    }

    @Override
    protected String getURL() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.getURL_PREFIX());
        sb.append(this.host);
        sb.append("/");
        sb.append(this.database);
        sb.append("?useSSL=false");

        return sb.toString();
    }


}
