package com.latesummer.web.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latesummer.domain.User;
import com.latesummer.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService userService;
	
    // 创建线程安全的Map 
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>()); 

	@RequestMapping(value = "/add/{id}/{name}/{age}")
	public User addUser(@PathVariable Integer id, @PathVariable String name, @PathVariable Integer age) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setAge(age);
		userService.saveUser(user);
		return user;
	}

	@RequestMapping(value = "/delete/{id}")
	public void deleteBook(@PathVariable Long id) {
		userService.delete(id);
	}

	@RequestMapping(value = "/")
	public Page<User> getBooks(@PageableDefault(value = 2, sort = { "id" }, direction = Direction.DESC) Pageable pageable) {
		return userService.findAll(pageable);
	}

	@RequestMapping(value = "/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		return user;
	}

	@RequestMapping(value = "/search/name/{name}")
	public List<User> getBookByName(@PathVariable String name) {
		List<User> users = userService.findByName(name);
		return users;
	}

}