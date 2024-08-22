package com.hsbc.ecommerceapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// util to establish connection with database
public class DatabaseConnection {
    // method for connecting to database
    public static Connection getConnection() {
        // try catch block to handel exception
        try {
            // connecting to database
            return DriverManager.getConnection("jdbc:mysql://localhost/hsbcdb", "root", "hksharsh11");
        }
        // handling exception
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
