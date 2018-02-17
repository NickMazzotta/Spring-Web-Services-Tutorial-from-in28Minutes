package com.soap.webservices.coursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DeleteCourseDetailsRequest;
import com.in28minutes.courses.DeleteCourseDetailsResponse;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.soap.webservices.coursemanagement.soap.bean.CourseBean;
import com.soap.webservices.coursemanagement.soap.bean.StatusBean;
import com.soap.webservices.coursemanagement.soap.exception.CourseNotFoundException;
import com.soap.webservices.coursemanagement.soap.service.CourseDetailsService;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;

	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		
		CourseBean course = service.findById(request.getId());
		
		if (course == null) {
			throw new CourseNotFoundException("Invalid Course Id " + request.getId());
		}
		
		return mapCourseDetails(course);
	}
	
	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		
		List<CourseBean> courses = service.findAll();
		
		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse processDeleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		
		StatusBean status = service.deleteById(request.getId());
		
		return mapDelete(status);
	}

	private DeleteCourseDetailsResponse mapDelete(StatusBean status) {
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
	}

	private com.in28minutes.courses.Status mapStatus(StatusBean status) {
		if(status==StatusBean.FAILURE)
			return com.in28minutes.courses.Status.FAILURE;
		return com.in28minutes.courses.Status.SUCCESS;
	}

	private GetCourseDetailsResponse mapCourseDetails(CourseBean course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = mapCourse(course);
		response.setCourseDetails(courseDetails);
		return response;
	}

	private CourseDetails mapCourse(CourseBean course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<CourseBean> courses) {
		
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for(CourseBean course:courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}

}
