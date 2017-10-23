package com.latesummer.utils.http;

public class Cookie {
	private String name = null;
	private String value = null;
	private String domain = "";
	private String path = "/";
	private long maxAge = -1L;
	private boolean secure = false;
	private boolean httpOnly = false;

	public String name() {
		return this.name;
	}

	public void name(String name) {
		this.name = name;
	}

	public String value() {
		return this.value;
	}

	public void value(String value) {
		this.value = value;
	}

	public String domain() {
		return this.domain;
	}

	public void domain(String domain) {
		this.domain = domain;
	}

	public String path() {
		return this.path;
	}

	public void path(String path) {
		this.path = path;
	}

	public long maxAge() {
		return this.maxAge;
	}

	public void maxAge(long maxAge) {
		this.maxAge = maxAge;
	}

	public boolean secure() {
		return this.secure;
	}

	public void secure(boolean secure) {
		this.secure = secure;
	}

	public boolean httpOnly() {
		return this.httpOnly;
	}

	public void httpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
	}
}