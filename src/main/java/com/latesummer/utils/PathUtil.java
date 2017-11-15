package com.latesummer.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public final class PathUtil {
	public static final String VAR_REGEXP = ":(\\w+)";
	public static final String VAR_REPLACE = "([^#/?.]+)";
	public static final String SLASH = "/";

	public static String getRelativePath(String path, String contextPath) {
		contextPath = null == contextPath ? "" : contextPath;
		path = path.substring(contextPath.length());

		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return path;
	}

	public static List<String> convertRouteToList(String route) {
		String[] pathArray = route.split("/");
		if ((null != pathArray) && (pathArray.length > 0)) {
			List<String> path = new ArrayList<String>();
			for (String p : pathArray) {
				if (p.length() > 0) {
					path.add(p);
				}
			}
			return path;
		}
		return new ArrayList<String>(0);
	}

	public static boolean isParam(String routePart) {
		return routePart.startsWith(":");
	}

	public static boolean isSplat(String routePart) {
		return routePart.equals("*");
	}

	public static String fixPath(String path) {
		if (null == path) {
			return "/";
		}
		if (path.charAt(0) != '/') {
			path = "/" + path;
		}
		if ((path.length() > 1) && (path.endsWith("/"))) {
			path = path.substring(0, path.length() - 1);
		}
		if (!path.contains("\\s")) {
			return path;
		}
		return path.replaceAll("\\s", "%20");
	}

	public static String cleanPath(String path) {
		if (path == null) {
			return null;
		}
		return path.replaceAll("[/]+", "/");
	}
}