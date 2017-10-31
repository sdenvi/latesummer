package com.latesummer.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.latesummer.base.BaseEntity;

import lombok.Data;

/**
 * 
 * @author Jenvi Sue
 *
 */
@Entity
@Data
@Table(name = "learn_resource")
public class LearnResouce extends BaseEntity {
	@Id
	@GeneratedValue
    private Long id;
    private String author;
    private String title;
    private String url;
    
    public LearnResouce(String author, String title, String url) {
        this.author = author;
        this.title = title;
        this.url = url;
    }
}
