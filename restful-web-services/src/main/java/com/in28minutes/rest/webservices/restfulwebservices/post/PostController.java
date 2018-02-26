package com.in28minutes.rest.webservices.restfulwebservices.post;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserDaoService;

public class PostController {

	@Autowired
	private UserDaoService userService;
	@Autowired
	private PostDaoService postService;
	
	
	@GetMapping(path="/users/{userId}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int userId) {
		User user = userService.findOne(userId);
		if(user == null)
			throw new UserNotFoundException("userId-" + userId);
		
		List<Post> posts = postService.findAllPosts(user);
		if(posts.isEmpty())
			throw new PostNotFoundException("Post list is empty");
			
		return posts;
	}
	
	@GetMapping(path="/users/{userId}/posts/{postId}")
	public Post retrieveSinglePost(@PathVariable int userId, @PathVariable int postId) {
		User user = userService.findOne(userId);
		if(user == null)
			throw new UserNotFoundException("userId-" + userId);
		
		Post post = postService.findPost(user, postId);
		if(post == null)
			throw new PostNotFoundException("postId-" + postId);
		return post;
	}
	
	@PostMapping(path="/users/{userId}/posts")
	public ResponseEntity<Object> createPost(@RequestBody Post post, @PathVariable int userId) {
		User user = userService.findOne(userId);
		if(user == null)
			throw new UserNotFoundException("userId-" + userId);
		
		Post savedPost = postService.savePost(user, post);
		if(savedPost == null)
			throw new PostNotFoundException("postId-" + post.getPostId());
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{postId}")
				.buildAndExpand(savedPost.getPostId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
