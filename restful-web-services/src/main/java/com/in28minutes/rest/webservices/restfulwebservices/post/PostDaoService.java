package com.in28minutes.rest.webservices.restfulwebservices.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;

@Component
public class PostDaoService {

	public List<Post> findAllPosts(User user) {
		return user.getAllPosts();
	}
	
	public Post findPost(User user, int postId) {
		List<Post> posts = user.getAllPosts();
		for (Post post : posts) {
			if (post.getPostId() == postId) {
				return post;
			}
		}
		return null;
	}
	
	public Post savePost(User user, Post post) {
		Post addedPost  = user.addPost(post);
		return addedPost;
	}
}
