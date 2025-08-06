package com.ranjithlearners.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLReal implements MySQL {

    private static final String DATABASE = DBConfig.get("db.name");
    private static final String USERNAME = DBConfig.get("db.user");
    private static final String PASSWORD = DBConfig.get("db.password");
    private static final String HOST = DBConfig.get("db.host");
    private static final String PORT = DBConfig.get("db.port");

    private static Connection connection = null;

    @Override
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
                        + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

                connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
                System.out.println("✅ Connected to MySQL database.");
            }
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResultSet executeSearch(String query, Object... params) throws SQLException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("❌ Failed to establish database connection");
        }
        PreparedStatement pst = conn.prepareStatement(query);
        setParameters(pst, params);
        return pst.executeQuery();
    }

    @Override
    public void executeIUD(String query, Object... params) throws SQLException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("❌ Failed to establish database connection");
        }
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            setParameters(pst, params);
            pst.executeUpdate();
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔒 MySQL connection closed.");
            } catch (SQLException e) {
                System.err.println("❌ Error closing connection: " + e.getMessage());
            }
        }
    }

// Utility to set parameters
    private void setParameters(PreparedStatement pst, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pst.setObject(i + 1, params[i]);
        }
    }

}
