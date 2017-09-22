package com.latesummer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 元数据
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
@Entity
@Table(name = "t_metas")
public class Metas {

	@Id
	@GeneratedValue
    // 项目主键
	@Column(unique = true)
    private Integer mid;
    // 名称
	@Column(nullable = false)
    private String  name;
    // 项目缩略名
    private String  slug;
    // 项目类型
    @Column(nullable = false)
    private String  type;
    // 选项描述
    private String  description;
    // 项目排序
    private Integer sort;
    // 父级
    private Integer parent;
    // 文章数
    private Integer count;

}