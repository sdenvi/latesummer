package com.latesummer.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.latesummer.base.BaseEntity;

import lombok.Data;

/**
 * 
 * @author Jenvi Sue
 *
 */

@Entity
@Data
@Table(name = "user")
public class User extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private Integer age;
}