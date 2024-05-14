package com.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
   
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oms";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Varun";

  
    public static Connection getDBConn() throws SQLException {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to the database.");
        }
    }
}
