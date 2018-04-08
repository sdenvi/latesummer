package com.latesummer.service;

import java.util.List;
import java.util.Map;

import com.latesummer.domain.LearnResouce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.latesummer.domain.User;

public interface UserService {
	void add(User user);

	void update(User user);

	void deleteByIds(String[] ids);

	User queryLearnResouceById(Long id);

	Page<User> userListByPage(Map<String, String> params, Pageable pageable);
}