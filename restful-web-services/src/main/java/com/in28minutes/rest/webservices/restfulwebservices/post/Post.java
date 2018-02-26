package com.in28minutes.rest.webservices.restfulwebservices.post;

import java.util.Date;

public class Post {

	private int postId;
	private String content;
	private Date timestamp;
	
	public int getPostId() {
		return postId;
	}
	public String getContent() {
		return content;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	
	private Post(int postId, String content, Date timestamp) {
		super();
		this.postId = postId;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	protected Post() {
		
	}
	
}
