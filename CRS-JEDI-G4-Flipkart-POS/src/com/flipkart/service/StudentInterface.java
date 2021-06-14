package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.SemesterRegistration;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseAlreadyFullException;
import com.flipkart.exception.CourseAlreadyRegisteredException;


// TODO: Auto-generated Javadoc
/** The Interface StudentInterface. */
public interface StudentInterface {

  /** Logout. */
  public void logout();

  /**
   * View report card.
   *
   * @param semesterRegistration the semester registration
   * @return the report card
   */
  ReportCard viewReportCard(SemesterRegistration semesterRegistration);

  /**
   * Pay fee.
   *
   * @param rollNo the roll no
   * @return true, if successful
   */
  boolean payFee(String rollNo);

  /**
   * Check availability.
   *
   * @param courseToCheck the course to check
   * @return true, if successful
   */
  boolean checkAvailability(Course courseToCheck);

  /**
   * Register for course.
   *
   * @param semesterRegistration the semester registration
   * @param CourseToRegister the course to register
   * @return true, if successful
 * @throws CourseAlreadyFullException 
 * @throws CourseAlreadyRegisteredException 
   */
  boolean registerForCourse(SemesterRegistration semesterRegistration, Course CourseToRegister) throws CourseAlreadyFullException, CourseAlreadyRegisteredException;

  /**
   * Login.
   *
   * @param username the username
   * @param password the password
   * @return true, if successful
   */
  boolean login(String username, String password);

  /**
   * Gets the student instance.
   *
   * @return the student instance
   */
  public Student getStudentInstance();
  
  public List<Student> getStudentsWaitingApprocal();
  
  public boolean approveStudent();

  boolean approveStudent(String email);
}
