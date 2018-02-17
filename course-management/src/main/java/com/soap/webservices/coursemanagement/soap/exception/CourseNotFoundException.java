package com.soap.webservices.coursemanagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;

@SoapFault(faultCode=FaultCode.CLIENT)
// Alternatively -> @SoapFault(faultCode=FaultCode.CUSTOM, customFaultCode="{http://in28minutes.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6785798005544772787L;

	public CourseNotFoundException(String message) {
		super(message);
	}

	
}
