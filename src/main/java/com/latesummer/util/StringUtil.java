package com.latesummer.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 字符串处理工具类
 * @Author Jenvi Sue
 * @Date 2017/09/11 15:18
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils{

	public static Logger logger = Logger.getLogger(StringUtil.class);
	private static final String CHARSET_NAME = "UTF-8";
	private static final String NULL = "null";
	private static final int KEY_PRE = '.';
	private static final Pattern NUMBER_PATTERN = Pattern.compile("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
	private static final Pattern BLANK_PATTERN = Pattern.compile("\\s*|\t|\r|\n");

	/**
	 * 判断字符串是否为null、“ ”、“null”
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(String obj) {
		if (obj == null) {
			return true;
		} else if ("".equals(obj.toString().trim())) {
			return true;
		} else if (NULL.equals(obj.toString().trim().toLowerCase())) {
			return true;
		}

		return false;
	}

	/**
	 * 正则验证是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
        Pattern pattern = NUMBER_PATTERN;
        Matcher match = pattern.matcher(str);

		return match.matches();
	}

	/**
	 * 将一个长整数转换位字节数组(8个字节)，b[0]存储高位字符，大端
	 * 
	 * @param l 长整数
	 * @return 代表长整数的字节数组
	 */
	public static byte[] longToBytes(long l) {
		byte[] b = new byte[8];
		b[0] = (byte) (l >>> 56);
		b[1] = (byte) (l >>> 48);
		b[2] = (byte) (l >>> 40);
		b[3] = (byte) (l >>> 32);
		b[4] = (byte) (l >>> 24);
		b[5] = (byte) (l >>> 16);
		b[6] = (byte) (l >>> 8);
		b[7] = (byte) (l);
		return b;
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return (null != str) && (!"".equals(str.trim()));
	}

	/**
	 * 判断字符串为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (null == str) || ("".equals(str.trim()));
	}

	/**
	 * 大写转换为小写
	 * 
	 * @param str
	 * @return
	 */
	public static String lowerFirst(String str) {
		if (StringUtil.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}

	/**
	 * 小写转换为大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upperFirst(String str) {
		if (StringUtil.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 对象转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 对象转换为Float类型
	 * 
	 * @param val
	 * @return
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 对象转换为Long类型
	 * 
	 * @param val
	 * @return
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 对象转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 对象转换字符串
	 */
	public static String getStr(Object obj) {
		if (obj == null) {
			return "";
		} else {
			String str = obj.toString();
			if (str == null) {
				return "";
			} else {
				return str.trim();
			}
		}
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
            Pattern p = BLANK_PATTERN;
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 过滤特殊字符
	 */
	@SuppressWarnings("unused")
	public static String stringFilter(String str) {
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（） _——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String stt = m.replaceAll("").trim();
		String result = str.replaceAll("\\\\", "");
		return result;
	}
	
	/**
	 * 改变格式
	 */
	public static String changStr(String str) {
		if (str == null || "".equals(str) || str.length() <= 0) {
			return "";
		}
		str = str.replaceAll("\"", " ");
		return "\"" + str + "\"";
	}
	
	/**
	 * 转换为字符串
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 转换为字节数组
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : str.toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param fname
	 * @return
	 */
	public static String fileExt(String fname) {
		if ((isBlank(fname)) || (fname.indexOf(KEY_PRE) == -1)) {
			return null;
		}
		return fname.substring(fname.lastIndexOf(KEY_PRE) + 1);
	}

	/**
	 * 比较字符串是否相同
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
		if (null == str1) {
			return false;
		}
		return str1.equals(str2);
	}
	
	@SuppressWarnings("unused")
	private static boolean notIsDigit(int c) {
		return !Character.isDigit(c);
	}

	public static String alignRight(Object o, int width, char c) {
		if (null == o){
			return null;
        }
		String s = o.toString();
		int len = s.length();
		if (len >= width){
			return s;
        }
		return new StringBuilder().append(dup(c, width - len)).append(s).toString();
	}

	public static String alignLeft(Object o, int width, char c) {
		if (null == o){
			return null;
        }
		String s = o.toString();
		int length = s.length();
		if (length >= width){
			return s;
        }
		return new StringBuilder().append(s).append(dup(c, width - length)).toString();
	}

	public static String dup(char c, int num) {
		if ((c == 0) || (num < 1)){
			return "";
        }
		StringBuilder sb = new StringBuilder(num);
		for (int i = 0; i < num; i++){
			sb.append(c);
        }
		return sb.toString();
	}
}
