package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;
	
	//GET /users
	//retrieveAllUsers
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers() {
		List<User> users = service.findAll();
		if (users.isEmpty())
			throw new UserNotFoundException("Users list empty");
		return users;
	}
	
	//GET /users/{userId}
	//retrieveUser(int userId)
	@GetMapping(path="/users/{userId}")
	public User retrieveUser(@PathVariable int userId) {
		User user = service.findOne(userId);
		if(user == null)
			throw new UserNotFoundException("userId-" + userId);
		return user;
	}
	
	//input - details of user
	//output - CREATED & Return the created URI
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		if (savedUser == null)
			throw new UserNotFoundException("userId-" + savedUser.getUserId());
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{userId}")
			.buildAndExpand(savedUser.getUserId())
			.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		User deletedUser = service.deleteByUserId(userId);
		
		if(deletedUser == null)
			throw new UserNotFoundException("userId-" + userId);
	}

}
