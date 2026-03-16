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
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.sendRedirect("login.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		try {

			String email = req.getParameter("email");
			String phoneStr = req.getParameter("phone");

			if (email == null || email.isEmpty() || phoneStr == null || phoneStr.isEmpty()) {
				out.println("Missing required fields!");
				return;
			}

			long phone = Long.parseLong(phoneStr);

			Connection con = StudentDB.getCon();

			 PreparedStatement ps = con.prepareStatement(
		                "SELECT ROLL_NO, SNAME, SMARK, SFNAME, SLNAME, SMAIL, SPHONE FROM student WHERE SMAIL=? AND SPHONE=?"
		            );
		            ps.setString(1, email);
		            ps.setLong(2, phone);

		            ResultSet rs = ps.executeQuery();

		            if (rs.next()) {
		                // Store student info in session
		                HttpSession session = req.getSession();
		                session.setAttribute("roll", rs.getInt("ROLL_NO"));
		                session.setAttribute("name", rs.getString("SNAME"));
		                session.setAttribute("mark", rs.getDouble("SMARK"));
		                session.setAttribute("fname", rs.getString("SFNAME"));
		                session.setAttribute("lname", rs.getString("SLNAME"));
		                session.setAttribute("email", rs.getString("SMAIL"));
		                session.setAttribute("phone", rs.getLong("SPHONE"));
//		                session.setAttribute("img", rs.getString("SIMG")); // profile image path


		                out.println("<h1 style='color:green'>Login Successful!</h1>");
				
				// Redirect to profile page
				res.sendRedirect("profile.jsp");
			//res.sendRedirect("dashboard");
				
			}
			else
				out.println("<h1 style='color:red'>Invalid Login</h1>");

		} catch (NumberFormatException nfe) {
			out.println("Invalid number format: " + nfe.getMessage());
		} catch (Exception e) {
			out.println("Error: " + e.getMessage());
		}

	}
	
}