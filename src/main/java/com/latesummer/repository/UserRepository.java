package com.latesummer.repository;

import java.util.List;

import com.latesummer.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.latesummer.common.dao.BaseDao;

/**
 * Create By Jenvi Sue On 2017年9月20日
 */
public interface UserRepository extends BaseDao<User, Long> {

	List<User> findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
