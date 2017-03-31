package test;

import com.mysql.jdbc.ConnectionImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jonas on 31.03.17.
 */
public class DemoConnection extends ConnectionImpl {


    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new DemoPreparedStatement();
    }
}
