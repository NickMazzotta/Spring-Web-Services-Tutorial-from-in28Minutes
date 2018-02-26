package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.rest.webservices.restfulwebservices.exception.PostNotFoundException;

@Component
public class PostDaoService {

	public List<Post> findAllPosts(User user) {
		return user.getPosts();
	}
	
	public Post findPost(User user, String id) {
		List<Post> posts = user.getPosts();
		for (Post post : posts) {
			if (post.getId() == id) {
				return post;
			}
		}
		return null;
	}
	
	public Post savePost(User user, Post post) {
		
	}
}
