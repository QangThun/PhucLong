package Com.Controller;

import java.sql.*;

public class DAO {

    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    public DAO() {
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=CafeManagement;encrypt=false";
        String user = "sa";
        String pass = "814362";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Kết nối thành công!");
        } catch (Exception e) {
            System.err.println("Lỗi kết nối database: " + e.getMessage());
        }
    }
}
