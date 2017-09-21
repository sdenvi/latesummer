package com.latesummer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * 元数据
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
@Entity
public class Metas {

	@Id
	@GeneratedValue
    // 项目主键
    private Integer mid;
    // 名称
    private String  name;
    // 项目缩略名
    private String  slug;
    // 项目类型
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