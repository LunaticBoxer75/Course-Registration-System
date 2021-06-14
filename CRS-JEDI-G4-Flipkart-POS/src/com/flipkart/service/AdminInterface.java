package com.flipkart.service;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.ProfessorPreExistsException;
import com.flipkart.exception.StudentPreExistsException;

// TODO: Auto-generated Javadoc
/** The Interface AdminInterface. */
public interface AdminInterface {

  /**
   * Adds the professor.
   *
   * @param professorToBeAdded the professor to be added
   * @return true, if successful
 * @throws ProfessorPreExistsException 
   */
  public boolean addProfessor(Professor professorToBeAdded) throws ProfessorPreExistsException;

  /**
   * Adds the student.
   *
   * @param student the student
   * @return true, if successful
 * @throws StudentPreExistsException 
   */
  public boolean addStudent(Student student) throws StudentPreExistsException;

  /**
   * Approve grade.
   *
   * @param rollNo the roll no
   * @return true, if successful
   */
  public boolean approveGrade(String rollNo);

  /**
   * Verify course reg.
   *
   * @param rollNo the roll no
   * @return true, if successful
   */
  public boolean verifyCourseReg(String rollNo);

  /**
   * Login.
   *
   * @param userID the user ID
   * @param password the password
   * @return true, if successful
   */
  public boolean login(String userID, String password);
}
