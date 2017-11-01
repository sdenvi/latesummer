package com.latesummer.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.latesummer.common.domain.BaseModel;

import lombok.Data;

/**
 * 
 * @author Jenvi Sue
 *
 */
@Entity
@Table(name = "learn_resource")
public class LearnResouce  implements BaseModel<Integer>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
    private Integer id;
	private String author;
    private String title;
    private String url;
    
    public LearnResouce(String author, String title, String url) {
        this.author = author;
        this.title = title;
        this.url = url;
    }
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
