package test.MySQLTest;

import com.github.schlak.database.ConnectionPool;
import com.github.schlak.database.Implementation.MySQL.MySQLConnector;

/**
 * Created by jonas on 17.03.17.
 */
public class InitDatabase {

    public static void setup() {

        MySQLConnector connector = new MySQLConnector();

        connector.setDatabase("test");
        connector.setHost("localhost:32772");
        connector.setPassword("root");
        connector.setUser("root");

        ConnectionPool.setup(connector);

    }


}
