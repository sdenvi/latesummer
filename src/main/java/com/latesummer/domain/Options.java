package com.latesummer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * 配置选项
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
@Entity
public class Options {
	
	@Id
	@GeneratedValue
    // 配置名称
    private String name;

    // 配置值
    private String value;

    // 配置描述
    private String description;

}