package com.latesummer.common.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.latesummer.utils.Constants;


/**
 * 需要标记删除的实体继承该类
 * @author sdenvi
 */
@MappedSuperclass
public abstract class MarkDeleteableModel<ID extends Serializable> extends AbstractBaseModel<ID>{

	private static final long serialVersionUID = -1880548221110317053L;
	
	@Column(name = "del", columnDefinition = "tinyint default 0")
	private int del = Constants.DEL_NO;

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
