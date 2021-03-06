package com.flipkart.dao;

import com.flipkart.bean.CourseCatalogue;
import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/** The Class CourseCatalogueDao. */
public class CourseCatalogueDao implements CourseCatalogueDaoInterface {

  private static Logger logger = Logger.getLogger(CourseCatalogueDao.class);

  /**
   * Adds the course catalogue.
   *
   * @param s the s
   * @return true, if successful
   */
  public static boolean addCourseCatalogue(CourseCatalogue s) {

    Connection conn = DBUtil.getConnection();

    PreparedStatement stmt = null;
    String sql = SQLConstants.courseCatalogueAdd;

    try {

      stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setInt(1, s.getSem());
      stmt.setInt(2, s.getYear());
      stmt.executeUpdate();
      ResultSet rs = stmt.getGeneratedKeys();

      if (rs.next()) {

        s.setId(rs.getInt(1));
      }

    } catch (Exception e) {

      logger.error(e);
    }

    return true;
  }

  /**
   * Gets the course catalogues.
   *
   * @return the course catalogues
   */
  public static List<CourseCatalogue> getCourseCatalogues() {

    Connection conn = DBUtil.getConnection();

    PreparedStatement stmt = null;
    List<CourseCatalogue> courseList = new ArrayList<CourseCatalogue>();

    try {

      String sql = SQLConstants.courseCatalogueList;

      stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      // STEP 5: Extract data from result set

      while (rs.next()) {

        // Retrieve by column name
        CourseCatalogue temp = new CourseCatalogue();
        temp.setId(rs.getInt("id"));
        temp.setSem(rs.getInt("semester"));
        temp.setYear(rs.getInt("year"));
        courseList.add(temp);
      }

      stmt.close();
      conn.close();
    } catch (Exception e) {

      logger.error(e);
    }

    // STEP 6: Clean-up environment
    // rs.close();
    return courseList;
  }

  /**
   * Gets the course idfrom code.
   *
   * @param courseCode the course code
   * @return the course idfrom code
   */
  public static int getCourseIdfromCode(String courseCode) {

    // System.out.println(courseCode);
    Connection conn = DBUtil.getConnection();

    PreparedStatement stmt = null;
    String sql = SQLConstants.getCourseIdfromCode;
    int cId = -1;

    try {

      stmt = conn.prepareStatement(sql);
      stmt.setString(1, courseCode);
      ResultSet rs = stmt.executeQuery();
      // System.out.println(rs);

      while (rs.next()) {

        cId = rs.getInt("id");
        // System.out.println(cId);
      }

      stmt.close();
      conn.close();
    } catch (Exception e) {

      logger.error(e);
    }

    return cId;
  }
}
