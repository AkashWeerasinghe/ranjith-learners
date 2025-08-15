/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.ranjithlearners.panel;

import com.formdev.flatlaf.FlatClientProperties;
import com.ranjithlearners.connection.MySQL;
import com.ranjithlearners.connection.MySQLProxy;
import com.ranjithlearners.util.BackgroundPanel;
import com.toedter.calendar.JDayChooser;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Akash Weerasinghe
 */
public class DashboardPanel extends BackgroundPanel {

    private final MySQL MySQL = new MySQLProxy();
    private Set<Date> upcomingExamDates = new HashSet<>();

    public DashboardPanel() {
        initComponents();
        jCalendar1.getMonthChooser().addPropertyChangeListener("month", e -> highlightCalendarDates());
        jCalendar1.getYearChooser().addPropertyChangeListener("year", e -> highlightCalendarDates());
//        styleCards();
        loadDashboardData();
        loadUpcomingExamDates();
        highlightCalendarDates();
        refreshBtn.putClientProperty(
                FlatClientProperties.STYLE,
                "arc:50; borderColor:#001E3D; borderWidth:2; hoverBackground:#FFA500; foreground:#001E3D"
        );
    }

    private void addHoverEffect(javax.swing.JPanel panel, Color normal, Color hover) {
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                panel.setBackground(hover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                panel.setBackground(normal);
            }
        });
    }

    private void loadUpcomingExamDates() {
        upcomingExamDates.clear();
        try {
            String sql = """
            SELECT date FROM written_examination WHERE date >= CURDATE()
            UNION
            SELECT date FROM practical_examination WHERE date >= CURDATE();
        """;
            ResultSet rs = MySQL.executeSearch(sql);
            while (rs.next()) {
                // Normalize date (remove time part)
                java.sql.Date sqlDate = rs.getDate("date");
                upcomingExamDates.add(sqlDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void highlightCalendarDates() {
    JDayChooser dayChooser = jCalendar1.getDayChooser();

    Calendar displayedMonth = Calendar.getInstance();
    displayedMonth.setTime(jCalendar1.getDate());

    for (Component comp : dayChooser.getDayPanel().getComponents()) {
        if (comp instanceof JButton btn) {

            String text = btn.getText();
            if (text != null && !text.isBlank()) {
                try {
                    int day = Integer.parseInt(text);

                    Calendar thisDay = (Calendar) displayedMonth.clone();
                    thisDay.set(Calendar.DAY_OF_MONTH, day);

                    boolean match = upcomingExamDates.stream().anyMatch(d -> {
                        Calendar exam = Calendar.getInstance();
                        exam.setTime(d);
                        return exam.get(Calendar.YEAR) == thisDay.get(Calendar.YEAR) &&
                               exam.get(Calendar.MONTH) == thisDay.get(Calendar.MONTH) &&
                               exam.get(Calendar.DAY_OF_MONTH) == thisDay.get(Calendar.DAY_OF_MONTH);
                    });

                    if (match) {
                        btn.setBackground(Color.ORANGE);
                        btn.setForeground(Color.RED);
                        btn.setOpaque(true);
                        btn.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
    }
}


    private void styleCards() {
        Color normalColor = new Color(22, 22, 22, 255);
        Color hoverColor = new Color(22, 22, 22, 200);

        addHoverEffect(todayPanel, normalColor, hoverColor);

        addHoverEffect(totalStudentPanel, normalColor, hoverColor);

        addHoverEffect(upcomingExamPanel, normalColor, hoverColor);
    }

    private void loadDashboardData() {
        try {

            String sql = """
            SELECT
                (SELECT COUNT(*) 
                 FROM student 
                 WHERE DATE(created_at) = CURDATE()) AS new_students_today,
                (SELECT COUNT(*) 
                 FROM student_has_written_examination shwe
                 JOIN student_has_smrt shs 
                   ON shwe.student_has_smrt_student_id = shs.student_id
                 WHERE shwe.written_examination_date = CURDATE()) AS today_written_exams,
                (SELECT COUNT(*) 
                 FROM student_has_practical_examination shpe
                 JOIN student_has_smrt shs 
                   ON shpe.student_has_smrt_student_id = shs.student_id
                 WHERE shpe.practical_examination_date = CURDATE()) AS today_practical_exams,
                (SELECT COUNT(*) 
                 FROM student) AS total_students,
                (SELECT MIN(date) 
                 FROM written_examination 
                 WHERE date >= CURDATE()) AS next_written_exam,
            
                (SELECT MIN(date) 
                 FROM practical_examination 
                 WHERE date >= CURDATE()) AS next_practical_exam;
            """;

            ResultSet rs = MySQL.executeSearch(sql);
            if (rs.next()) {
                totalStudentLable.setText(String.valueOf(rs.getInt("total_students")));
                newStudentLable.setText(String.valueOf(rs.getInt("new_students_today")));
                writtenLable.setText(String.valueOf(rs.getInt("today_written_exams")));
                practicalLable.setText(String.valueOf(rs.getInt("today_practical_exams")));
                Date upDate = rs.getDate("next_written_exam");
                upcomingExamDateLable.setText(upDate != null ? upDate.toString() : "No upcoming exams");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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

        glassIcon1 = new raven.glassmorphism.GlassIcon();
        jLabel1 = new javax.swing.JLabel();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        todayPanel = new raven.crazypanel.CrazyPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        newStudentLable = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        writtenLable = new javax.swing.JLabel();
        practicalLable = new javax.swing.JLabel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        totalStudentPanel = new raven.crazypanel.CrazyPanel();
        jLabel6 = new javax.swing.JLabel();
        totalStudentLable = new javax.swing.JLabel();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        upcomingExamPanel = new raven.crazypanel.CrazyPanel();
        jLabel8 = new javax.swing.JLabel();
        upcomingExamDateLable = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        refreshBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 153));
        setMinimumSize(new java.awt.Dimension(830, 645));
        setPreferredSize(new java.awt.Dimension(830, 645));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dashboard");

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "arc:20;[light]background:shade(@background,5%);[dark]background:tint(@background,5%);[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[fill,200]",
            "[fill]",
            null
        ));
        crazyPanel1.setOpaque(false);

        todayPanel.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "arc:20;[light]background:lighten(@background,3%);[dark]background:darken(@background,3%)",
            null
        ));
        todayPanel.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "",
            "",
            null
        ));
        todayPanel.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Exams Scheduled:");

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("New Students:");

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Today's Activity");

        newStudentLable.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Written:");

        jLabel13.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Practical:");

        writtenLable.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N

        practicalLable.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N

        javax.swing.GroupLayout todayPanelLayout = new javax.swing.GroupLayout(todayPanel);
        todayPanel.setLayout(todayPanelLayout);
        todayPanelLayout.setHorizontalGroup(
            todayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(todayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(todayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(todayPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newStudentLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(todayPanelLayout.createSequentialGroup()
                        .addGroup(todayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addGroup(todayPanelLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(todayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(todayPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(writtenLable))
                                    .addGroup(todayPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(practicalLable)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        todayPanelLayout.setVerticalGroup(
            todayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(todayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(todayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(todayPanelLayout.createSequentialGroup()
                        .addComponent(newStudentLable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(todayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(writtenLable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(todayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(practicalLable))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout crazyPanel1Layout = new javax.swing.GroupLayout(crazyPanel1);
        crazyPanel1.setLayout(crazyPanel1Layout);
        crazyPanel1Layout.setHorizontalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(todayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crazyPanel1Layout.setVerticalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(todayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "arc:20;[light]background:shade(@background,2%);[dark]background:tint(@background,2%);[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[fill,200]",
            "[fill]",
            null
        ));
        crazyPanel2.setOpaque(false);

        totalStudentPanel.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "arc:20;[light]background:lighten(@background,3%);[dark]background:darken(@background,3%)",
            new String[]{
                ""
            }
        ));
        totalStudentPanel.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[fill,200]",
            "[fill]",
            null
        ));
        totalStudentPanel.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total Students");

        totalStudentLable.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N

        javax.swing.GroupLayout totalStudentPanelLayout = new javax.swing.GroupLayout(totalStudentPanel);
        totalStudentPanel.setLayout(totalStudentPanelLayout);
        totalStudentPanelLayout.setHorizontalGroup(
            totalStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalStudentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addComponent(totalStudentLable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        totalStudentPanelLayout.setVerticalGroup(
            totalStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalStudentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalStudentLable, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout crazyPanel2Layout = new javax.swing.GroupLayout(crazyPanel2);
        crazyPanel2.setLayout(crazyPanel2Layout);
        crazyPanel2Layout.setHorizontalGroup(
            crazyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalStudentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        crazyPanel2Layout.setVerticalGroup(
            crazyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalStudentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "arc:20;[light]background:shade(@background,2%);[dark]background:tint(@background,2%);[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[fill,200]",
            "[fill]",
            new String[]{
                ""
            }
        ));
        crazyPanel3.setOpaque(false);

        upcomingExamPanel.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "arc:20;[light]background:lighten(@background,2%);[dark]background:darken(@background,2%)",
            null
        ));
        upcomingExamPanel.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[fill,200]",
            "[fill]",
            null
        ));
        upcomingExamPanel.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Upcoming Exam Date");

        upcomingExamDateLable.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        upcomingExamDateLable.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout upcomingExamPanelLayout = new javax.swing.GroupLayout(upcomingExamPanel);
        upcomingExamPanel.setLayout(upcomingExamPanelLayout);
        upcomingExamPanelLayout.setHorizontalGroup(
            upcomingExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upcomingExamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upcomingExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addComponent(upcomingExamDateLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        upcomingExamPanelLayout.setVerticalGroup(
            upcomingExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upcomingExamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upcomingExamDateLable, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout crazyPanel3Layout = new javax.swing.GroupLayout(crazyPanel3);
        crazyPanel3.setLayout(crazyPanel3Layout);
        crazyPanel3Layout.setHorizontalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upcomingExamPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        crazyPanel3Layout.setVerticalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upcomingExamPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        refreshBtn.setBackground(new java.awt.Color(211, 127, 0));
        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(crazyPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crazyPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        loadDashboardData();
        loadUpcomingExamDates();
        highlightCalendarDates();
    }//GEN-LAST:event_refreshBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.glassmorphism.GlassIcon glassIcon1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel newStudentLable;
    private javax.swing.JLabel practicalLable;
    private javax.swing.JButton refreshBtn;
    private raven.crazypanel.CrazyPanel todayPanel;
    private javax.swing.JLabel totalStudentLable;
    private raven.crazypanel.CrazyPanel totalStudentPanel;
    private javax.swing.JLabel upcomingExamDateLable;
    private raven.crazypanel.CrazyPanel upcomingExamPanel;
    private javax.swing.JLabel writtenLable;
    // End of variables declaration//GEN-END:variables
}
