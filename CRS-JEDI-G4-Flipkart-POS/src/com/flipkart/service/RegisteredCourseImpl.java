package com.flipkart.service;

import com.flipkart.bean.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RegisteredCourseImpl implements RegisteredCourseInterface {

	private static List<RegisteredCourse> registeredCourses;
	public static int maximumEnrollment = 10;

	public RegisteredCourseImpl() {
		if(registeredCourses == null){
			registeredCourses = new ArrayList<>();
		}
	}

	@Override
	public boolean addRegisteredCourse(RegisteredCourse registeredCourse) {
		return registeredCourses.add(registeredCourse);
	}

	@Override
	public boolean dropRegisteredCourse(RegisteredCourse registeredCourse) {
		return registeredCourses.remove(registeredCourse);
	}

	@Override
	public List<RegisteredCourse> findRegisteredCourses(SemesterRegistration semesterRegistration) {
		List<RegisteredCourse> ret = new ArrayList<>();
		for(RegisteredCourse r:registeredCourses)
			if(r.getSemesterRegistration() == semesterRegistration)
				ret.add(r);
		return ret;
	}

	@Override
	public boolean checkAvailability(Course course) {
		return viewEnrolledStudents(course).size() <= maximumEnrollment;
	}

	@Override
	public RegisteredCourse findRegisteredCourse(SemesterRegistration semesterRegistration, String courseID) {
		RegisteredCourse ret = null;
		for(RegisteredCourse r:registeredCourses)
			if(r.getSemesterRegistration() == semesterRegistration && r.getCourse().getCourseID().equals(courseID))
				ret =  r;
		return ret;
	}

	@Override
	public boolean markGrade(String courseID, CourseCatalogue courseCatalogue, String rollNo, Grade grade) {
		RegisteredCourse ret = null;
		for(RegisteredCourse r:registeredCourses)
			if(r.getStudent().getRollNo().equals(rollNo) && r.getCourse().getCourseID().equals(courseID) && r.getSemesterRegistration().getSemester() == courseCatalogue.getSem())
				ret =  r;
		ret.setGrade(grade);
		return true;
	}

	@Override
	public List<Student> viewEnrolledStudents(Course course) {
		ArrayList<Student> students = new ArrayList<>();
		for(RegisteredCourse r : registeredCourses)
			if(r.getCourse()==course)
				students.add(r.getStudent());
		return students;
	}


}
