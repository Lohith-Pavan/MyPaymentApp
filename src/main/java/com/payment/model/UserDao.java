package com.payment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.payment.controller.Dbcon;

public class UserDao {
     public static int registerUser(UserDto user) {
    	 int i=0;
    	 Connection con = Dbcon.getCon();
    	 String query = "insert into user_details (user_name,password,first_name, last_name, phone_number, email, address) values (?,?,?,?,?,?,?)";
    	 try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3,user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getPhoneNumber());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getAddress());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return i;
     }
     public static UserDto loginUser(String userName,String password) {
    	 UserDto user = null;
    	 Connection con = Dbcon.getCon();
    	 String query = "select * from user_details where user_name = ? and password = ?";
    	 try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new UserDto(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return user;
     }
}
