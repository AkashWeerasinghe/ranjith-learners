package com.ranjithlearners.panel;

import com.formdev.flatlaf.FlatClientProperties;
import com.ranjithlearners.connection.MySQL;
import com.ranjithlearners.connection.MySQLProxy;
import com.ranjithlearners.gui.HomeScreen;
import com.ranjithlearners.gui.LoginScreen;
import com.ranjithlearners.util.AESUtil;
import com.ranjithlearners.validation.Validator;
import java.awt.CardLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import raven.toast.Notifications;
import java.util.prefs.Preferences;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LoginPanel extends javax.swing.JPanel {

    private final LoginScreen loginScreen;
    private final CardLayout contentPanelLayout;
    private final JPanel contentPanel;

    public LoginPanel(LoginScreen loginscreen, CardLayout contentpanellayout, JPanel contentpanel) {
        this.loginScreen = loginscreen;
        this.contentPanelLayout = contentpanellayout;
        this.contentPanel = contentpanel;

        initComponents();
        signingBtn.putClientProperty(FlatClientProperties.STYLE,
                "arc:50; hoverBackground:#FFA500; background:#D37F00; foreground:#002449");
        toggleShowPassword.putClientProperty(FlatClientProperties.STYLE,
                "arc:50; hoverBackground:#003366; background:#001A33; foreground:#FFFFFF");

        txtEmail.putClientProperty(FlatClientProperties.STYLE, "arc:50");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "arc:50");
        signingBtn.putClientProperty(FlatClientProperties.STYLE, "arc:50");
        toggleShowPassword.putClientProperty(FlatClientProperties.STYLE, "arc:50");

        Notifications.getInstance().setJFrame(loginscreen);
        Preferences prefs = Preferences.userRoot().node("com/ranjithlearners/login");

        String savedEmail = prefs.get("email", "");
        String savedPassword = prefs.get("password", "");
        boolean rememberMe = prefs.getBoolean("rememberMe", false);
        txtEmail.setText(savedEmail);
        txtPassword.setText(savedPassword);
        chkRememberMe.setSelected(rememberMe);
        txtEmail.setNextFocusableComponent(txtPassword);
        txtPassword.setNextFocusableComponent(signingBtn);
        SwingUtilities.invokeLater(() -> signingBtn.requestFocusInWindow());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toggleShowPassword = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        chkRememberMe = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        signingBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(333, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        toggleShowPassword.setBackground(new java.awt.Color(0, 30, 61));
        toggleShowPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ranjithlearners/img/closed_eye.png"))); // NOI18N
        toggleShowPassword.setBorder(null);
        toggleShowPassword.setBorderPainted(false);
        toggleShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleShowPasswordActionPerformed(evt);
            }
        });
        add(toggleShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 40, 40));

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLabel7.setText("Email Address");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLabel8.setText("Password");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        txtPassword.setBackground(new java.awt.Color(0, 30, 61));
        txtPassword.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 240, 60));

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Sign Up");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, -1, 16));

        txtEmail.setBackground(new java.awt.Color(0, 30, 61));
        txtEmail.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        txtEmail.setToolTipText("Email Address");
        txtEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 240, 60));

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel5.setText("Don't have an Account ?");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 160, -1));

        chkRememberMe.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        chkRememberMe.setForeground(new java.awt.Color(255, 255, 255));
        chkRememberMe.setText("Remember Me");
        chkRememberMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRememberMeActionPerformed(evt);
            }
        });
        add(chkRememberMe, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 150, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ranjithlearners/img/wheel.png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 80));

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 27)); // NOI18N
        jLabel4.setText("Training School");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 220, 30));

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 1, 30)); // NOI18N
        jLabel2.setText("Ranjith Driving");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 220, 50));

        signingBtn.setBackground(new java.awt.Color(211, 127, 0));
        signingBtn.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        signingBtn.setForeground(new java.awt.Color(0, 36, 73));
        signingBtn.setText("Sign In");
        signingBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        signingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signingBtnActionPerformed(evt);
            }
        });
        add(signingBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 260, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ranjithlearners/img/login.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void signingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signingBtnActionPerformed
        String email = txtEmail.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();

        if (!Validator.isEmailValid(email)) {
            return;
        }
        if (!Validator.isPasswordValid(password)) {
            return;
        }

        try {
            MySQL MySQL = new MySQLProxy();
            String encryptedEmail = AESUtil.encrypt(email);
            String encryptedPassword = AESUtil.encrypt(password);

            ResultSet rs = MySQL.executeSearch(
                    "SELECT * FROM `admin` WHERE `email` = ? AND `password` = ?",
                    encryptedEmail,
                    encryptedPassword
            );

            if (rs.next()) {
                if (rs.getInt("status_id") == 1) { // Active
                    if (chkRememberMe.isSelected()) {
                        Preferences prefs = Preferences.userRoot().node("com/ranjithlearners/login");
                        prefs.put("email", txtEmail.getText());
                        prefs.put("password", new String(txtPassword.getPassword()));
                        prefs.putBoolean("rememberMe", true);
                    } else {
                        Preferences prefs = Preferences.userRoot().node("com/ranjithlearners/login");
                        prefs.remove("email");
                        prefs.remove("password");
                        prefs.putBoolean("rememberMe", false);
                    }

                    Notifications.getInstance().show(Notifications.Type.SUCCESS,
                            Notifications.Location.TOP_CENTER,
                            2000,
                            "Login Successful");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                                new HomeScreen().setVisible(true);
                                loginScreen.dispose();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR,
                            Notifications.Location.TOP_CENTER,
                            2000,
                            "OOPS!... Your account is suspended. Please contact super admin");
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_CENTER,
                        2000,
                        "OOPS!... Somthing went wrong! Please check the login credintials.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_signingBtnActionPerformed

    private void chkRememberMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRememberMeActionPerformed

    }//GEN-LAST:event_chkRememberMeActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed

    }//GEN-LAST:event_txtEmailActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.contentPanelLayout.show(contentPanel, "register_panel");
    }//GEN-LAST:event_jLabel6MouseClicked

    private void toggleShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleShowPasswordActionPerformed
        if (toggleShowPassword.isSelected()) {
            txtPassword.setEchoChar((char) 0);
            toggleShowPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ranjithlearners/img/eye.png")));
        } else {
            txtPassword.setEchoChar('\u2022');
            toggleShowPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ranjithlearners/img/closed_eye.png")));
        }
    }//GEN-LAST:event_toggleShowPasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkRememberMe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton signingBtn;
    private javax.swing.JToggleButton toggleShowPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
