package com.github.schlak.database.Connector;

import com.github.schlak.database.Definition.QueryFactory;

import java.sql.Connection;

/**
 * Created by Jonas Schlak on 27.10.2016.
 */
public abstract class Connector {

    private final String URL_PREFIX = "jdbc:mysql://";

    protected String host;
    protected String user;
    protected String password;
    protected String database;
    protected String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    protected abstract String getURL_PREFIX();

    protected abstract String getURL();

    public abstract Connection getConnection();

    public abstract QueryFactory getQueryBuilder();

    public abstract String getDatabaseIdentifier();
}
