package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.in28minutes.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;

public class PostController {

	@Autowired
	private UserDaoService userService;
	@Autowired
	private PostDaoService service;
	
	
	@GetMapping(path="/users/{id}/posts")
	public List<Post> retrieveAllPostsForUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id-" + id);
		
		List<Post> posts = user.getPosts();
		if(posts.isEmpty())
			throw new PostNotFoundException("Post list is empty");
			
		return posts;
	}
	
	@PostMapping(path="/users/{id}/posts")
	public ResponseEntity<Object> createPost(@RequestBody Post post, @PathVariable int id) {
		User user = service.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id-" + id);
		
		
	}
}
