package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import com.in28minutes.rest.webservices.restfulwebservices.post.Post;

public class User {
	
	private Integer userId;
	private String name;
	private Date birthDate;
	private List<Post> posts;
	
	public List<Post> getAllPosts() {
		return posts;
	}
	
	public Post addPost(Post post) {
		this.posts.add(post);
		return post;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return String.format("User [userId=%s, name=%s, birthDate=%s]", userId, name, birthDate);
	}

	public User(Integer userId, String name, Date birthDate) {
		super();
		this.userId = userId;
		this.name = name;
		this.birthDate = birthDate;
	}

	protected User() {
		
	}
	
	
}
