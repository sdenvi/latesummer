package com.latesummer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 数据关系
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
@Entity
@Table(name = "t_relationships")
public class Relationships {

	@Id
	@GeneratedValue
    // 内容主键
	@Column(nullable = false)
    private Integer cid;

    // 项目主键
	@Column(nullable = false)
    private Integer mid;

}