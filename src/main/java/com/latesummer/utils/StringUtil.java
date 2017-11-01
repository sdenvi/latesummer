package com.latesummer.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * Create By Jenvi Sue On 2017年9月21日
 */
public class StringUtil {
	
	private static final Random random = new Random();

	/**
	 * 判断字符串是否为null、“ ”、“null”
	 * @param obj
	 * @return
	 */
	public static boolean isNull(String obj) {
		if (obj == null){
			return true;
		}else if (obj.toString().trim().equals("")){
			return true;
		}else if(obj.toString().trim().toLowerCase().equals("null")){
			return true;
		}
		
		return false;
	}

	/**
	 * 正则验证是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
		Matcher match = pattern.matcher(str);
		
		return match.matches();
	}
    /** 
     * 将一个长整数转换位字节数组(8个字节)，b[0]存储高位字符，大端 
     *  
     * @param l  长整数 
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
	 * 获取某个范围内的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int rand(int min, int max) {
		return random.nextInt(max) % (max - min + 1) + min;
	}

	/**
	 * 获取固定位数的随机数
	 * @param size
	 * @return
	 */
	public static String rand(int size) {
		String num = "";
		for (int i = 0; i < size; i++) {
			double a = Math.random() * 9.0D;
			a = Math.ceil(a);
			int randomNum = new Double(a).intValue();
			num = new StringBuilder().append(num).append(randomNum).toString();
		}
		return num;
	}

	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return (null != str) && (!"".equals(str.trim()));
	}

	/**
	 * 判断字符串为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (null == str) || ("".equals(str.trim()));
	}

	@SuppressWarnings("unused")
	private static boolean notIsDigit(int c) {
		return !Character.isDigit(c);
	}

	public static String alignRight(Object o, int width, char c) {
		if (null == o)
			return null;
		String s = o.toString();
		int len = s.length();
		if (len >= width)
			return s;
		return new StringBuilder().append(dup(c, width - len)).append(s).toString();
	}

	public static String alignLeft(Object o, int width, char c) {
		if (null == o)
			return null;
		String s = o.toString();
		int length = s.length();
		if (length >= width)
			return s;
		return new StringBuilder().append(s).append(dup(c, width - length)).toString();
	}

	public static String dup(char c, int num) {
		if ((c == 0) || (num < 1))
			return "";
		StringBuilder sb = new StringBuilder(num);
		for (int i = 0; i < num; i++)
			sb.append(c);
		return sb.toString();
	}

	/**
	 * 获取文件后缀名
	 * @param fname
	 * @return
	 */
	public static String fileExt(String fname) {
		if ((isBlank(fname)) || (fname.indexOf('.') == -1)) {
			return null;
		}
		return fname.substring(fname.lastIndexOf('.') + 1);
	}

	/**
	 * 比较字符串是否相同
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
}
