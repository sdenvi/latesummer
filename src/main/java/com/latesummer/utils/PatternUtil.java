package com.latesummer.utils;

import java.util.regex.Pattern;

/**
 * 使用正则表达式进行数据校验
 * Create By Jenvi Sue On 2017年9月21日
 */
public final class PatternUtil {
	public static boolean isEmail(String email) {
		String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
		return Pattern.matches(regex, email);
	}

	public static boolean isIdCard(String idCard) {
		String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
		return Pattern.matches(regex, idCard);
	}

	public static boolean isImage(String suffix) {
		if ((null != suffix) && (!"".equals(suffix))) {
			String regex = "(.*?)(?i)(jpg|jpeg|png|gif|bmp|webp)";
			return Pattern.matches(regex, suffix);
		}
		return false;
	}

	public static boolean isMobile(String mobile) {
		String regex = "(\\+\\d+)?1[34578]\\d{9}$";
		return Pattern.matches(regex, mobile);
	}

	public static boolean isPhone(String phone) {
		String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
		return Pattern.matches(regex, phone);
	}

	public static boolean isDigit(String digit) {
		String regex = "\\-?[1-9]\\d+";
		return Pattern.matches(regex, digit);
	}

	public static boolean isDecimals(String decimals) {
		String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
		return Pattern.matches(regex, decimals);
	}

	public static boolean isBlankSpace(String blankSpace) {
		String regex = "\\s+";
		return Pattern.matches(regex, blankSpace);
	}

	public static boolean isNumber(String str) {
		String regex = "^[1-9]\\d*$";
		return Pattern.matches(regex, str);
	}

	public static boolean isStudentNum(String num) {
		String regex = "^[A-Za-z0-9-_]+$";
		return Pattern.matches(regex, num);
	}

	public static boolean isBirthday(String birthday) {
		String regex = "^(\\d{4})-(\\d{2})-(\\d{2})$";
		return Pattern.matches(regex, birthday);
	}

	public static boolean isURL(String url) {
		String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
		return Pattern.matches(regex, url);
	}

	public static boolean isPostcode(String postcode) {
		String regex = "[1-9]\\d{5}";
		return Pattern.matches(regex, postcode);
	}

	public static boolean isIpAddress(String ipAddress) {
		String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
		return Pattern.matches(regex, ipAddress);
	}
}