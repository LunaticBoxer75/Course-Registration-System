package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalogue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/** The Class CourseDao. */
public class CourseDao implements CourseDaoInterface {

  /**
   * Adds the course.
   *
   * @param s the s
   * @return true, if successful
   */
	
	private static Logger logger = Logger.getLogger(CourseDao.class);

  public static boolean addCourse(Course s) {

	  BasicConfigurator.configure();
    Connection conn = Connection1.getConnection();
    boolean check = true;

    PreparedStatement stmt = null;
    String sql1 = "SELECT COUNT(*) as cnt from course where courseCode= ? and courseCatalogueId=? ";
    try {
      stmt = conn.prepareStatement(sql1);
      stmt.setString(1, s.getCourseCode());
      stmt.setInt(2, s.getCourseCatalogueId());
      ResultSet rs = stmt.executeQuery();
      rs.next();
      int cnt = rs.getInt("cnt");
      if (cnt > 0) check = false;
    } catch (Exception e) {
      check = false;
      logger.error(e);
    }

    if (check == false) return check;

    String sql =
        "INSERT INTO course (courseCode, department, description, preRequisites, courseCatalogueId,"
            + " professorId) VALUES (?,?,?,?,?,?)";

    stmt = null;

    try {
      // System.out.println("hi");
      stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, s.getCourseCode()); // This would set age
      stmt.setString(2, s.getDepartment());
      stmt.setString(3, s.getDescriptions());
      stmt.setString(4, s.getPreRequisites());
      stmt.setInt(5, s.getCourseCatalogueId());
      stmt.setInt(6, s.getProfessorId());
      stmt.executeUpdate();
      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        s.setId(rs.getInt(1));
      }

    } catch (Exception e) {
      check = false;
      logger.error(e);
    }
    return check;
  }

  /**
   * Removes the course.
   *
   * @param courseId the course id
   * @return true, if successful
   */
  public static boolean removeCourse(int courseId) {

	  BasicConfigurator.configure();
    Connection conn = Connection1.getConnection();

    PreparedStatement stmt = null;
    String sql = "Delete from course where id=? ";

    try {
      // System.out.println("hi");
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, courseId);

      // stmt.setString(5, s.getEmpID());
      stmt.executeUpdate();
      stmt.close();
      conn.close();

    } catch (Exception e) {

    	logger.error(e);
    }

    return true;
  }

  /**
   * Read course.
   *
   * @param rs the rs
   * @return the course
   */
  private static Course readCourse(ResultSet rs) {
    try {
    	BasicConfigurator.configure();
      Course temp = new Course();
      temp.setCourseCode(rs.getString("courseCode"));
      temp.setDepartment(rs.getString("department"));
      temp.setId(rs.getInt("id"));
      temp.setCourseCatalogueId(rs.getInt("courseCatalogueId"));
      temp.setProfessorId(rs.getInt("professorId"));
      temp.setPreRequisites(rs.getString("prerequisites"));
      temp.setDescriptions(rs.getString("description"));
      return temp;
    } catch (Exception e) {
    	logger.error(e);
      return null;
    }
  }

  /**
   * Gets the courses.
   *
   * @param sql the sql
   * @return the courses
   */
  private static List<Course> getCourses(String sql) {
    Connection conn = Connection1.getConnection();

    BasicConfigurator.configure();
    PreparedStatement stmt = null;
    List<Course> courseList = new ArrayList<Course>();
    try {
      stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery(sql);
      // STEP 5: Extract data from result set
      while (rs.next()) {
        // Retrieve by column name
        courseList.add(readCourse(rs));
      }
      stmt.close();
      conn.close();
    } catch (Exception e) {

    	logger.error(e);
    }
    return courseList;
  }

  /**
   * Gets the course.
   *
   * @param courseId the course id
   * @return the course
   */
  public static Course getCourse(int courseId) {
    return getCourses("select * from course where id=" + courseId).get(0);
  }

  /**
   * Find courses.
   *
   * @param courseCatalogue the course catalogue
   * @return the list
   */
  public static List<Course> findCourses(CourseCatalogue courseCatalogue) {
    return getCourses("select * from course where courseCatalogueId=" + courseCatalogue.getId());
  }

  /**
   * Find course.
   *
   * @param courseCatalogue the course catalogue
   * @param coursecode the coursecode
   * @return the course
   */
  public static Course findCourse(CourseCatalogue courseCatalogue, String coursecode) {
    return getCourses(
            "select * from course where courseCatalogueId="
                + courseCatalogue.getId()
                + " and coursecode='"
                + coursecode
                + "'")
        .get(0);
  }

  /**
   * Mark course to teach.
   *
   * @param cId the c id
   * @param pId the id
   * @return true, if successful
   */
  public static boolean markCourseToTeach(int cId, int pId) {
    Connection conn = Connection1.getConnection();
    BasicConfigurator.configure();
    PreparedStatement stmt = null;
    String sql = "UPDATE course SET professorId = ? where id = ? ";

    try {
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, pId);
      stmt.setInt(2, cId);

      stmt.executeUpdate();
    } catch (Exception e) {
    	logger.error(e);
    }

    return true;
  }
}
