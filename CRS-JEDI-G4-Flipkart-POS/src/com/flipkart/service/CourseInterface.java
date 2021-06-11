package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface CourseInterface {

    public Boolean indicateProfessor(Course course, Professor professor);

    public Boolean checkAvailability(Course course);

    public List<Student> viewEnrolledStudents(Course course);

}
