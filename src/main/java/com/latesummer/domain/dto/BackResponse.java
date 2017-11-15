package com.latesummer.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
public class BackResponse implements Serializable {

	private static final long serialVersionUID = 664292680342779659L;
	
	private String attach_path;
    private String theme_path;
    private String sql_path;

}
