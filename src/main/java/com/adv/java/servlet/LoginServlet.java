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

			PreparedStatement ps = con.prepareStatement("SELECT * FROM student WHERE smail=? AND sphone=?");

			ps.setString(1, email);
			ps.setLong(2, phone);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
				out.println("<h1>Login Successfully !</h1>");
			else
				out.println("Invalid Login");

		} catch (NumberFormatException nfe) {
			out.println("Invalid number format: " + nfe.getMessage());
		} catch (Exception e) {
			out.println("Error: " + e.getMessage());
		}

	}
}