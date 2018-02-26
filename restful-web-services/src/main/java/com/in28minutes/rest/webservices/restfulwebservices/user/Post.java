package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;

public class Post {

	private String id;
	private String content;
	private Date timestamp;
	
	public String getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	
	private Post(String id, String content, Date timestamp) {
		super();
		this.id = id;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	protected Post() {
		
	}
	
}
