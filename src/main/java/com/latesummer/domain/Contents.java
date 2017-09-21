package com.latesummer.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 内容
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
@Entity
public class Contents {

    // post表主键
	@Id
	@GeneratedValue
    private Integer cid;
    // 内容标题
    private String  title;
    // 内容缩略名
    private String  slug;
    // 内容生成时的GMT unix时间戳
    private Integer created;
    // 内容更改时的GMT unix时间戳
    private Integer modified;
    // 内容文字
    private String  content;
    // 内容所属用户id
    private Integer authorId;
    // 点击次数
    private Integer hits;
    // 内容类别
    private String  type;
    // 内容类型，markdown或者html
    private String  fmtType;
    // 文章缩略图
    private String  thumbImg;
    // 标签列表
    private String  tags;
    // 分类列表
    private String  categories;
    // 内容状态
    private String  status;
    // 内容所属评论数
    private Integer commentsNum;
    // 是否允许评论
    private Boolean allowComment;
    // 是否允许ping
    private Boolean allowPing;
    // 允许出现在聚合中
    private Boolean allowFeed;
}