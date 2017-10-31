package com.latesummer.common.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 提取了部分公共字段，可以避免多个实体重复设置这些属性
 * Create By Jenvi Sue On 2017年10月31日
 */
@MappedSuperclass
public abstract class AbstractBaseModel<ID extends Serializable> implements BaseModel<ID>{

	private static final long serialVersionUID = 1195969732659409799L;

	@Column(columnDefinition="int default 0")
	private int creator = 0;
	
	@Column(name="create_time")
	private Timestamp createTime;
	
	@Column(columnDefinition="int default 0")
	private int updator = 0;
	
	@Column(name="update_time")
	private Timestamp updateTime;

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getUpdator() {
		return updator;
	}

	public void setUpdator(int updator) {
		this.updator = updator;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
