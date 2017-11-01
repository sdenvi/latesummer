package com.latesummer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latesummer.domain.entity.User;
import com.latesummer.service.IUserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private IUserService userService;

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