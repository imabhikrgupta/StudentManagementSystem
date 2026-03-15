package com.adv.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.adv.java.db.StudentDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    	res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            // Get parameters safely
            String rollStr = req.getParameter("roll");
            String markStr = req.getParameter("mark");
            String phoneStr = req.getParameter("phone");

            if (rollStr == null || markStr == null || phoneStr == null) {
                out.println("Missing required fields!");
                return;
            }

            int roll = Integer.parseInt(rollStr);
            String name = req.getParameter("name");
            double mark = Double.parseDouble(markStr);
            String fname = req.getParameter("fname");
            String lname = req.getParameter("lname");
            String email = req.getParameter("email");
            long phone = Long.parseLong(phoneStr);

            Connection con = StudentDB.getCon();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO student VALUES(?,?,?,?,?,?,?)"
            );

            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setDouble(3, mark);
            ps.setString(4, fname);
            ps.setString(5, lname);
            ps.setString(6, email);
            ps.setLong(7, phone);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                out.println("<h1>Student Registered Successfully</h1>");
            } else {
                out.println("Registration failed.");
            }

        } catch (NumberFormatException nfe) {
            out.println("Invalid number format: " + nfe.getMessage());
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    // Optional: show form if accessed via GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.sendRedirect("register.html");
    }
}