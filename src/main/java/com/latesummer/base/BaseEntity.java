package com.latesummer.base;

import java.io.Serializable;

import lombok.Data;

/**
 * Create By Jenvi Sue On 2017年10月25日
 */
@Data
public class BaseEntity implements Serializable{
  
	/**
     * 分页页码,默认页码为1
     */
    protected int page = 1;
    /**
     * 分页每页数量,默认10条
     */
    protected int size = 10;

    /**
     * 排序列名称,默认为id
     */
    protected String sidx = "id";

    /**
     * 排序正序
     */
    protected String sord = "asc";
}