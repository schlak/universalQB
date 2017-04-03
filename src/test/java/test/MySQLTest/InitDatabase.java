package test.MySQLTest;

import com.github.schlak.database.ConnectionPool;
import com.github.schlak.database.Implementation.MySQL.MySQLConnector;

/**
 * Created by jonas on 17.03.17.
 */
public class InitDatabase {

    public static void setup() {

        MySQLConnector connector = new MySQLConnector();

        connector.setDatabase(System.getenv("DBNAME"));
        connector.setHost(System.getenv("DBHOST") + ":" + System.getenv("DBPORT"));
        connector.setPassword(System.getenv("DBPASS"));
        connector.setUser(System.getenv("DBUSER"));

        ConnectionPool.setup(connector);

    }


}
