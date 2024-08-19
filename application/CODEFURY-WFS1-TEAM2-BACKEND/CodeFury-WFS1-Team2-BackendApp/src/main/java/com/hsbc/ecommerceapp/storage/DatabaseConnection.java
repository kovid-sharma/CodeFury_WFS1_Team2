package com.hsbc.ecommerceapp.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// util to establish connection with database
public class DatabaseConnection {
    // method for connecting to database
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/hsbcdb", "root", "hksharsh11");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
