package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.security.KeyPair;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/** The Class StudentDao. */
public class StudentDao implements StudentDaoInterface {

  /**
   * Login.
   *
   * @param username the username
   * @param password the password
   * @return the student
   */
  public static Student login(String username, String password) {
    Connection conn = Connection1.getConnection();

    PreparedStatement stmt = null;
    String sql = "Select * from Student where username=? and password=?";
    try {
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, username);
      stmt.setString(2, password);

      ResultSet rs = stmt.executeQuery();
      if (!rs.next()) return null;
      Student temp = new Student();
      temp.setUserID(rs.getInt("id"));
      temp.setName(rs.getString("name"));
      temp.setEmail(rs.getString("email"));
      temp.setUsername(rs.getString("username"));
      temp.setPassword(rs.getString("password"));
      temp.setRollNo(rs.getString("rollno"));
      temp.setDepartment(rs.getString("department"));
      temp.setApproved(rs.getBoolean("isApproved"));
      return temp;
    } catch (Exception e) {

      System.out.println(e);
    }
    return null;
  }

  /**
   * Adds the student.
   *
   * @param s the s
   * @return true, if successful
   */
  public static boolean addStudent(Student s) {

    Connection conn = Connection1.getConnection();

    PreparedStatement stmt = null;
    String sql =
        "INSERT INTO student (name, email, username, password, rollno, department,isApproved) VALUES (?,"
            + " ?,?,?,?,?,?)";
    try {
      stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, s.getName()); // This would set age
      stmt.setString(2, s.getEmail());
      stmt.setString(3, s.getUsername());
      stmt.setString(4, s.getPassword());

      stmt.setString(5, s.getRollNo());
      stmt.setString(6, s.getDepartment());
      stmt.setBoolean(7, false);
      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        s.setUserID(rs.getInt(1));
      }
      
      return true;

    } catch (Exception e) {

      System.out.println(e);
    }
   return false;
  }

  /**
   * Gets the studentsfrom id.
   *
   * @param sId the s id
   * @return the studentsfrom id
   */
  public static List<Student> getStudentsfromId(List<Integer> sId) {

    Connection conn = Connection1.getConnection();

    PreparedStatement stmt = null;
    List<Student> students = new ArrayList<Student>();
    for (Integer id : sId) {
      String sql = "Select * from Student where id= ? ";
      try {
        stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
          // Retrieve by column name
          Student temp = new Student();
          temp.setUserID(rs.getInt("id"));
          temp.setName(rs.getString("name"));
          temp.setEmail(rs.getString("email"));
          temp.setUsername(rs.getString("username"));
          temp.setRollNo(rs.getString("rollno"));
          temp.setDepartment(rs.getString("department"));

          students.add(temp);
        }

      } catch (Exception e) {

        System.out.println(e);
      }
    }
    return students;
  }

  /**
   * Gets the i dfrom roll no.
   *
   * @param rollno the rollno
   * @return the i dfrom roll no
   */
  public static int getIDfromRollNo(String rollno) {
    int sId = -1;
    Connection conn = Connection1.getConnection();

    PreparedStatement stmt = null;
    String sql = "Select id from student where rollno=?";

    try {
      // System.out.println("hi");
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, rollno);
      ResultSet rs = stmt.executeQuery();
      //			System.out.println(rs);
      while (rs.next()) {
        sId = rs.getInt("id");
        //			 System.out.println(cId);
      }
      stmt.close();
      conn.close();

    } catch (Exception e) {

      System.out.println(e);
    }

    return sId;
  }
  
  public static List<Student> getStudentsPendingApproval() {
	  
	  List<Student> students = new ArrayList<Student>();
	  
	  String sql = "SELECT * FROM student where isApproved = ?";
	  Connection con = Connection1.getConnection();
	  
	
	  try {
		  PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setBoolean(1, false);
		  ResultSet rs =  stmt.executeQuery();
		  
		  while(rs.next()) {
			  Student s = new Student();
			  s.setName(rs.getString("name"));
			  s.setEmail(rs.getString("email"));
			  
			  students.add(s);
		  }
		  
	  }catch(Exception e){
		  
		  System.out.println(e.getMessage());
		  
	  }finally {
		  
	  }
	  
	  return students;
	  
  }
  
  public  static boolean approveStudent(String email) {
	  
	  
	  String sql = "UPDATE student set isApproved = ? where email = ?";
	  Connection con = Connection1.getConnection();
	  
	
	  try {
		  PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setBoolean(1,true);
		 stmt.setString(2, email);
		 stmt.executeUpdate();
		  
		 return true;
		  
	  }catch(Exception e){
		  
		  System.out.println(e.getMessage());
		  
	  }finally {
		  
	  }
	  
	  return false;
	  
  }
  
}
