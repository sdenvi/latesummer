package com.latesummer.utils.http;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpMethod;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.Session;
import org.apache.tomcat.util.http.fileupload.FileItem;

import com.latesummer.utils.ServletUtil;
import com.latesummer.utils.StringUtil;

import lombok.NonNull;

public abstract interface Request {
	public abstract Request initPathParams(Route paramRoute);

	public abstract String host();

	public abstract String uri();

	public abstract String url();

	public default String userAgent() {
		return header("User-Agent");
	}

	public abstract String protocol();

	public default String contextPath() {
		return WebContext.contextPath();
	}

	public abstract Map<String, String> pathParams();

	public default String pathString(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		return (String) pathParams().get(name);
	}

	public default Integer pathInt(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		String val = pathString(name);
		return StringUtil.isNotBlank(val) ? Integer.valueOf(Integer.parseInt(val)) : null;
	}

	public default Long pathLong(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		String val = pathString(name);
		return StringUtil.isNotBlank(val) ? Long.valueOf(Long.parseLong(val)) : null;
	}

	public abstract String queryString();

	public abstract Map<String, List<String>> parameters();

	public default Optional<Object> query(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		List values = (List) parameters().get(name);
		if ((null != values) && (values.size() > 0))
			return Optional.of(values.get(0));
		return Optional.empty();
	}

	public default String query(@NonNull String name, @NonNull String defaultValue) {
		if (name == null)
			throw new NullPointerException("name");
		if (defaultValue == null)
			throw new NullPointerException("defaultValue");
		Optional value = query(name);
		if (value.isPresent())
			return (String) value.get();
		return defaultValue;
	}

	public default Optional<Integer> queryInt(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		Optional value = query(name);
		if (value.isPresent())
			return Optional.of(Integer.valueOf(Integer.parseInt((String) value.get())));
		return Optional.empty();
	}

	public default int queryInt(@NonNull String name, int defaultValue) {
		if (name == null)
			throw new NullPointerException("name");
		Optional value = query(name);
		if (value.isPresent())
			return Integer.parseInt((String) value.get());
		return defaultValue;
	}

	public default Optional<Long> queryLong(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		Optional value = query(name);
		if (value.isPresent())
			return Optional.of(Long.valueOf(Long.parseLong((String) value.get())));
		return Optional.empty();
	}

	public default long queryLong(@NonNull String name, long defaultValue) {
		if (name == null)
			throw new NullPointerException("name");
		Optional value = query(name);
		if (value.isPresent())
			return Long.parseLong((String) value.get());
		return defaultValue;
	}

	public default Optional<Double> queryDouble(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		Optional value = query(name);
		if (value.isPresent())
			return Optional.of(Double.valueOf(Double.parseDouble((String) value.get())));
		return Optional.empty();
	}

	public default double queryDouble(@NonNull String name, double defaultValue) {
		if (name == null)
			throw new NullPointerException("name");
		Optional value = query(name);
		if (value.isPresent())
			return Double.parseDouble((String) value.get());
		return defaultValue;
	}

	public abstract String method();

	public abstract HttpMethod httpMethod();

	public default String address() {
		return ServletUtil.ipAddr(this);
	}

	public abstract Session session();

	public default String contentType() {
		String contentType = header("Content-Type");
		return null != contentType ? contentType : "Unknown";
	}

	public abstract boolean isSecure();

	public default boolean isAjax() {
		return "XMLHttpRequest".equals(header("x-requested-with"));
	}

	public default boolean isIE() {
		String ua = userAgent();
		return (ua.contains("MSIE")) || (ua.contains("TRIDENT"));
	}

	public abstract Map<String, String> cookies();

	public default Optional<String> cookie(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		String value = (String) cookies().getOrDefault(name, "");
		if (value.length() > 0) {
			return Optional.of(value);
		}
		return Optional.empty();
	}

	public abstract Optional<Cookie> cookieRaw(String paramString);

	public default String cookie(@NonNull String name, @NonNull String defaultValue) {
		if (name == null)
			throw new NullPointerException("name");
		if (defaultValue == null)
			throw new NullPointerException("defaultValue");
		return cookie(name).isPresent() ? (String) cookie(name).get() : defaultValue;
	}

	public abstract Request cookie(Cookie paramCookie);

	public abstract Map<String, String> headers();

	public default String header(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		return (String) headers().getOrDefault(name, "");
	}

	public default String header(@NonNull String name, @NonNull String defaultValue) {
		if (name == null)
			throw new NullPointerException("name");
		if (defaultValue == null)
			throw new NullPointerException("defaultValue");
		String value = header(name);
		return value.length() > 0 ? value : defaultValue;
	}

	public abstract boolean keepAlive();

	public abstract Map<String, Object> attributes();

	public default Request attribute(@NonNull String name, Object value) {
		if (name == null)
			throw new NullPointerException("name");
		if (null != value)
			attributes().put(name, value);
		return this;
	}

	public default <T> Object attribute(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		Object object = attributes().get(name);
		return null != object ? object : null;
	}

	public abstract Map<String, FileItem> fileItems();

	public default Optional<FileItem> fileItem(@NonNull String name) {
		if (name == null)
			throw new NullPointerException("name");
		return Optional.ofNullable(fileItems().get(name));
	}

	public abstract ByteBuf body();

	public abstract String bodyToString();
}