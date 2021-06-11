package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public class AdminImpl implements AdminInterface {

	@Override
	public boolean addCourse(Course courseToBeAdded) {
		return new CourseCatalogueImpl().addCourse(courseToBeAdded);
		// TODO Auto-generated method stub
//		return false;
	}

	@Override
	public boolean removeCourse(Course courseToBeRemoved) {
		return new CourseCatalogueImpl().removeCourse(courseToBeRemoved);
		// TODO Auto-generated method stub
//		return false;
	}

	@Override
	public boolean addProfessor(Professor professorToBeAdded) {
		System.out.println("Adding professor todo");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveStudent(String rollNo ) {
		System.out.println("Approving student todo");

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveGrade(String rollNo) {
		System.out.println("Approving grade todo");
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean verifyCourseReg(String rollNo) {
		System.out.println("Approving course todo");

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login(String userID, String password) {
		if(userID.trim().equals("test") && password.trim().equals("test"))
			return true;
		return false;
	}
}
