package com.latesummer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.latesummer.utils.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志记录
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
@Entity
@Table(name = "t_logs") 
public class Logs {
	@Id
	@GeneratedValue
    // 项目主键
	@Column(unique = true)
    private Integer id;

    // 产生的动作
	@Column(nullable = false)
    private String action;

    // 产生的数据
    private String data;

    // 发生人id
    @Column(nullable = false)
    private Integer author_id;

    // 日志产生的ip
    private String ip;

    // 日志创建时间
    @Column(nullable = false)
    private Integer created;

    public Logs(String action, String data, String ip, Integer uid) {
        this.action = action;
        this.data = data;
        this.ip = ip;
        this.author_id = uid;
        this.created = DateUtil.nowUnix();
    }

}