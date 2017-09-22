package com.latesummer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 附件
 * Create By Jenvi Sue On 2017年9月21日
 */
@Entity
@Data
@Table(name = "t_attach")
public class Attach {
	@Id
	@GeneratedValue
    private Integer id;
	@Column(nullable = false)
    private String fname;
    private String ftype;
    private String fkey;
    private Integer author_id;
    private Integer created;

}
