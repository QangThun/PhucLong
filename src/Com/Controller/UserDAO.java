package Com.Controller;

import Com.Model.ModelUser;
import java.awt.HeadlessException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserDAO extends DAO{
    private ArrayList<ModelUser> dsUser;

    public UserDAO() {
        super(); // Call parent constructor first
        
        // check connection with better error handling
        try {
            if (getConn() != null && !getConn().isClosed()) {
                System.out.println("Database connection successful!");
                dsUser = getListUser();
            } else {
                dsUser = new ArrayList<>();
                System.err.println("Không thể khởi tạo UserDAO - connection null hoặc đã đóng");
                // Remove JOptionPane from constructor to avoid EDT issues
                System.err.println("Lỗi kết nối database! Kiểm tra:\n" +
                    "1. SQL Server đã chạy chưa?\n" +
                    "2. Database có tồn tại không?\n" +
                    "3. Username/Password đúng chưa?");
            }
        } catch (Exception e) {
            dsUser = new ArrayList<>();
            System.err.println("Lỗi khởi tạo UserDAO: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ArrayList<ModelUser> getDsUser() {
        if (dsUser == null) {
            dsUser = getListUser();
        }
        return dsUser;
    }
    
    public boolean addUser(ModelUser modelUser){
        if (getConn() == null) {
            JOptionPane.showMessageDialog(null, "Không có kết nối database!");
            return false;
        }
        
        String sql = "INSERT INTO Staff(FullName, Gender, DateStaff, PhoneNumber, AddressStaff, UserName, PassWordStaff, Email, Position, ImageStaff)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setString(1, modelUser.getName());
            ps.setString(2, modelUser.getGender());
            ps.setString(3, modelUser.getDateOfBirth());
            ps.setString(4, modelUser.getPhone());
            ps.setString(5, modelUser.getAddress());
            ps.setString(6, modelUser.getUsername());
            ps.setString(7, modelUser.getPassword());
            ps.setString(8, modelUser.getEmail());
            ps.setString(9, modelUser.getPosition());
            if (modelUser.getImage()==null){
                ps.setNull(10, Types.BLOB);
            }else
                ps.setBytes(10, modelUser.getImage());
            
            boolean result = ps.executeUpdate() > 0;
            if (result) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                // Refresh user list
                dsUser = getListUser();
            }
            return result;
        }catch(HeadlessException | SQLException ex){
            System.err.println("Lỗi addUser: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm user: " + ex.getMessage());
        }
        return false;
    }
    
    public ArrayList<ModelUser> getListUser(){
        ArrayList<ModelUser> userList = new ArrayList<>();
        
        if (getConn() == null) {
            System.err.println("Connection null - không thể lấy danh sách user");
            return userList;
        }
        
        String sql = "SELECT * FROM Staff";
        try{
            PreparedStatement ps = getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ModelUser s = new ModelUser();
                s.setId(rs.getInt("StaffID"));
                s.setName(rs.getString("FullName"));
                s.setGender(rs.getString("Gender"));
                s.setDateOfBirth(rs.getString("DateStaff"));
                s.setPhone(rs.getString("PhoneNumber"));
                s.setAddress(rs.getString("AddressStaff"));
                s.setUsername(rs.getString("UserName"));
                s.setPassword(rs.getString("PassWordStaff"));
                s.setEmail(rs.getString("Email"));
                s.setPosition(rs.getString("Position"));
                s.setImage(rs.getBytes("ImageStaff"));
                userList.add(s);
            }
            System.out.println("Loaded " + userList.size() + " users from database");
        }catch (SQLException ex){
            System.err.println("Lỗi getListUser: " + ex.getMessage());
            ex.printStackTrace();
        }
        return userList;
    }
    
    public boolean updateUser(ModelUser modelUser){
        if (getConn() == null) {
            JOptionPane.showMessageDialog(null, "Không có kết nối database!");
            return false;
        }
        
        try {           
            String sql = "UPDATE Staff SET FullName=?, Gender=?, DateStaff=?, PhoneNumber=?, AddressStaff=?, UserName=?, PassWordStaff=?, Email=?, Position=?, ImageStaff=? WHERE StaffID = ?";
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setString(1, modelUser.getName());
            ps.setString(2, modelUser.getGender());
            ps.setString(3, modelUser.getDateOfBirth());
            ps.setString(4, modelUser.getPhone());
            ps.setString(5, modelUser.getAddress());
            ps.setString(6, modelUser.getUsername());
            ps.setString(7, modelUser.getPassword());
            ps.setString(8, modelUser.getEmail());
            ps.setString(9, modelUser.getPosition());
            ps.setBytes(10, modelUser.getImage());
            ps.setInt(11, modelUser.getId()); // Use parameterized query
            
            boolean result = ps.executeUpdate() > 0;
            if (result) {
                JOptionPane.showMessageDialog(null, "Updated successfully");    
                // Refresh user list
                dsUser = getListUser();
            }
            return result;
        } catch (HeadlessException | SQLException e ) {
            System.err.println("Lỗi updateUser: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "update not successful: " + e.getMessage());      
        }
        return false;
    }
    
    public boolean deleteStaff(String staffID) throws SQLException{
        if (getConn() == null) {
            throw new SQLException("Không có kết nối database!");
        }
        
        try {
            // Turn off auto-commit for transaction
            getConn().setAutoCommit(false);
            
            String sqlDeleteChiTietHoaDon = "DELETE FROM ChiTietHoaDon WHERE MaHD IN " +
                    "(SELECT MaHD FROM HoaDon WHERE StaffID = ?)";
            try (PreparedStatement stmt1 = getConn().prepareStatement(sqlDeleteChiTietHoaDon)) {
                stmt1.setString(1, staffID);
                stmt1.executeUpdate();
            }

            String sqlDeleteHoaDon = "DELETE FROM HoaDon WHERE StaffID = ?";
            try (PreparedStatement stmt2 = getConn().prepareStatement(sqlDeleteHoaDon)) {
                stmt2.setString(1, staffID);
                stmt2.executeUpdate();
            }

            String sqlDeleteStaff = "DELETE FROM Staff WHERE StaffID = ?";
            boolean result;
            try (PreparedStatement stmt3 = getConn().prepareStatement(sqlDeleteStaff)) {
                stmt3.setString(1, staffID);
                result = stmt3.executeUpdate() > 0;
            }
            
            if (result) {
                getConn().commit();
                // Refresh user list
                dsUser = getListUser();
            } else {
                getConn().rollback();
            }
            
            return result;
        } catch (SQLException e) {
            getConn().rollback();
            throw e;
        } finally {
            getConn().setAutoCommit(true);
        }
    }
    
    public ArrayList<ModelUser> searchUser(String name){
        ArrayList<ModelUser> searchResult = new ArrayList<>();
        
        if (getConn() == null) {
            System.err.println("Connection null - không thể search user");
            return searchResult;
        }
        
        // Fix SQL query - remove redundant conditions
        String sql = "SELECT * FROM Staff WHERE FullName LIKE ?";
        try{
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ModelUser s = new ModelUser();
                s.setId(rs.getInt("StaffID"));
                s.setName(rs.getString("FullName"));
                s.setGender(rs.getString("Gender"));
                s.setDateOfBirth(rs.getString("DateStaff"));
                s.setPhone(rs.getString("PhoneNumber"));
                s.setAddress(rs.getString("AddressStaff"));
                s.setUsername(rs.getString("UserName"));
                s.setPassword(rs.getString("PassWordStaff"));
                s.setEmail(rs.getString("Email"));
                s.setPosition(rs.getString("Position"));
                s.setImage(rs.getBytes("ImageStaff"));
                searchResult.add(s);
            }
        }catch (SQLException ex){
            System.err.println("Lỗi searchUser: " + ex.getMessage());
            ex.printStackTrace();
        }
        return searchResult;
    }
}