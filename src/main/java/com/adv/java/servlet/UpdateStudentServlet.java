package com.adv.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.adv.java.db.StudentDB;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UpdateStudentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException {
	    res.sendRedirect("update.html");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		try {
			
			
			String rollStr = req.getParameter("roll");
			String email = req.getParameter("email");
			String phoneStr = req.getParameter("phone");

			if (rollStr == null || rollStr.isEmpty() ||
			    email == null || email.isEmpty() ||
			    phoneStr == null || phoneStr.isEmpty()) {
			    out.println("Missing required fields!");
			    return;
			}


			int roll = Integer.parseInt(rollStr);
			long phone = Long.parseLong(phoneStr);


			Connection con = StudentDB.getCon();

			PreparedStatement ps = con.prepareStatement("UPDATE student SET smail=?,sphone=? WHERE roll_no=?");

			ps.setString(1, email);
			ps.setLong(2, phone);
			ps.setInt(3, roll);

			int rows = ps.executeUpdate();

			if (rows > 0)
				out.println("Student Updated Successfully");
			else
				out.println("Student Not Found");

		}  catch (NumberFormatException nfe) {
		    out.println("Invalid number format: " + nfe.getMessage());
		} catch (Exception e) {
		    out.println("Error: " + e.getMessage());
		}


	}
}