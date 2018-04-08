package com.latesummer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.latesummer.domain.LearnResouce;
import com.latesummer.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latesummer.domain.User;
import com.latesummer.repository.UserRepository;
import com.latesummer.service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public void add(User user) {
		userRepository.save(user);
	}

	@Override
	public void update(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteByIds(String[] ids) {
		for (String id : ids) {
			Long parseId = Long.valueOf(id);
			userRepository.delete(parseId);
		}
	}

	@Override
	public User queryLearnResouceById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public Page<User> userListByPage(Map<String, String> params, Pageable pageable) {
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				String id = params.get("id");
				String name= params.get("name");
				if(StringUtil.isNotBlank(id)){
					list.add(cb.equal(root.get("id"), id));
				}
				if(StringUtil.isNotBlank(name)){
					list.add(cb.equal(root.get("name"), name));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		Page<User> page = userRepository.findAll(spec, pageable);
		return page;
	}
}