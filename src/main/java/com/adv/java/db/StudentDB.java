package com.adv.java.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class StudentDB {
    private static Connection con;

    public static Connection getCon() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // Use service name XEPDB1 for Oracle XE 21c
                String url = "jdbc:oracle:thin:@localhost:1521:XE";
                String userid = "studentdb";
                String password = "stu123";

                con = DriverManager.getConnection(url, userid, password);
                System.out.println("Connection established successfully!");
            }
        } catch (Exception e) {
            System.out.println("DB Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
}