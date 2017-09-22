package com.latesummer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 评论
 * Create By Jenvi Sue On 2017年9月22日
 */
@Entity
@Data
@Table(name = "t_comments") 
public class Comments {

	@Id
	@GeneratedValue
    // comment表主键
    private Integer coid;

    // post表主键,关联字段
	@Column(nullable = false)
    private Integer cid;

    // 评论生成时的GMT unix时间戳
	@Column(nullable = false)
    private Integer created;

    // 评论作者
	@Column(nullable = false)
    private String author;

    // 评论所属用户id
	@Column(columnDefinition = "INT default 0")
    private Integer author_id;

    // 评论所属内容作者id
	@Column(columnDefinition = "INT default 0")
    private Integer owner_id;

    // 评论者邮件
    private String mail;

    // 评论者网址
    private String url;

    // 评论者ip地址
    private String ip;

    // 评论者客户端
    private String agent;

    // 评论内容
    private String content;

    // 评论类型
    private String type;

    // 评论状态
    private String status;

    // 父级评论
	@Column(columnDefinition = "INT default 0")
    private Integer parent;

}