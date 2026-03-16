package com.adv.java.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class StudentDB {
    public static Connection getCon() {
        Connection con = null;
        try {
            // Read values from environment variables
        	String url  = "jdbc:mysql://localhost:3306/abhidb";
        	String user = "root";
        	String pass = "Abhishek@09";

        	Class.forName("com.mysql.cj.jdbc.Driver");
        	 con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}