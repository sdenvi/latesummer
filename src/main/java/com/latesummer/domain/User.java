package com.latesummer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.latesummer.common.domain.BaseModel;

import lombok.Data;

/**
 * Create By Jenvi Sue On 2017年11月21日
 */
@Data
@Entity
@Table(name = "user")
public class User implements BaseModel<Long> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private Integer age;
}