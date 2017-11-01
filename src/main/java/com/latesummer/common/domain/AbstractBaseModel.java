package com.latesummer.common.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 * 提取了部分公共字段，可以避免多个实体重复设置这些属性
 * Create By Jenvi Sue On 2017年10月31日
 */
@MappedSuperclass
@Data
public abstract class AbstractBaseModel<ID extends Serializable> implements BaseModel<ID>{

	private static final long serialVersionUID = 1195969732659409799L;

	@ApiModelProperty(value="创建者ID")
	@Column(columnDefinition="int default 0")
	private int creator = 0;
	
	@ApiModelProperty(value="创建时间")
	@Column(name="create_time")
	private Timestamp createTime;
	
	@ApiModelProperty(value="最后修改人")
	@Column(columnDefinition="int default 0")
	private int updator = 0;
	
	@ApiModelProperty(value="最后修改时间")
	@Column(name="update_time")
	private Timestamp updateTime;

}
