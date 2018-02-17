package com.soap.webservices.coursemanagement.soap.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.soap.webservices.coursemanagement.soap.bean.CourseBean;
import com.soap.webservices.coursemanagement.soap.bean.StatusBean;

@Component
public class CourseDetailsService {
	
	private static List<CourseBean> courses = new ArrayList<>();
	
	static {
		CourseBean course1 = new CourseBean(1, "Spring", "10 Steps");
		courses.add(course1);
		
		CourseBean course2 = new CourseBean(2, "Spring MVC", "10 Examples");
		courses.add(course2);
		
		CourseBean course3 = new CourseBean(3, "Spring Boot", "4 Steps");
		courses.add(course3);
		
		CourseBean course4 = new CourseBean(4, "Maven", "5 Steps");
		courses.add(course4);
	}

	//course - 1
	public CourseBean findById(int id) {
		for(CourseBean course : courses) {
			if(course.getId()==id)
				return course;
		}
		return null;
	}
	
	//courses
	public List<CourseBean> findAll() {
		return courses;
	}
	
	//delete course
	public StatusBean deleteById(int id) {
		
		Iterator<CourseBean> iterator = courses.iterator();
		while (iterator.hasNext()) {
			CourseBean course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return StatusBean.SUCCESS;
			}
		}
		return StatusBean.FAILURE;
	}
	
	//updating course and new course
	
}
