package com.latesummer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.latesummer.domain.User;

/**
 * Create By Jenvi Sue On 2017年9月20日
 */
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
