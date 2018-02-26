package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	// dummy users data
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}
	
	
	// Methods
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getUserId()==null) {
			user.setUserId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int userId) {
		for(User user: users) {
			if(user.getUserId() == userId)
				return user;
		}
		return null;
	}
	
	public User deleteByUserId(int userId) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getUserId() == userId) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
