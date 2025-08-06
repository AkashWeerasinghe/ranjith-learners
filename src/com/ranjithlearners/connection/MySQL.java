package com.ranjithlearners.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface MySQL {

    Connection getConnection();

    ResultSet executeSearch(String query, Object... params) throws SQLException;

    void executeIUD(String query, Object... params) throws SQLException;
    
    void close();
    
}
