package com.payment.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.payment.model.UserDao;
import com.payment.model.UserDto;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Connection con = Dbcon.getCon();
		String uName = request.getParameter("userName");
		String pass = request.getParameter("password");
		UserDto user = UserDao.loginUser(uName, pass);
		if (user != null) {
			HttpSession hs = request.getSession();
			hs.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else {
			response.setContentType("text/html");
			pw.print("<h2 style='text-align:center;'>LOGIN UNSUCCESSFUL!</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
	}

}
