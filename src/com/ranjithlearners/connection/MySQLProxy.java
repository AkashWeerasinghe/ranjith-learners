package com.ranjithlearners.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLProxy implements MySQL {

    private MySQLReal realDatabase;

    public MySQLProxy() {
        this.realDatabase = null;
    }

    private void init() {
        if (realDatabase == null) {
            realDatabase = new MySQLReal();
            System.out.println("🔁 Real DB object created.");
        }
    }

    @Override
    public Connection getConnection() {
        init();
        System.out.println("➡️ Proxy: getConnection called");
        return realDatabase.getConnection();
    }

    @Override
    public ResultSet executeSearch(String query, Object... params) throws SQLException {
        init();
        System.out.println("➡️ Proxy: executeSearch called");
        long start = System.currentTimeMillis();
        ResultSet rs = realDatabase.executeSearch(query, params);
        long end = System.currentTimeMillis();
        System.out.println("🕒 Search executed in " + (end - start) + " ms: " + query);
        return rs;
    }

    @Override
    public void executeIUD(String query, Object... params) throws SQLException {
        init();
        System.out.println("➡️ Proxy: executeIUD called");
        long start = System.currentTimeMillis();
        realDatabase.executeIUD(query, params);
        long end = System.currentTimeMillis();
        System.out.println("🕒 IUD executed in " + (end - start) + " ms: " + query);
    }

    @Override
    public void close() {
        if (realDatabase != null) {
            realDatabase.close();
            System.out.println("🛑 Proxy closed connection.");
        }
    }

}
