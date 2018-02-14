package com.soap.webservices.coursemanagement.soap.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.soap.webservices.coursemanagement.soap.bean.Course;

@Component
public class CourseDetailsService {
	
	private static List<Course> courses = new ArrayList<>();
	
	static {
		Course course1 = new Course(1, "Spring", "10 Steps");
		courses.add(course1);
		
		Course course2 = new Course(2, "Spring MVC", "10 Examples");
		courses.add(course2);
		
		Course course3 = new Course(3, "Spring Boot", "4 Steps");
		courses.add(course3);
		
		Course course4 = new Course(4, "Maven", "5 Steps");
		courses.add(course4);
	}

	//course - 1
	public Course findById(int id) {
		for(Course course : courses) {
			if(course.getId()==id)
				return course;
		}
		return null;
	}
	
	//courses
	public List<Course> findAll() {
		return courses;
	}
	
	//delete course
	public int deleteById(int id) {
		
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return 1;
			}
		}
		return 0;
	}
	
	//updating course and new course
	
}
