package com.latesummer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latesummer.domain.User;
import com.latesummer.service.IUserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/add/{id}/{name}/{address}")
	public User addUser(@PathVariable Long id, @PathVariable String name, @PathVariable Integer age) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setAge(age);
		userService.saveUser(user);
		return user;
	}

	@RequestMapping(value = "/delete/{id}")
	public void deleteBook(@PathVariable int id) {
		userService.delete(id);
	}

	@RequestMapping(value = "/")
	public List<User> getBooks() {
		return userService.findAll();
	}

	@RequestMapping(value = "/{id}")
	public User getUser(@PathVariable int id) {
		User user = userService.findOne(id);
		return user;
	}

	@RequestMapping(value = "/search/name/{name}")
	public List<User> getBookByName(@PathVariable String name) {
		List<User> users = userService.findByName(name);
		return users;
	}

}