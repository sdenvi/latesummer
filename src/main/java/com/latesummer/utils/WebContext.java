package com.latesummer.utils;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import io.netty.util.concurrent.FastThreadLocal;

public class WebContext {
	private static final FastThreadLocal<WebContext> fastThreadLocal = new FastThreadLocal();
	private static String contextPath;
	private Request request;
	private Response response;

	public WebContext(Request request, Response response) {
		this.request = request;
		this.response = response;
	}

	public static void set(WebContext webContext) {
		fastThreadLocal.set(webContext);
	}

	public static WebContext get() {
		return (WebContext) fastThreadLocal.get();
	}

	public static void remove() {
		fastThreadLocal.remove();
	}

	public static Request request() {
		WebContext webContext = get();
		return null != webContext ? webContext.request : null;
	}

	public static Response response() {
		WebContext webContext = get();
		return null != webContext ? webContext.response : null;
	}

	public static String contextPath() {
		return contextPath;
	}
}