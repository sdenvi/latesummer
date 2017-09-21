package com.latesummer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * 附件
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
@Entity
public class Attach {
	@Id
	@GeneratedValue
    private Integer id;
	@Column(nullable = true)
    private String fname;
	@Column(nullable = false)
    private String ftype;
	@Column(nullable = false)
    private String fkey;
	@Column(nullable = false)
    private Integer author_id;
	@Column(nullable = false)
    private Integer created;

}
