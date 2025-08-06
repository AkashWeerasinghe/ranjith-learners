/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.ranjithlearners.panel;

import com.ranjithlearners.connection.MySQL;
import com.ranjithlearners.connection.MySQLProxy;
import com.ranjithlearners.dialog.AddNewStudent;
import com.ranjithlearners.dialog.SearchDialog;
import com.ranjithlearners.gui.HomeScreen;
import com.ranjithlearners.util.AESUtil;
import com.ranjithlearners.util.BackgroundPanel;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Akash Weerasinghe
 */
public class StudentDetailsPanel extends BackgroundPanel {

    /**
     * Creates new form StudentDetailsPanel
     */
    private final HomeScreen homeScreen;

    public StudentDetailsPanel(HomeScreen parent) {
        initComponents();
        this.homeScreen = parent;
        loadTableData();
        setupReportClickListener();
    }

    private void loadTableData() {
        System.out.println("✅ loadTableData() method called");
        try {
            MySQL MySQL = new MySQLProxy();
            ResultSet rs = MySQL.executeSearch("SELECT "
                    + "s.id AS student_id, "
                    + "s.name AS student_name, "
                    + "s.nic AS student_nic, "
                    + "s.mobile AS student_mobile, "
                    + "s.dob AS student_dob, "
                    + "s.medical_no AS student_med_no, "
                    + "s.address AS student_address, "
                    + "s.created_at AS student_added_date, "
                    + "shc.course_code AS student_course_code "
                    + "FROM student s "
                    + "JOIN student_has_course shc ON s.id = shc.student_id");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<Object> data = new Vector<>();
                data.add(rs.getString("student_id"));
                try {
                    data.add(AESUtil.decrypt(rs.getString("student_name")));
                    data.add(AESUtil.decrypt(rs.getString("student_nic")));
                    data.add(AESUtil.decrypt(rs.getString("student_mobile")));
                    data.add(rs.getString("student_course_code"));
                    data.add(rs.getString("student_dob"));
                    String medNoEnc = rs.getString("student_med_no");
                    data.add(medNoEnc == null ? "" : AESUtil.decrypt(medNoEnc));
                    data.add(AESUtil.decrypt(rs.getString("student_address")));
                } catch (Exception e) {
                    e.printStackTrace();
                    // In case of decrypt error, fallback to raw values
                    data.add(rs.getString("student_name"));
                    data.add(rs.getString("student_nic"));
                    data.add(rs.getString("student_mobile"));
                    data.add(rs.getString("student_course_code"));
                    data.add(rs.getString("student_dob"));
                    data.add(rs.getString("student_med_no"));
                    data.add(rs.getString("student_address"));
                }
                data.add(rs.getString("student_added_date"));
                data.add("View");
                dtm.addRow(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "SQL Error: " + e.getMessage());
        }
    }

    private String getValue(JTable table, int row, int col) {
        Object value = table.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    private void setupReportClickListener() {
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                try {

                    int selectedRow = jTable1.getSelectedRow();

                    if (selectedRow == -1) {
                        return;
                    }

                    String dsr = getValue(jTable1, selectedRow, 0);
                    String name = getValue(jTable1, selectedRow, 1);
                    String nic = getValue(jTable1, selectedRow, 2);
                    String mobile = getValue(jTable1, selectedRow, 3);
                    String vehicle_classes = getValue(jTable1, selectedRow, 4);
                    String dob = getValue(jTable1, selectedRow, 5);
                    String med_no = getValue(jTable1, selectedRow, 6);
                    String address = getValue(jTable1, selectedRow, 7);
                    String created_at = getValue(jTable1, selectedRow, 8);

                    InputStream filePath = getClass()
                            .getClassLoader()
                            .getResourceAsStream("com/ranjithlearners/report/ranjith_learners_report.jasper");

                    HashMap<String, Object> parameters = new HashMap<>();
                    parameters.put("DSR", dsr);
                    parameters.put("Name", name);
                    parameters.put("NIC", nic);
                    parameters.put("Mobile_No", mobile);
                    parameters.put("Vehicle_Classes", vehicle_classes);
                    parameters.put("Date_of_Birth", dob);
                    parameters.put("Medical_No", med_no);
                    parameters.put("Address", address);
                    parameters.put("Added_Date", created_at);

                    JasperPrint fillreport = JasperFillManager.fillReport(filePath, parameters, new JREmptyDataSource());
                    JasperViewer.viewReport(fillreport);
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }

        });

        jTable1.getColumnModel().getColumn(9).setCellRenderer(new LinkRenderer());
    }

    private class LinkRenderer extends JLabel implements TableCellRenderer {

        public LinkRenderer() {
            setForeground(Color.BLUE);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText("<html><u>" + value.toString() + "</u></html>");
            return this;
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

        jLabel1 = new javax.swing.JLabel();
        addStudent = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 153));
        setMinimumSize(new java.awt.Dimension(830, 645));
        setPreferredSize(new java.awt.Dimension(830, 645));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student Details");

        addStudent.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        addStudent.setText("Add new Student");
        addStudent.setMargin(new java.awt.Insets(5, 14, 3, 14));
        addStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentActionPerformed(evt);
            }
        });

        refreshBtn.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        refreshBtn.setText("Refresh");
        refreshBtn.setMargin(new java.awt.Insets(5, 14, 3, 14));
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jTextField1.setMargin(new java.awt.Insets(7, 6, 2, 6));
        jTextField1.setPreferredSize(new java.awt.Dimension(64, 28));

        jButton1.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jButton1.setText("Search");
        jButton1.setMargin(new java.awt.Insets(7, 14, 3, 14));
        jButton1.setPreferredSize(new java.awt.Dimension(75, 28));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DSR", "Name", "NIC", "Mobile_No", "Vehicle_Classes", "Date_of_Birth", "Medical_No", "Address", "Added_Date", "Report"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jButton2.setText("Edit Student");
        jButton2.setMargin(new java.awt.Insets(5, 14, 3, 14));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refreshBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addStudent, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshBtn)
                            .addComponent(addStudent)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentActionPerformed
        AddNewStudent addStudent = new AddNewStudent(homeScreen, true);
        addStudent.setLocationRelativeTo(homeScreen);
        addStudent.setVisible(true);
    }//GEN-LAST:event_addStudentActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        loadTableData();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SearchDialog searchDialog = new SearchDialog(homeScreen, true);
        searchDialog.setLocationRelativeTo(this);
        searchDialog.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudent;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton refreshBtn;
    // End of variables declaration//GEN-END:variables
}
