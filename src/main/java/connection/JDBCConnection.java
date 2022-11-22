package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    /**
     * phương thức tạo kết nối tới Database MySQL
     *
     * @return (trả về đối tượng Connection kết nối tới Database MySQL)
     */
    public static Connection getMySQLConnection() {
        final String URL = "jdbc:mysql://localhost:3306/crm_app";
        final String USERNAME = "root";
        final String PASSWORD = "0123456789";

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Not found Driver, " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Not found Database, " + ex.getMessage());
            ex.printStackTrace();
        }
        return connection;
    }
}
