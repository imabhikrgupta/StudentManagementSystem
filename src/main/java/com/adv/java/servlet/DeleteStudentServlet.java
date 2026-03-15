package com.adv.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.adv.java.db.StudentDB;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteStudentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.sendRedirect("delete.html");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
  
		try {

			int roll = Integer.parseInt(req.getParameter("roll"));

			Connection con = StudentDB.getCon();

			PreparedStatement ps = con.prepareStatement("DELETE FROM student WHERE roll_no=?");

			ps.setInt(1, roll);

			int rows = ps.executeUpdate();

			if (rows > 0)
				out.println("<h1>Student Deleted</h1>");
			else
				out.println("Student Not Found");

		} catch (Exception e) {
			out.println(e);
		}

	}
}