package com.schlak.Database.QuerryBuilder.Interface;

import java.sql.Connection;

/**
 * Created by Jonas Schlak on 26.01.17.
 */
public interface IGetConnection {

    /**
     * Returns the {@link Connection} to the Database.
     *
     * @return the {@link Connection}
     */
    public Connection getConnection();

}
