package com.github.schlak.database.Connector;

import java.util.HashMap;

/**
 * Created by Jonas Schlak on 27.10.2016.
 */
public abstract class AConnector {

    public static HashMap<String, IConnector> connectorHashMap = new HashMap<>();
    public static String DEFAULT = "DEFAULT";

    protected String URL = "jdbc:mysql://";
    protected String USER;
    protected String PASS;
    protected String DATABASE;

}
