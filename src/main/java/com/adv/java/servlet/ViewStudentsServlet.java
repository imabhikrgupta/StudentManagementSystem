package com.adv.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.adv.java.db.StudentDB;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewStudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Connection con = StudentDB.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");
            out.println("<html><head><title>Students</title>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head><body>");
            out.println("<h2>Student List</h2>");
            out.println("<table border='1' cellpadding='8' cellspacing='0'>");
            out.println("<tr><th>Roll No</th><th>Name</th><th>Mark</th><th>Father</th><th>Email</th><th>Phone</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("ROLL_NO") + "</td>");
                out.println("<td>" + rs.getString("SNAME") + "</td>");
                out.println("<td>" + rs.getDouble("SMARK") + "</td>");
                out.println("<td>" + rs.getString("SFNAME") + " " + rs.getString("SLNAME") + "</td>");
                out.println("<td>" + rs.getString("SMAIL") + "</td>");
                out.println("<td>" + rs.getLong("SPHONE") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
