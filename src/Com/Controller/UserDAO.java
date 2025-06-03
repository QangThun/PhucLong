package Com.Controller;

import Com.Model.ModelUser;
import java.awt.HeadlessException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserDAO extends DAO{
    ArrayList<ModelUser> dsUser;

    public UserDAO() {
        // check connection with better error handling
        try {
            if (getConn() != null) {
                System.out.println("Database connection successful!");
                dsUser = getListUser();
            } else {
                dsUser = new ArrayList<>();
                System.err.println("Không thể khởi tạo UserDAO - connection null");
                JOptionPane.showMessageDialog(null, "Lỗi kết nối database! Kiểm tra:\n" +
                    "1. MySQL Server đã chạy chưa?\n" +
                    "2. Database có tồn tại không?\n" +
                    "3. Username/Password đúng chưa?");
            }
        } catch (Exception e) {
            dsUser = new ArrayList<>();
            System.err.println("Lỗi khởi tạo UserDAO: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Lỗi khởi tạo UserDAO: " + e.getMessage());
        }
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
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            return ps.executeUpdate() > 0;
        }catch(HeadlessException | SQLException ex){
            System.err.println("Lỗi addUser: " + ex.getMessage());
        }
        return false;
    }
    
    public ArrayList<ModelUser> getListUser(){
        ArrayList<ModelUser> dsUser = new ArrayList<>();
        
        if (getConn() == null) {
            System.err.println("Connection null - không thể lấy danh sách user");
            return dsUser;
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
                // FIX: Đổi từ "PasswordStaff" thành "PassWordStaff" cho khớp với INSERT
                s.setPassword(rs.getString("PassWordStaff"));
                s.setEmail(rs.getString("Email"));
                s.setPosition(rs.getString("Position"));
                s.setImage(rs.getBytes("ImageStaff"));
                dsUser.add(s);
            }
            System.out.println("Loaded " + dsUser.size() + " users from database");
        }catch (SQLException ex){
            System.err.println("Lỗi getListUser: " + ex.getMessage());
            ex.printStackTrace();
        }
        return dsUser;
    }
    
    public boolean updateUser(ModelUser modelUser){
        if (getConn() == null) {
            JOptionPane.showMessageDialog(null, "Không có kết nối database!");
            return false;
        }
        
        try {           
            PreparedStatement ps = getConn().prepareStatement("UPDATE Staff SET FullName=?, Gender=?, DateStaff=?, PhoneNumber=?, AddressStaff=?, UserName=?, PassWordStaff=?, Email=?, Position=?, ImageStaff=? WHERE StaffID = "+modelUser.getId());
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
            ps.execute();

            JOptionPane.showMessageDialog(null, "Updated");    
            return true;
        } catch (HeadlessException | SQLException e ) {
            JOptionPane.showMessageDialog(null, "update not successful: " + e.getMessage());      
        }
        return false;
    }
    
    public boolean deleteStaff(String staffID) throws SQLException{
        if (getConn() == null) {
            throw new SQLException("Không có kết nối database!");
        }
        
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
        try (PreparedStatement stmt3 = getConn().prepareStatement(sqlDeleteStaff)) {
            stmt3.setString(1, staffID);
            return stmt3.executeUpdate()>0;
        }
    }
    
    public ArrayList<ModelUser> searchUser(String name){
        ArrayList<ModelUser> dsUser = new ArrayList<>();
        
        if (getConn() == null) {
            System.err.println("Connection null - không thể search user");
            return dsUser;
        }
        
        String sql = "SELECT * FROM Staff where (FullName like N'%"+name+"%') or (FullName like N'"+name+"%') or (FullName like N'%"+name+"')";
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
                dsUser.add(s);
            }
        }catch (SQLException ex){
            System.err.println("Lỗi searchUser: " + ex.getMessage());
        }
        return dsUser;
    }
}