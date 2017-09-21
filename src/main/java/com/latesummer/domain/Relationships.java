package com.latesummer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * 数据关系
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
@Entity
public class Relationships {

	@Id
	@GeneratedValue
    // 内容主键
    private Integer cid;

    // 项目主键
    private Integer mid;

}