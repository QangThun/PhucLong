package Com.Controller;

import java.sql.*;


public class DAO {
    private Connection conn;

    public Connection getConn() {
        return conn;
    }
    
    public DAO(){
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=CafeManagement;user=sa;password=814362;encrypt =  false;";
            String username = "sa";
            String password = "123";
            
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối thành công!");
            
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối database: " + e.getMessage());
            conn = null;
        }
    }    
}
    