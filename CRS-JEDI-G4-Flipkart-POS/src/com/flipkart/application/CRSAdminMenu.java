package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalogue;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDao;
import com.flipkart.exception.LoginFailedException;
import com.flipkart.exception.StudentApprovalFailedException;
import com.flipkart.service.*;

import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

// TODO: Auto-generated Javadoc
/** The Class CRSAdminMenu. */
public class CRSAdminMenu {


  /** Show admin menu. */
  public static void showAdminMenu() {

    System.out.println("-----------Admin Menu-----------");

    System.out.println("Press 1 - Add course");

    System.out.println("Press 2 - Remove course");

    System.out.println("Press 3 - Add Professor");

    System.out.println("Press 4 - List Courses");
    
    System.out.println("Press 5 - Get Student list waiting for approval");
    
    System.out.println("Press 6 - Logout");

    System.out.println("---------------------------------");
  }

  /** Admin menu handler. */
  public static void adminMenuHandler() {

    try {

      // first login as an admin
      Scanner sc = new Scanner(System.in);
      AdminImpl admin = new AdminImpl();
      CourseCatalogueImpl courseCatalogues = new CourseCatalogueImpl();
      CourseCatalogue chosen = null;

      if (courseCatalogues.getCourseCatalogues().size() == 0) {

        chosen = new CourseCatalogue();
        chosen.setYear(2020);
        chosen.setSem(0);
        courseCatalogues.addCourseCatalogue(chosen);
      } else {

        chosen = courseCatalogues.getCourseCatalogues().get(0);
      }

      while (true) {

        System.out.print("Enter admin username: ");
        String username = sc.next();
        System.out.print("Enter admin password: ");
        String password = sc.next();

        try {

          if (admin.login(username, password)) break;
        }
        catch(LoginFailedException e){

        	System.out.println(e.getMessage());
        }

        //System.out.println("Invalid login. Please retry.");

        if(true) {

          throw new LoginFailedException(username);
        }
      }

      CourseImpl courseImpl = new CourseImpl();

      while (true) {

        CRSAdminMenu.showAdminMenu();

        int option = sc.nextInt();

        System.out.println();

        switch (option) {

          case 1:
            Course newCourse = new Course();
            String takeInput;

            System.out.print("Enter Course Code: ");
            takeInput = sc.next();
            newCourse.setCourseCode(takeInput);

            System.out.print("Enter Course Department: ");
            takeInput = sc.next();
            newCourse.setDepartment(takeInput);

            System.out.print("Enter Course Description: ");
            takeInput = sc.next();
            newCourse.setDescriptions(takeInput);

            System.out.print("Enter Course preRequisites: ");
            takeInput = sc.next();
            newCourse.setPreRequisites(takeInput);

            newCourse.setCourseCatalogueId(chosen.getId());

            try {

              courseImpl.addCourse(newCourse);
              System.out.println("The course was successfully added.");
            } catch (Exception e) {

            	System.out.println(e.getMessage());
            }

            break;

          case 2:
            System.out.print("Enter Course code of Course to removed: ");
            takeInput = sc.next();
            try {
            	 courseCatalogues.findCourse(chosen, takeInput);
            	 courseImpl.removeCourse(chosen, takeInput);
                 System.out.println("Course removed successfully!");
            }catch(Exception e) {
            	System.out.println(e.getMessage());
            }


            break;

          case 3:
            Professor newProfessor = new Professor();

            String takeInput2;

            System.out.print("Enter Professor username: ");
            takeInput2 = sc.next();
            newProfessor.setUsername(takeInput2);

            System.out.print("Enter Professor Name: ");
            takeInput2 = sc.next();
            newProfessor.setName(takeInput2);

            System.out.print("Enter Professor Email: ");
            takeInput2 = sc.next();
            newProfessor.setEmail(takeInput2);

            System.out.print("Enter Professor Password: ");
            takeInput2 = sc.next();
            newProfessor.setPassword(takeInput2);

            System.out.print("Enter Professor EmployeeID: ");
            takeInput2 = sc.next();
            newProfessor.setEmpID(takeInput2);

            System.out.print("Enter Professor Department: ");
            takeInput2 = sc.next();
            newProfessor.setDept(takeInput2);

            try {

              admin.addProfessor(newProfessor);
            } catch (Exception e) {

            	System.out.println(e.getMessage());
            }

            break;

          case 4:
            List<Course> courses = courseImpl.findCourses(chosen);
            System.out.println("Total " + courses.size() + " courses found");
            if(courses.size()>0) {
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.format("%25s%25s%25s%25s%n", "Course Code", "Course Description", "Course Department", "Course Prerequisites" );
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            
            for (Course course : courses) {

              System.out.format("%25s%25s%25s%25s%n",course.getCourseCode(), course.getDescriptions(), course.getDepartment(), course.getPreRequisites());
            }
            
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            }
            break;

          case 5 :

            StudentImpl s = new StudentImpl();
            List<Student> student = s.getStudentsWaitingApprocal();

            if(student.size() == 0)
            {
              System.out.println("No students pending approval");
            }
            else {
              System.out.println("List of students waiting for approval:");
              
              System.out.format("%25s%25s%n","Name","Roll No");
              System.out.println("-------------------------------------------------------------------------------------------------------------");
              for (int i = 0; i < student.size(); i++) {

                System.out.format("%25s%25s%n",student.get(i).getName(), student.get(i).getRollNo() );
   
//                System.out.println(student.get(i).getName() + "\t" + student.get(i).getRollNo());
              }
              System.out.println("-------------------------------------------------------------------------------------------------------------");
              System.out.println("Enter the roll number of student you want to approve");

              String rollno = sc.next();

              try {

                boolean done = s.approveStudent(rollno);
                (new NotificationImpl()).showNotification("Welcome to Flipkart University!", rollno);
                System.out.println("Student approved successfully !!!");
              } catch (StudentApprovalFailedException e) {

            	  System.out.println(e.getMessage());
              }
            }

            break;

          case 6:
            System.out.println("Successfully logged out !!");

            return;

          default:

            System.out.println("Invalid choice");

            break;
        }
      }
    } catch (Exception e) {

    	System.out.println(e.getMessage());
    }
  }
}
