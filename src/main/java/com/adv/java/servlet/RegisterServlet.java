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
        	String markStr = req.getParameter("mark");
        	String phoneStr = req.getParameter("phone");

        	if (markStr == null || phoneStr == null) {
        	    out.println("Missing required fields!");
        	    return;
        	}

        	// remove rollStr entirely
        	String name  = req.getParameter("name");
        	double mark  = Double.parseDouble(markStr);
        	String fname = req.getParameter("fname");
        	String lname = req.getParameter("lname");
        	String email = req.getParameter("email");
        	long phone   = Long.parseLong(phoneStr);
            Connection con = StudentDB.getCon();
            PreparedStatement ps = con.prepareStatement(
            	    "INSERT INTO student (SNAME, SMARK, SFNAME, SLNAME, SMAIL, SPHONE) VALUES (?, ?, ?, ?, ?, ?)"
            	);
            	ps.setString(1, name);      // SNAME
            	ps.setDouble(2, mark);      // SMARK
            	ps.setString(3, fname);     // SFNAME
            	ps.setString(4, lname);     // SLNAME
            	ps.setString(5, email);     // SMAIL
            	ps.setLong(6, phone);       // SPHONE

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