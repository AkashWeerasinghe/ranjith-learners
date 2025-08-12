/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ranjithlearners.dialog;

import com.ranjithlearners.connection.MySQL;
import com.ranjithlearners.connection.MySQLProxy;
import com.ranjithlearners.panel.ExamResultsPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Akash Weerasinghe
 */
public class AddNewExamResult extends javax.swing.JDialog {

    private ExamResultsPanel examResultsPanel;

    public AddNewExamResult(ExamResultsPanel parent) {
        initComponents();
        this.examResultsPanel = parent;
        buttonGroup1.add(writtenRadio);
        buttonGroup1.add(practicalRadio);
        if (writtenRadio.isSelected()) {
            loadWrittenDates();
        } else if (practicalRadio.isSelected()) {
            loadPracticalDates();
        }
    }

    public AddNewExamResult(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    private void loadWrittenDates() {
        try {
            comboDate.removeAllItems();
            MySQL db = new MySQLProxy();
            ResultSet rs = db.executeSearch("SELECT date FROM written_examination");
            comboDate.addItem("Select Exam Date");
            while (rs.next()) {
                comboDate.addItem(rs.getString("date"));
            }
        } catch (Exception e) {
        }

    }

    private void loadPracticalDates() {
        try {
            comboDate.removeAllItems();
            MySQL db = new MySQLProxy();
            ResultSet rs = db.executeSearch("SELECT date FROM practical_examination");
            comboDate.addItem("Select Exam Date");
            while (rs.next()) {
                comboDate.addItem(rs.getString("date"));
            }
        } catch (Exception e) {
        }

    }

    private void addToWrittenResults() {
        String examDate = comboDate.getSelectedItem().toString();
        String status = comboStatus.getSelectedItem().toString().toLowerCase();
        String studentId = txtID.getText().trim();
        String smrtYearcode = txtYC.getText().trim();
        String smrtNo = txtSMRT.getText().trim();

        if (studentId.isEmpty() || smrtYearcode.isEmpty() || smrtNo.isEmpty()
                || examDate.equals("Select Exam Date") || status.equals("select result")) {
            JOptionPane.showMessageDialog(this, "Please fill all fields correctly", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            MySQL db = new MySQLProxy();
            ResultSet rs = db.executeSearch("SELECT * FROM student_has_smrt WHERE student_id = ?", studentId);
            if (!rs.next()) {
                String sql = "INSERT INTO student_has_smrt "
                        + "(student_id, smrt_yearcode, smrt_smrt_no) "
                        + "VALUES (?, ?, ?)";
                db.executeIUD(sql, studentId, smrtYearcode, smrtNo);
            }
            String SQL = "INSERT INTO student_has_written_examination "
                    + "(written_examination_date, status, student_has_smrt_student_id, student_has_smrt_smrt_yearcode, student_has_smrt_smrt_smrt_no) "
                    + "VALUES (?, ?, ?, ?, ?)";
            db.executeIUD(SQL, examDate, status, studentId, smrtYearcode, smrtNo);
            JOptionPane.showMessageDialog(this, "Written exam result added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addToPracticalResults() {
        String examDate = comboDate.getSelectedItem().toString();
        String status = comboStatus.getSelectedItem().toString().toLowerCase();
        String studentId = txtID.getText().trim();
        String smrtYearcode = txtYC.getText().trim();
        String smrtNo = txtSMRT.getText().trim();
        if (studentId.isEmpty() || smrtYearcode.isEmpty() || smrtNo.isEmpty()
                || examDate.equals("Select Exam Date") || status.equals("select result")) {
            JOptionPane.showMessageDialog(this, "Please fill all fields correctly", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            MySQL db = new MySQLProxy();
            ResultSet rs = db.executeSearch("SELECT * FROM student_has_smrt WHERE student_id = ?", studentId);
            if (!rs.next()) {
                String sql = "INSERT INTO student_has_smrt "
                        + "(student_id, smrt_yearcode, smrt_smrt_no) "
                        + "VALUES (?, ?, ?)";
                db.executeIUD(sql, studentId, smrtYearcode, smrtNo);
            }
            String sql = "INSERT INTO student_has_practical_examination "
                    + "(practical_examination_date, status, student_has_smrt_student_id, student_has_smrt_smrt_yearcode, student_has_smrt_smrt_smrt_no) "
                    + "VALUES (?, ?, ?, ?, ?)";
            db.executeIUD(sql, examDate, status, studentId, smrtYearcode, smrtNo);
            JOptionPane.showMessageDialog(this, "Practical exam result added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSMRT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtYC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboDate = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox<>();
        writtenRadio = new javax.swing.JRadioButton();
        practicalRadio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 1, 16)); // NOI18N
        jLabel1.setText("SMRT No:");

        txtSMRT.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 1, 16)); // NOI18N
        jLabel2.setText("Year Code:");

        txtYC.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtYC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYCActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 1, 16)); // NOI18N
        jLabel5.setText("Exam Date:");

        comboDate.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        comboDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Date" }));
        comboDate.setRenderer(null);

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        jLabel7.setText("DSR :");

        txtID.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        txtID.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setBackground(new java.awt.Color(102, 0, 0));
        jButton1.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setMargin(new java.awt.Insets(9, 14, 3, 14));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        addBtn.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        addBtn.setText("Add Student");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 1, 16)); // NOI18N
        jLabel9.setText("Result:");

        comboStatus.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Result", "Pending", "Passed", "Failed" }));
        comboStatus.setRenderer(null);
        comboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusActionPerformed(evt);
            }
        });

        writtenRadio.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        writtenRadio.setSelected(true);
        writtenRadio.setText("Written Exam");
        writtenRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writtenRadioActionPerformed(evt);
            }
        });

        practicalRadio.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        practicalRadio.setText("Practical Exam");
        practicalRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                practicalRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(writtenRadio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(47, 47, 47)
                        .addComponent(practicalRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboDate, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtSMRT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtYC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSMRT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(writtenRadio)
                    .addComponent(practicalRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        if (writtenRadio.isSelected()) {
            addToWrittenResults();
        } else if (practicalRadio.isSelected()) {
            addToPracticalResults();
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtYCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYCActionPerformed

    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboStatusActionPerformed

    private void practicalRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_practicalRadioActionPerformed
        loadPracticalDates();
    }//GEN-LAST:event_practicalRadioActionPerformed

    private void writtenRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writtenRadioActionPerformed
        loadWrittenDates();
    }//GEN-LAST:event_writtenRadioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddNewExamResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewExamResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewExamResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewExamResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddNewExamResult dialog = new AddNewExamResult(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboDate;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton practicalRadio;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtSMRT;
    private javax.swing.JTextField txtYC;
    private javax.swing.JRadioButton writtenRadio;
    // End of variables declaration//GEN-END:variables
}
