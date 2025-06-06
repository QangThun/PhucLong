package Com.View.Login;

import Com.Controller.UserDAO;
import Com.Model.ModelUser;
import Com.View.Dashboard.Dashboard;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Login extends javax.swing.JFrame {
    
    private UserDAO udao;
    public static ModelUser user;
    
    public Login() {
        try {
            initComponents();
            
            // Initialize UserDAO with proper error handling
            try {
                udao = new UserDAO();
            } catch (Exception e) {
                System.err.println("Lỗi khởi tạo UserDAO: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
            }
            
            // Safe image loading với try-catch
            try {
                scaleImage();
            } catch (Exception e) {
                System.out.println("Không thể load background image: " + e.getMessage());
            }
            
            try {
                setIconImage(new ImageIcon(getClass().getResource("/Com/Icon/phuclongIcon.png")).getImage());
            } catch (Exception e) {
                System.out.println("Không thể set icon frame: " + e.getMessage());
            }
            
            UserNameTextField.setHint("User....");
            
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo Login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Chỉnh kích thước ảnh theo với JLabel - Version an toàn
    public void scaleImage(){
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Com/Icon/PhucLong.png"));
            if (icon.getIconWidth() > 0) { // Kiểm tra image có load được không
                Image img = icon.getImage();
                Image imgScale = img.getScaledInstance(jLabel6.getWidth(), jLabel6.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(imgScale);
                jLabel6.setIcon(scaledIcon);
            } else {
                System.out.println("Không thể load image: /Com/Icon/phuclongIcon.png");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi scale image: " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        LoginLabel = new javax.swing.JLabel();
        UserNameLabel = new javax.swing.JLabel();
        PasswordLabel = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        Text = new javax.swing.JLabel();
        SignUpButton = new javax.swing.JButton();
        UserNameTextField = new Com.View.Swing.SearchText();
        UserPasswordField = new Com.View.Swing.SearchText();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(0, 102, 102));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel6.setPreferredSize(new java.awt.Dimension(400, 500));

        javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
        Right.setLayout(RightLayout);
        RightLayout.setHorizontalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RightLayout.setVerticalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(Right);
        Right.setBounds(0, 0, 400, 500);

        Left.setBackground(java.awt.SystemColor.control);
        Left.setPreferredSize(new java.awt.Dimension(400, 500));

        LoginLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        LoginLabel.setForeground(new java.awt.Color(0, 102, 102));
        LoginLabel.setText("LOGIN");

        UserNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        UserNameLabel.setText("User Name");

        PasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        PasswordLabel.setText("Password");

        LoginButton.setBackground(new java.awt.Color(0, 102, 102));
        LoginButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(255, 255, 255));
        LoginButton.setText("Login");
        LoginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        Text.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Text.setForeground(new java.awt.Color(0, 51, 255));
        Text.setText("Don't have an account");

        SignUpButton.setText("Sign Up");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });

        UserNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        UserNameTextField.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Com/Icon/7.png"))); // NOI18N
        UserNameTextField.setSelectionColor(new java.awt.Color(0, 0, 51));

        UserPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        UserPasswordField.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Com/Icon/key.png"))); // NOI18N
        UserPasswordField.setSelectionColor(new java.awt.Color(0, 0, 51));

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(LoginLabel)
                        .addGap(144, 144, 144))
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(UserPasswordField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(UserNameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LoginButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, LeftLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Text)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SignUpButton))
                            .addComponent(PasswordLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UserNameLabel, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(53, Short.MAX_VALUE))))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(LoginLabel)
                .addGap(29, 29, 29)
                .addComponent(UserNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(LoginButton)
                .addGap(43, 43, 43)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text)
                    .addComponent(SignUpButton))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jPanel1.add(Left);
        Left.setBounds(400, 0, 400, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Event nút Sign Up
    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
         try {
            SignUp1 signFrame = new SignUp1();
            signFrame.setVisible(true);
            signFrame.pack();
            signFrame.setLocationRelativeTo(null); // Frame Center
            signFrame.setResizable(false);
            this.dispose();
        } catch (Exception e) {
            System.err.println("Lỗi khi mở SignUp form: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể mở form đăng ký: " + e.getMessage());
        }
    }//GEN-LAST:event_SignUpButtonActionPerformed
    
    //Event nút Login
    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
         // Kiểm tra UserDAO đã được khởi tạo chưa
        if (udao == null) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!");
            return;
        }
        
        boolean kt = false;
    
        // Validation input
        if(UserNameTextField.getText().trim().isEmpty() || UserPasswordField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        
        try {
            // Kiểm tra danh sách user có tồn tại không
            if (udao.getListUser() == null || udao.getListUser().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có dữ liệu người dùng!");
                return;
            }
            
            for(ModelUser m : udao.getListUser()){
                if(UserNameTextField.getText().equalsIgnoreCase(m.getUsername()) && 
                   UserPasswordField.getText().equalsIgnoreCase(m.getPassword())){
                    user = m;
                    kt = true;
                    break; // Thoát khỏi loop khi tìm thấy
                }   
            }
            
            if(kt) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
                
                // Use SwingUtilities.invokeLater for better thread safety
                SwingUtilities.invokeLater(() -> {
                    try {
                        Dashboard testDashboard = new Dashboard();
                        testDashboard.setVisible(true);
                        testDashboard.pack();
                        testDashboard.setLocationRelativeTo(null);
                        this.dispose();
                    } catch (ExceptionInInitializerError e) {
                        System.err.println("ExceptionInInitializerError in Dashboard: " + e.getMessage());
                        e.printStackTrace();
                        if (e.getCause() != null) {
                            System.err.println("Caused by: " + e.getCause().getMessage());
                            e.getCause().printStackTrace();
                        }
                        JOptionPane.showMessageDialog(this, "Lỗi khởi tạo Dashboard. Vui lòng kiểm tra console để biết chi tiết.");
                    } catch (Exception e) {
                        System.err.println("Lỗi khi khởi tạo Dashboard: " + e.getMessage());
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Lỗi khi khởi tạo Dashboard: " + e.getMessage());
                    }
                });
                
            } else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!");
            }
            
        } catch (Exception e) {
            System.err.println("Lỗi trong quá trình đăng nhập: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi đăng nhập: " + e.getMessage());
        }  
    }//GEN-LAST:event_LoginButtonActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JButton LoginButton;
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JPanel Right;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLabel Text;
    private javax.swing.JLabel UserNameLabel;
    private Com.View.Swing.SearchText UserNameTextField;
    private Com.View.Swing.SearchText UserPasswordField;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
