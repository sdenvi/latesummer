package com.latesummer.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * 
 * @author Jenvi Sue
 *
 */
public interface LearnResouceRepository extends JpaRepository<LearnResouce, Long> {
	
	

	List<User> findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
