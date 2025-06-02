package Com.Controller;

import java.sql.*;

public class DAO {
    private Connection conn;

    public Connection getConn() {
        return conn;
    }
    
    public DAO(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=CafeManagement;user=sa;password=814362;encrypt=false;"
            );
            System.out.println("Kết nối thành công!");
        }catch(Exception ex){
            System.err.println("Lỗi kết nối database: " + ex.getMessage());
            conn = null;
        }
    }    
}