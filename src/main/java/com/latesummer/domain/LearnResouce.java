package com.latesummer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.latesummer.common.domain.BaseModel;

/**
 * Create By Jenvi Sue On 2017年11月21日
 */
@Entity
@Table(name = "learn_resource")
public class LearnResouce implements BaseModel<Long>{

	private static final long serialVersionUID = -91221104520172449L;
	
	@Id
	@GeneratedValue
    private Long id;
	private String author;
    private String title;
    private String url;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
