package com.latesummer.domain;

import java.io.Serializable;

/**
 * 返回消息对象.
 * @Author Jenvi Sue
 * @Date 2017/12/12 9:30
 */
public class WeatherResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 消息数据
	 */
	private Weather data;
	/**
	 * 消息状态
	 */
	private String status;
	/**
	 * 消息描述
	 */
	private String desc;

	public Weather getData() {
		return data;
	}

	public void setData(Weather data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
