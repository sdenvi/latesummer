package com.latesummer.utils.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import java.io.File;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;


public abstract interface Response {
	public abstract int statusCode();

	public abstract Response status(int paramInt);

	public default Response badRequest() {
		return status(400);
	}

	public default Response unauthorized() {
		return status(401);
	}

	public default Response notFound() {
		return status(404);
	}

	public abstract Response contentType(String paramString);

	public abstract String contentType();

	public abstract Map<String, String> headers();

	public abstract Response header(String paramString1, String paramString2);

	public abstract Map<String, String> cookies();

	public abstract Response cookie(Cookie paramCookie);

	public abstract Response cookie(String paramString1, String paramString2);

	public abstract Response cookie(String paramString1, String paramString2, int paramInt);

	public abstract Response cookie(String paramString1, String paramString2, int paramInt, boolean paramBoolean);

	public abstract Response cookie(String paramString1, String paramString2, String paramString3, int paramInt,
			boolean paramBoolean);

	public abstract Response removeCookie(String paramString);

	public default void text(String text) {
		if (null == text)
			return;
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.valueOf(statusCode()), Unpooled.wrappedBuffer(text.getBytes(CharsetUtil.UTF_8)));
		contentType("text/plain; charset=UTF-8");
		send(response);
	}

	public default void html(String html) {
		if (null == html)
			return;
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.valueOf(statusCode()), Unpooled.wrappedBuffer(html.getBytes(CharsetUtil.UTF_8)));
		contentType("text/html; charset=UTF-8");
		send(response);
	}

	public default void json(String json) {
		if (null == json)
			return;
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.valueOf(statusCode()), Unpooled.wrappedBuffer(json.getBytes(CharsetUtil.UTF_8)));
		/*if (!WebContext.request().isIE()) {
			contentType("application/json; charset=UTF-8");
		}*/
		send(response);
	}

	/*public default void json(Object bean) {
		if (null == bean)
			return;
		json(JsonKit.toString(bean));
	}*/

	public default void body(String data) {
		if (null == data)
			return;
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.valueOf(statusCode()), Unpooled.wrappedBuffer(data.getBytes(CharsetUtil.UTF_8)));
		send(response);
	}

	public default void body(byte[] data) {
		if (null == data)
			return;
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.valueOf(statusCode()), Unpooled.wrappedBuffer(data));
		send(response);
	}

	public default void body(ByteBuf byteBuf) {
		if (null == byteBuf)
			return;
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.valueOf(statusCode()), byteBuf);
		send(response);
	}

	public abstract void download(String paramString, File paramFile) throws Exception;

	//public abstract OutputStreamWrapper outputStream() throws IOException;

	public default void render(String view) {
		if (null == view)
			return;
		render(new ModelAndView(view));
	}

	public abstract void render(ModelAndView paramModelAndView);

	public abstract void redirect(String paramString);

	public abstract boolean isCommit();

	public abstract void send(FullHttpResponse paramFullHttpResponse);
}