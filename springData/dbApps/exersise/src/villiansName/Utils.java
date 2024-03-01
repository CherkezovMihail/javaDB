package villiansName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum Utils {
    ;
    static Connection getSqlConnection() throws SQLException {

        final Properties prop = new Properties();
        prop.setProperty(Constants.USER_KEY, Constants.USER_VALUE);
        prop.setProperty(Constants.PASSWORD_KEY, Constants.PASSWORD_VALUE);

        return DriverManager.getConnection(Constants.JDBC_MYSQL_URL, prop);
    }
}
