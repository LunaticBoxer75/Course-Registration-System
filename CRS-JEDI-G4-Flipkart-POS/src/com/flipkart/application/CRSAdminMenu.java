package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalogue;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminImpl;
import com.flipkart.service.CourseCatalogueImpl;
import com.flipkart.service.CourseImpl;
import com.flipkart.service.StudentImpl;

import java.util.List;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/** The Class CRSAdminMenu. */
public class CRSAdminMenu {

  /** Show admin menu. */
  public static void showAdminMenu() {

    System.out.println("-----------Admin Menu-----------");

    System.out.println("Press 1 - Add course");

    System.out.println("Press 2 - Remove course");

    System.out.println("Press 3 - Add Professor");

    System.out.println("Press 4 - Add Student");

    System.out.println("Press 5 - List Courses");
    
    System.out.println("Press 6 - Get Student list waiting for approval");
    
    System.out.println("Press 7 - Logout");

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
      } else chosen = courseCatalogues.getCourseCatalogues().get(0);
      while (true) {
        System.out.print("Enter admin username: ");
        String username = sc.next();
        System.out.print("Enter admin password: ");
        String password = sc.next();
        if (admin.login(username, password)) break;
        System.out.println("Invalid login. Please retry.");
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

            System.out.print("Enter Course Code:");
            takeInput = sc.next();
            newCourse.setCourseCode(takeInput);

            System.out.print("Enter Course Department:");
            takeInput = sc.next();
            newCourse.setDepartment(takeInput);

            System.out.print("Enter Course Description:");
            takeInput = sc.next();
            newCourse.setDescriptions(takeInput);

            System.out.print("Enter Course preRequisites:");
            takeInput = sc.next();
            newCourse.setPreRequisites(takeInput);

            newCourse.setCourseCatalogueId(chosen.getId());
            try {
              courseImpl.addCourse(newCourse);
            } catch (Exception e) {
              System.out.println(e);
            }

            break;

          case 2:
            System.out.print("Enter Course code of Course to removed: ");
            takeInput = sc.next();
            Course removalCourse = courseCatalogues.findCourse(chosen, takeInput);
            if (removalCourse == null) System.out.println("Course not found!");
            else {
              courseImpl.removeCourse(chosen, takeInput);
              System.out.println("Course removed successfully!");
            }

            break;

          case 3:
            Professor newProfessor = new Professor();

            String takeInput2;

            System.out.print("Enter Professor username:");
            takeInput2 = sc.next();
            newProfessor.setUsername(takeInput2);

            System.out.print("Enter Professor Name:");
            takeInput2 = sc.next();
            newProfessor.setName(takeInput2);

            System.out.print("Enter Professor Email:");
            takeInput2 = sc.next();
            newProfessor.setEmail(takeInput2);

            System.out.print("Enter Professor Password:");
            takeInput2 = sc.next();
            newProfessor.setPassword(takeInput2);

            System.out.print("Enter Professor EmployeeID:");
            takeInput2 = sc.next();
            newProfessor.setEmpID(takeInput2);

            System.out.print("Enter Professor Department:");
            takeInput2 = sc.next();
            newProfessor.setDept(takeInput2);

            admin.addProfessor(newProfessor);
            break;

          case 4:
            Student newStudent = new Student();

            System.out.print("Enter Student username:");
            takeInput2 = sc.next();
            newStudent.setUsername(takeInput2);

            System.out.print("Enter Student Name:");
            takeInput2 = sc.next();
            newStudent.setName(takeInput2);

            System.out.print("Enter Student Email:");
            takeInput2 = sc.next();
            newStudent.setEmail(takeInput2);

            System.out.print("Enter Student Password:");
            takeInput2 = sc.next();
            newStudent.setPassword(takeInput2);

            System.out.print("Enter Student roll number:");
            takeInput2 = sc.next();
            newStudent.setRollNo(takeInput2);

            System.out.print("Enter Student department:");
            takeInput2 = sc.next();
            newStudent.setDepartment(takeInput2);

            admin.addStudent(newStudent);

            break;

          case 5:
            List<Course> courses = courseImpl.findCourses(chosen);
            System.out.println("Total " + courses.size() + " courses found");
            
            System.out.format("%25s%25s%25s%25s%n", "Course Code", "Course Description", "Course Department", "Course Prerequisites" );
            
            for (Course course : courses) {    
              System.out.format("%25s%25s%25s%25s%n",course.getCourseCode(), course.getDescriptions(), course.getDepartment(), course.getPreRequisites());         
            }
            break;
          case 6 : 
        	  System.out.println("****All the stduent waiting approval are***");
        	  
        	  StudentImpl s = new StudentImpl();
        	  List<Student> student = s.getStudentsWaitingApprocal();
        	  
        	  for(int i=0;i<student.size();i++) {
        		  System.out.println("Name     " + "Email   ");
        		  System.out.println(student.get(i).getName() + "    " + student.get(i).getEmail());
        		 
        		  
        	  }
        	  System.out.println("** ENTER THEB EMAIL NUMBER OF THE STUDENT YOU WANT TO APPROVE");
        	  
        	  String email = sc.next();
    		  
    		  StudentImpl studImpl = new StudentImpl();
    		  
    		  boolean done  = studImpl.approveStudent(email);
    		  
    		  if(done  == true) {
    			  System.out.println("Student approved successfully !!!");
    		  }
    		  
    		  break;
        	  
          case 7:
            System.out.println("Successfully logged out");
            return;
          default:
            System.out.println("Invalid choice");
            break;
        }
      }
    } catch (Exception e) {

    }
  }
}
