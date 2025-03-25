package com.payment.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.payment.model.UserDao;
import com.payment.model.UserDto;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String uName = request.getParameter("userName");
		String uPass = request.getParameter("password");
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String phno = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		UserDto user = new UserDto(uName, uPass, fName, lName, phno, email, address);
		int i = UserDao.registerUser(user);
		if (i > 0) {
//				pw.print("done");

			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} else {
//				pw.print("not done");
			response.setContentType("test/html");
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.include(request, response);
			pw.print("<h1>Registration Unsucessful</h1>");
		}

	}

}
