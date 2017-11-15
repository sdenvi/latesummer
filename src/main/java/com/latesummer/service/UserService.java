package com.latesummer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.latesummer.domain.User;

public interface UserService {
	public Page<User> findAll(Pageable pageable);

	public void saveUser(User book);

	public User findOne(long id);

	public void delete(long id);

	public List<User> findByName(String name);

}