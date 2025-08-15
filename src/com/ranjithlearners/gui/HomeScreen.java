/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ranjithlearners.gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.ranjithlearners.panel.CertificateCreatorPanel;
import com.ranjithlearners.panel.DashboardPanel;
import com.ranjithlearners.panel.ExamResultsPanel;
import com.ranjithlearners.panel.PaymentDetailsPanel;
import com.ranjithlearners.panel.StudentDetailsPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class HomeScreen extends javax.swing.JFrame {

    public HomeScreen() {
        initComponents();
        loadPanels();
        init();
    }

    private DashboardPanel dashboardPanel;
    private StudentDetailsPanel studentDetailsPanel;
    private PaymentDetailsPanel paymentdetailsPanel;
    private ExamResultsPanel examresultsPanel;
    private CertificateCreatorPanel certificatecreatorPanel;
    private CardLayout contentPanelLayout;
    private JPanel glassPaneOverlay;
    private boolean isCollapsed = false;
    private final int COLLAPSED_WIDTH = 0;
    private final int EXPANDED_WIDTH = 250;

    private void toggleSidePanel() {
        int start = jSplitPane1.getDividerLocation();
        int end = isCollapsed ? EXPANDED_WIDTH : COLLAPSED_WIDTH;
        int step = isCollapsed ? 10 : -10;

        Timer timer = new Timer(5, null);
        timer.addActionListener(e -> {
            int current = jSplitPane1.getDividerLocation();
            int next = current + step;

            if ((step > 0 && next >= end) || (step < 0 && next <= end)) {
                jSplitPane1.setDividerLocation(end);
                timer.stop();

                toggleButton.setText(isCollapsed ? "▶" : "◀");
                for (Component comp : sidePanel.getComponents()) {
                    if (comp != toggleButton) {
                        comp.setVisible(!isCollapsed);
                    }
                }
            } else {
                jSplitPane1.setDividerLocation(next);
            }
        });

        if (!isCollapsed) {
            for (Component comp : sidePanel.getComponents()) {
                if (comp != toggleButton) {
                    comp.setVisible(false);
                }
            }
        }

        timer.start();
        isCollapsed = !isCollapsed;
    }

    private void init() {

        dashboardBtn.putClientProperty(
                FlatClientProperties.STYLE,
                "hoverBackground:#FFA500; borderColor:#001225;"
        );
        studentdetailsBtn.putClientProperty(
                FlatClientProperties.STYLE,
                "hoverBackground:#FFA500; borderColor:#001225;"
        );
        paymentdetailsBtn.putClientProperty(
                FlatClientProperties.STYLE,
                "hoverBackground:#FFA500; borderColor:#001225;"
        );
        examresultsBtn.putClientProperty(
                FlatClientProperties.STYLE,
                "hoverBackground:#FFA500; borderColor:#001225;"
        );
        certificatecreatorBtn.putClientProperty(
                FlatClientProperties.STYLE,
                "hoverBackground:#FFA500; borderColor:#001225;"
        );
    }

    private void loadPanels() {
        if (contentPanelLayout == null && contentPanel.getLayout() instanceof CardLayout) {
            this.contentPanelLayout = (CardLayout) contentPanel.getLayout();
        }

        this.dashboardPanel = new DashboardPanel();
        this.studentDetailsPanel = new StudentDetailsPanel(this);
        this.paymentdetailsPanel = new PaymentDetailsPanel();
        this.examresultsPanel = new ExamResultsPanel(this);
        this.certificatecreatorPanel = new CertificateCreatorPanel();

//        this.dashboardPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
//        this.studentDetailsPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
//        this.paymentdetailsPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
//        this.examresultsPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
//        this.certificatecreatorPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.contentPanel.add(dashboardPanel, "dashboard_panel");
        this.contentPanel.add(studentDetailsPanel, "studentdetails_panel");
        this.contentPanel.add(paymentdetailsPanel, "paymentdetails_panel");
        this.contentPanel.add(examresultsPanel, "examresults_panel");
        this.contentPanel.add(certificatecreatorPanel, "certificatecreator_panel");

        SwingUtilities.updateComponentTreeUI(contentPanel);
    }

    private void changeThemes(boolean dark) {
        if (FlatLaf.isLafDark() != dark) {
            if (!dark) {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatIntelliJLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            } else {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatDarculaLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        darkLightSwitchIcon1 = new com.ranjithlearners.util.DarkLightSwitchIcon();
        jSplitPane1 = new javax.swing.JSplitPane();
        sidePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        dashboardBtn = new javax.swing.JButton();
        studentdetailsBtn = new javax.swing.JButton();
        paymentdetailsBtn = new javax.swing.JButton();
        examresultsBtn = new javax.swing.JButton();
        certificatecreatorBtn = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        toggleButton = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();

        darkLightSwitchIcon1.setCenterSpace(20);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ranjith Driving Training School");

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setToolTipText("");

        sidePanel.setBackground(new java.awt.Color(0, 18, 37));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        jLabel1.setText("Options");

        dashboardBtn.setBackground(new java.awt.Color(0, 18, 37));
        dashboardBtn.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setMargin(new java.awt.Insets(8, 14, 3, 14));
        dashboardBtn.setPreferredSize(new java.awt.Dimension(118, 35));
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });

        studentdetailsBtn.setBackground(new java.awt.Color(0, 18, 37));
        studentdetailsBtn.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        studentdetailsBtn.setText("Student Details");
        studentdetailsBtn.setMargin(new java.awt.Insets(8, 14, 3, 14));
        studentdetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentdetailsBtnActionPerformed(evt);
            }
        });

        paymentdetailsBtn.setBackground(new java.awt.Color(0, 18, 37));
        paymentdetailsBtn.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        paymentdetailsBtn.setText("Payment Details");
        paymentdetailsBtn.setMargin(new java.awt.Insets(8, 14, 3, 14));
        paymentdetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentdetailsBtnActionPerformed(evt);
            }
        });

        examresultsBtn.setBackground(new java.awt.Color(0, 18, 37));
        examresultsBtn.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        examresultsBtn.setText("Exam Results");
        examresultsBtn.setToolTipText("");
        examresultsBtn.setMargin(new java.awt.Insets(8, 14, 3, 14));
        examresultsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examresultsBtnActionPerformed(evt);
            }
        });

        certificatecreatorBtn.setBackground(new java.awt.Color(0, 18, 37));
        certificatecreatorBtn.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        certificatecreatorBtn.setText("Certificate Creator");
        certificatecreatorBtn.setMargin(new java.awt.Insets(8, 14, 3, 14));
        certificatecreatorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                certificatecreatorBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(studentdetailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(examresultsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(certificatecreatorBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(paymentdetailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentdetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paymentdetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(examresultsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(certificatecreatorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(331, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(sidePanel);

        mainPanel.setBackground(new java.awt.Color(0, 30, 61));

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ranjith Driving Training School");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setMaximumSize(new java.awt.Dimension(828, 59));
        jLabel2.setMinimumSize(new java.awt.Dimension(828, 59));

        toggleButton.setBackground(new java.awt.Color(211, 127, 0));
        toggleButton.setText("◀");
        toggleButton.setPreferredSize(new java.awt.Dimension(30, 30));
        toggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonActionPerformed(evt);
            }
        });

        contentPanel.setPreferredSize(new java.awt.Dimension(830, 645));
        contentPanel.setLayout(new java.awt.CardLayout());
        jScrollPane1.setViewportView(contentPanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(toggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(mainPanel);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void toggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonActionPerformed
        toggleSidePanel();
    }//GEN-LAST:event_toggleButtonActionPerformed

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "dashboard_panel");
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void studentdetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentdetailsBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "studentdetails_panel");
    }//GEN-LAST:event_studentdetailsBtnActionPerformed

    private void paymentdetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentdetailsBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "paymentdetails_panel");
    }//GEN-LAST:event_paymentdetailsBtnActionPerformed

    private void examresultsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examresultsBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "examresults_panel");
    }//GEN-LAST:event_examresultsBtnActionPerformed

    private void certificatecreatorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_certificatecreatorBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "certificatecreator_panel");
    }//GEN-LAST:event_certificatecreatorBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FlatDarkLaf.setup();
                new HomeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton certificatecreatorBtn;
    private javax.swing.JPanel contentPanel;
    private com.ranjithlearners.util.DarkLightSwitchIcon darkLightSwitchIcon1;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JButton examresultsBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton paymentdetailsBtn;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JButton studentdetailsBtn;
    private javax.swing.JToggleButton toggleButton;
    // End of variables declaration//GEN-END:variables
}
