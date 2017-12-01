package com.latesummer.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServletUtil {

	// 服务器标识
	@SuppressWarnings("unused")
	private static String hostName = "";

	// 响应的ContentType内容
	private static final String RESPONSE_CONTENTTYPE = "application/json";

	// 响应编码
	private static final String RESPONSE_CHARACTERENCODING = "utf-8";

	// 业务名称的缩写
	private static final String BIZ_NAME = "";

	private static Logger log = Logger.getLogger(ServletUtil.class);

	static {
		try {
			InetAddress netAddress = InetAddress.getLocalHost();
			hostName = netAddress.getHostName();
		} catch (UnknownHostException e) {
			log.error("netAddress.getHostName failed", e);
		}
	}

	/**
	 * 生成系统异常错误报文
	 * 
	 * @param response
	 */
	public static String createSysErrorResponse(HttpServletResponse response) {
		final String code = "INTERNAL_SERVER_ERROR";
		String message = "服务器内部错误";
		return createErrorResponse(500, 500, code, message, response);
	}

	/**
	 * 生成参数不正确报文
	 * 
	 * @param response
	 */
	public static String createParamErrorResponse(HttpServletResponse response) {
		final String code = "REQUIRE_ARGUMENT";
		String message = "缺少参数";
		return createErrorResponse(400, 400, code, message, response);
	}

	/**
	 * 生成参数不正确报文
	 * 
	 * @param param
	 *            缺少的参数名称
	 * @param response
	 */
	public static String createParamErrorResponse(String param, HttpServletResponse response) {
		final String code = "REQUIRE_ARGUMENT";
		String message = "缺少参数：" + param;
		return createErrorResponse(400, 400, code, message, response);
	}

	/**
	 * 认证失败
	 * 
	 * @param response
	 */
	public static String createAuthorizationErrorResponse(HttpServletResponse response) {
		final String code = "AUTH_INVALID_TOKEN";
		String message = "请求认证失败！请按规范在Header报文头中附上正确的Authorization认证属性!";
		return createErrorResponse(401, 401, code, message, response);
	}

	/**
	 * 授权失败
	 * 
	 * @param response
	 */
	public static String createAuthorizeErrorResponse(HttpServletResponse response) {
		final String code = "AUTH_DENIED";
		String message = "请求失败，没有访问或操作该资源的权限!";
		return createErrorResponse(403, 403, code, message, response);
	}

	/**
	 * 授权失败
	 * 
	 * @param response
	 */
	public static String createAuthorizeErrorResponse(HttpServletResponse response, String message) {
		final String code = "AUTH_DENIED";
		return createErrorResponse(403, 403, code, message, response);
	}

	/**
	 * 路径不存在
	 * 
	 * @param response
	 */
	public static String createNotFoundErrorResponse(HttpServletResponse response) {
		final String code = "NOT_FOUND";
		String message = "请求的URL路径不存在!";
		return createErrorResponse(404, 404, code, message, response);
	}

	/**
	 * 生成错误报文
	 * 
	 * @param httpStatus
	 * @param code
	 * @param message
	 * @param response
	 */
	public static String createErrorResponse(Integer httpStatus, Object code, String message,
			HttpServletResponse response) {
		code = BIZ_NAME + code;
		PrintWriter printWriter = null;
		String jsonString = "";
		try {
			response.setCharacterEncoding(RESPONSE_CHARACTERENCODING);
			response.setContentType(RESPONSE_CONTENTTYPE);
			response.setStatus(httpStatus);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", code);
			map.put("message", message);
			// map.put("request_id", requestId==null?"":requestId);
			// map.put("host_id", hostName);
			map.put("server_time", DateUtil.formatISO8601DateString(new Date()));

			printWriter = response.getWriter();
			jsonString = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
			printWriter.write(jsonString);
			printWriter.flush();
		} catch (Exception e) {
			log.error("createResponse failed", e);
		} finally {
			if (null != printWriter)
				printWriter.close();
		}

		return jsonString;
	}

	/**
	 * 生成错误报文
	 * 
	 * @param httpStatus
	 * @param res_code
	 * @param code
	 * @param message
	 * @param response
	 * @return
	 */
	public static String createErrorResponse(Integer httpStatus, Integer res_code, Object code, String message,
			HttpServletResponse response) {
		code = BIZ_NAME + code;
		PrintWriter printWriter = null;
		String jsonString = "";
		try {
			response.setCharacterEncoding(RESPONSE_CHARACTERENCODING);
			response.setContentType(RESPONSE_CONTENTTYPE);
			response.setStatus(httpStatus);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", code);
			map.put("message", message);
			// map.put("request_id", requestId==null?"":requestId);
			// map.put("host_id", hostName);
			map.put("res_code", res_code);
			map.put("server_time", DateUtil.formatISO8601DateString(new Date()));

			printWriter = response.getWriter();
			jsonString = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
			printWriter.write(jsonString);
			printWriter.flush();
		} catch (Exception e) {
			log.error("createResponse failed", e);
		} finally {
			if (null != printWriter)
				printWriter.close();
		}

		return jsonString;
	}

	/**
	 * 生成成功报文
	 * 
	 * @param httpCode
	 * @param result
	 * @param response
	 */
	public static String createSuccessResponse(Integer httpCode, Object result, HttpServletResponse response) {
		return createSuccessResponse(httpCode, result, SerializerFeature.WriteMapNullValue, null, response);
	}

	public static String createSuccessResponse(Integer httpCode, String message, Object result,
			HttpServletResponse response) {
		return createSuccessResponse(httpCode, message, result, SerializerFeature.WriteMapNullValue, null, response);
	}

	/**
	 * 生成登录传成功报文
	 * 
	 * @param httpCode
	 * @param result
	 * @param response
	 */
	public static String createLoginSuccessResponse(Integer httpCode, Object result, HttpServletResponse response) {
		/*
		 * response.setHeader("Access-Control-Allow-Origin", "*"); response.setHeader(
		 * "Access-Control-Allow-Methods","POST, GET, PATCH, DELETE, PUT");
		 * response.setHeader("Access-Control-Allow-Headers",request.getHeader(
		 * "Access-Control-Request-Headers"));
		 */
		return createSuccessResponse(httpCode, result, SerializerFeature.WriteMapNullValue, null, response);
	}

	public static String createSuccessResponse(Integer httpCode, Object result, SerializeFilter filter,
			HttpServletResponse response) {
		return createSuccessResponse(httpCode, result, SerializerFeature.PrettyFormat, filter, response);
	}

	public static String createSuccessResponse(Integer httpCode, Object result, SerializerFeature serializerFeature,
			HttpServletResponse response) {
		return createSuccessResponse(httpCode, result, serializerFeature, null, response);
	}

	public static String createSuccessResponse(Integer httpCode, Object result, SerializerFeature serializerFeature,
			SerializeFilter filter, HttpServletResponse response) {
		PrintWriter printWriter = null;
		String jsonString = "";
		try {
			response.setCharacterEncoding(RESPONSE_CHARACTERENCODING);
			response.setContentType(RESPONSE_CONTENTTYPE);
			response.setStatus(httpCode);
			printWriter = response.getWriter();
			if (null != result) {
				if (null != filter) {
					jsonString = JSONObject.toJSONString(result, filter, serializerFeature);
				} else {
					// jsonString = JSONObject.toJSONString(result, serializerFeature);
					jsonString = JSONObject.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:ss:mm",
							serializerFeature);
				}
				printWriter.write(jsonString);
			}
			printWriter.flush();

		} catch (Exception e) {
			log.error("createResponse failed", e);
		} finally {
			if (null != printWriter)
				printWriter.close();
		}
		return jsonString;
	}

	public static String createSuccessResponse(Integer httpCode, String message, Object result,
			SerializerFeature serializerFeature, SerializeFilter filter, HttpServletResponse response) {
		PrintWriter printWriter = null;
		String jsonString = "";
		try {

			response.setCharacterEncoding(RESPONSE_CHARACTERENCODING);
			response.setContentType(RESPONSE_CONTENTTYPE);
			response.setStatus(httpCode);
			printWriter = response.getWriter();
			SerializeConfig config = new SerializeConfig();
			config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
			Map<String, Object> map = new HashMap<String, Object>();
			if (null != result) {
				map.put("res_code", httpCode);
				map.put("message", message);
				map.put("data", result);
				if (null != filter) {
					jsonString = JSONObject.toJSONString(map, filter, serializerFeature);
				} else {
					// jsonString = JSONObject.toJSONString(map,config,serializerFeature);
					jsonString = JSONObject.toJSONStringWithDateFormat(map, "yyyy-MM-dd");

				}
				printWriter.write(jsonString);
			}
			printWriter.flush();

		} catch (Exception e) {
			log.error("createResponse failed", e);
		} finally {
			if (null != printWriter)
				printWriter.close();
		}
		return jsonString;
	}

	/**
	 * 获取报文IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if ((StringUtil.isBlank(ipAddress)) || ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if ((StringUtil.isBlank(ipAddress)) || ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((StringUtil.isBlank(ipAddress)) || ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("X-Real-IP");
		}
		if (StringUtil.isBlank(ipAddress)) {
			ipAddress = "127.0.0.1";
		}
		return ipAddress;
	}
	
	/**
	 * IP 地址转换成 long 数据
	 * @param ipAddress
	 * @return
	 */
	public static long ipAddressToLong(String ipAddress) {
		long ipInt = 0;
		if (ValidatorUtil.isIPv4Address(ipAddress)) {
			String[] ipArr = ipAddress.split("\\.");
			if (ipArr.length == 3) {
				ipAddress = ipAddress + ".0";
			}
			ipArr = ipAddress.split("\\.");
			long p1 = Long.parseLong(ipArr[0]) * 256 * 256 * 256;
			long p2 = Long.parseLong(ipArr[1]) * 256 * 256;
			long p3 = Long.parseLong(ipArr[2]) * 256;
			long p4 = Long.parseLong(ipArr[3]);
			ipInt = p1 + p2 + p3 + p4;
		}
		return ipInt;
	}

	/**
	 * 获取带参数的URL串
	 */
	public static String getUrlWithParams(HttpServletRequest request) {
		String url = request.getRequestURI();

		if (!StringUtil.isNull(request.getQueryString())) {
			url = url + "?" + request.getQueryString();
		}

		return url;
	}

	/**
	 * 获取AccessToken
	 * 
	 * @param request
	 * @return
	 */
	public static String getAccessToken(HttpServletRequest request) {
		String accessToken = null;

		String authorization = request.getHeader("Authorization");
		if (StringUtil.isNull(authorization)) {
			return accessToken;
		}

		if (authorization.startsWith("MAC")) {
			Pattern p = Pattern.compile("MAC id=\"(.*)\",nonce=\"(.*)\",mac=\"(.*)\"");
			Matcher m = p.matcher(authorization);
			if (m.find() && !StringUtil.isNull(m.group(1))) {
				return m.group(1);
			}
		} else if (authorization.startsWith("Bearer")) {
			Pattern p = Pattern.compile("Bearer \"(.*)\"");
			Matcher m = p.matcher(authorization);
			if (m.find() && !StringUtil.isNull(m.group(1))) {
				return m.group(1);
			}
		}

		return accessToken;
	}

	/**
	 * 判断是否是Mac Token
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isExistMacToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (!StringUtil.isNull(authorization) && authorization.startsWith("MAC id=")) {
			return true;
		}

		return false;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header. 根据浏览器的不同设置不同的编码格式 防止中文乱码
	 * 
	 * @param fileName 下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response,
			String fileName) {
		try {
			// 中文文件名支持
			String encodedfileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
			response.setContentType("application/force-download");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
