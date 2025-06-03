package Com.View.Dashboard;

import Com.Event.MenuEvent;
import Com.View.Form.Form;
import Com.View.Form.Customer.FormCustomer;
import Com.View.Form.Staff.FormStaff;
import Com.View.Form.Info.InforForm;
import Com.View.Form.MainFormDashboard;
import Com.View.Form.Order.FormOrder;
import Com.View.Form.Product.FormProductDetail;
import Com.View.Form.Sell.FormSell;
import Com.View.Form.Statistics.FormStatistics;
import Com.View.Login.Login;
import Com.View.Component.Menu;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Dashboard extends javax.swing.JFrame {
    
    private Menu menu;
    
   public Dashboard() {
    try {
        this.setTitle("Anh Em Quán");
        setIconImage(new ImageIcon(getClass().getResource("/Com/Icon/iconFrame.png")).getImage());
        initComponents();
        getContentPane().setBackground(new Color(63, 109, 217));
        
        // Initialize menu
        menu = new Menu();
        
        // Tạo MenuEvent không dùng lambda để tương thích với Java cũ
        MenuEvent event = new MenuEvent() {
            @Override
            public void menuSelected(int index) {
                handleMenuSelection(index);
            }
        };
        
        menu.initMenu(event);
        menu.setSelected(0);
        
        // THÊM DÒNG NÀY: Thay đổi layout của jPanel1 thành BorderLayout
        jPanel1.setLayout(new java.awt.BorderLayout());
        
        // THÊM DÒNG NÀY: Thêm menu vào phía WEST (bên trái)
        jPanel1.add(menu, java.awt.BorderLayout.WEST);
        
        // THÊM DÒNG NÀY: Di chuyển body sang CENTER
        jPanel1.add(body, java.awt.BorderLayout.CENTER);
        
        // Show default form
        MainFormDashboard mainForm = new MainFormDashboard();
        showForm(mainForm);
        
        // THÊM DÒNG NÀY: Refresh lại giao diện
        jPanel1.revalidate();
        jPanel1.repaint();
        
    } catch (Exception e) {
        System.out.println("Lỗi khi khởi tạo Dashboard: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    
    private void handleMenuSelection(int index) {
        try {
            switch (index) {
                case 0:
                    MainFormDashboard mainFormDashboard = new MainFormDashboard();
                    showForm(mainFormDashboard);
                    break;
                case 1:
                    FormSell formSell = new FormSell();
                    showForm(formSell);
                    break;
                case 2:
                    FormStaff formStaff = new FormStaff();
                    showForm(formStaff);
                    break;
                case 4:
                    FormOrder formOrder = new FormOrder();
                    showForm(formOrder);
                    break;
                case 5:
                    FormProductDetail formProduct = new FormProductDetail();
                    showForm(formProduct);
                    break;
                case 6:
                    FormCustomer formCustomer = new FormCustomer();
                    showForm(formCustomer);
                    break;
                case 7:
                    FormStatistics formStatistics = new FormStatistics();
                    showForm(formStatistics);
                    break;
                case 8:
                    InforForm inforForm = new InforForm();
                    showForm(inforForm);
                    break;
                case 9:
                    int opt = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không ?", "Delete", JOptionPane.YES_NO_OPTION);
                    if (opt == 0) {
                        Login loginFrame = new Login();
                        loginFrame.setVisible(true);
                        loginFrame.pack();
                        loginFrame.setLocationRelativeTo(null);
                        loginFrame.setResizable(false);
                        closeFrame();
                    }
                    break;
                default:
                    showForm(new Form(index + ""));
                    break;
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi xử lý menu selection: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi chuyển form: " + e.getMessage());
        }
    }
    
    private void closeFrame() {
        this.dispose();
    }
    
    private void showForm(Component com) {
        try {
            body.removeAll();
            body.add(com);
            body.revalidate();
            body.repaint();
        } catch (Exception e) {
            System.err.println("Lỗi khi hiển thị form: " + e.getMessage());
            e.printStackTrace();
        }
    }
           
                        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(50, 50, 50));
        jPanel1.setMinimumSize(new java.awt.Dimension(1237, 659));
        jPanel1.setName(""); // NOI18N

        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1194, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}

