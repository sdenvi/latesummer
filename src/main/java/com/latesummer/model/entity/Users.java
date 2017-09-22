package com.latesummer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 用户
 * Create By Jenvi Sue On 2017年9月21日
 */
@Entity
@Data
@Table(name = "t_users")
public class Users {

	@Id
	@GeneratedValue
    // user表主键
    private Integer uid;

    // 用户名称
	@Column(nullable = false)
    private String username;

    // 用户密码
	@Column(nullable = false)
    private String password;

    // 用户的邮箱
    private String email;

    // 用户的主页
    private String home_url;

    // 用户显示的名称
    private String screen_name;

    // 用户注册时的GMT unix时间戳
    @Column(nullable = false)
    private Integer created;

    // 最后活动时间
    private Integer activated;

    // 上次登录最后活跃时间
    private Integer logged;

    // 用户组
    private String group_name;

}