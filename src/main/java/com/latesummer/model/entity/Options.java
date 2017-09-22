package com.latesummer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 配置选项
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
@Entity
@Table(name = "t_options")
public class Options {
	
	@Id
	@GeneratedValue
	@Column(unique = true)
	private Long id;
    // 配置名称
	@Column(nullable = false)
    private String name;

    // 配置值
    private String value;

    // 配置描述
    @Column(nullable = false)
    private String description;

}