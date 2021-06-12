package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public class ProfessorDao {
	
	public static Professor login(String username, String password) {
		Connection conn = Connection1.getConnection();

		PreparedStatement stmt = null;
		String sql = "Select * from professor where username=? and password=?";
		try {
			
			System.out.println(username +  password);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next())
				return null;
			Professor temp = new Professor();
			temp.setUserID(rs.getInt("id"));
			temp.setName(rs.getString("name"));
			temp.setEmail(rs.getString("email"));
			temp.setUsername(rs.getString("username"));
			temp.setPassword(rs.getString("password"));
			temp.setEmpID(rs.getString("empid"));
			return temp;
		} catch (Exception e) {

			System.out.println(e);
		}
		return null;
	}
	
	public static boolean addProfessor(Professor s) {

		Connection conn = Connection1.getConnection();

		PreparedStatement stmt = null;
		String sql = "INSERT INTO professor (name, email, username, password,empId) VALUES (?, ?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getName()); // This would set age
			stmt.setString(2, s.getEmail());
			stmt.setString(3, s.getUsername());
			stmt.setString(4, s.getPassword());

			stmt.setString(5, s.getEmpID());
			stmt.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
		return true;

	}

}
