/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ranjithlearners.gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.ranjithlearners.panel.CertificateCreatorPanel;
import com.ranjithlearners.panel.DashboardPanel;
import com.ranjithlearners.panel.ExamResultsPanel;
import com.ranjithlearners.panel.PaymentDetailsPanel;
import com.ranjithlearners.panel.StudentDetailsPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
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
    private final int COLLAPSED_WIDTH = 60;
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

                // Show/hide components after animation completes
                for (Component comp : sidePanel.getComponents()) {
                    if (comp != toggleButton) {
                        comp.setVisible(!isCollapsed);
                    }
                }
            } else {
                jSplitPane1.setDividerLocation(next);
            }
        });

        // Temporarily hide/show components immediately to prevent layout shift
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
        glassPaneOverlay = new JPanel();
        glassPaneOverlay.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent black
        glassPaneOverlay.setVisible(false);
        setGlassPane(glassPaneOverlay);

    }

    private void loadPanels() {
        if (contentPanelLayout == null && contentPanel.getLayout() instanceof CardLayout) {
            this.contentPanelLayout = (CardLayout) contentPanel.getLayout();
        }

        this.dashboardPanel = new DashboardPanel();
        this.studentDetailsPanel = new StudentDetailsPanel(this);
        this.paymentdetailsPanel = new PaymentDetailsPanel();
        this.examresultsPanel = new ExamResultsPanel();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        sidePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        toggleButton = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JSeparator();
        dashboardBtn = new javax.swing.JButton();
        studentdetailsBtn = new javax.swing.JButton();
        paymentdetailsBtn = new javax.swing.JButton();
        examresultsBtn = new javax.swing.JButton();
        certificatecreatorBtn = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ranjith Driving Training School");

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setToolTipText("");

        sidePanel.setBackground(new java.awt.Color(0, 18, 37));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel1.setText("Options");

        toggleButton.setBackground(new java.awt.Color(211, 127, 0));
        toggleButton.setText("◀");
        toggleButton.setPreferredSize(new java.awt.Dimension(30, 30));
        toggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonActionPerformed(evt);
            }
        });

        dashboardBtn.setBackground(new java.awt.Color(211, 127, 0));
        dashboardBtn.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setMargin(new java.awt.Insets(8, 14, 3, 14));
        dashboardBtn.setPreferredSize(new java.awt.Dimension(118, 35));
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });

        studentdetailsBtn.setBackground(new java.awt.Color(211, 127, 0));
        studentdetailsBtn.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        studentdetailsBtn.setText("Student Details");
        studentdetailsBtn.setMargin(new java.awt.Insets(8, 14, 3, 14));
        studentdetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentdetailsBtnActionPerformed(evt);
            }
        });

        paymentdetailsBtn.setBackground(new java.awt.Color(211, 127, 0));
        paymentdetailsBtn.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        paymentdetailsBtn.setText("Payment Details");
        paymentdetailsBtn.setMargin(new java.awt.Insets(8, 14, 3, 14));
        paymentdetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentdetailsBtnActionPerformed(evt);
            }
        });

        examresultsBtn.setBackground(new java.awt.Color(211, 127, 0));
        examresultsBtn.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        examresultsBtn.setText("Exam Results");
        examresultsBtn.setToolTipText("");
        examresultsBtn.setMargin(new java.awt.Insets(8, 14, 3, 14));
        examresultsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examresultsBtnActionPerformed(evt);
            }
        });

        certificatecreatorBtn.setBackground(new java.awt.Color(211, 127, 0));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(certificatecreatorBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(examresultsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paymentdetailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentdetailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashboardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentdetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(paymentdetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(examresultsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(certificatecreatorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(318, 318, 318))
        );

        jSplitPane1.setLeftComponent(sidePanel);

        mainPanel.setBackground(new java.awt.Color(0, 30, 61));

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ranjith Driving Training School");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setMaximumSize(new java.awt.Dimension(828, 59));
        jLabel2.setMinimumSize(new java.awt.Dimension(828, 59));

        contentPanel.setLayout(new java.awt.CardLayout());
        jScrollPane1.setViewportView(contentPanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(mainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

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
