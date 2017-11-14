package com.latesummer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latesummer.domain.User;
import com.latesummer.domain.UserRepository;
import com.latesummer.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public void saveUser(User book) {
		userRepository.save(book);
	}

	@Cacheable("users")
	public User findOne(long id) {
		System.out.println("Cached Pages");
        return userRepository.findOne(id);
	}

	@Override
	public void delete(long id) {
		userRepository.delete(id);
	}

	@Override
	public List<User> findByName(String name) {
        return userRepository.findByName(name);
	}
	
}