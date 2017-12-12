package com.latesummer.util;

import java.util.Random;

/**
 * @Author Jenvi Sue
 * @Date 2017/10/12 10:35
 */
public class CalculateUtil {

	/**
	 * 随机码字典集
	 */
	private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * 取某个范围的任意数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getNext(int min, int max) {
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	/**
	 * 取某个范围的任意数
	 * 
	 * @param max
	 * @return
	 */
	public static int getNext(int max) {
		Random random = new Random();
		int s = random.nextInt(max);
		return s;
	}

	/**
	 * 生成sum位随机码
	 * 
	 * @return
	 */
	public static String generateDigitRandomCode(int sum) {
		Random rd = new Random();
		String n = "";
		int getNum;
		do {
			// 产生数字0-9的随机数
			getNum = Math.abs(rd.nextInt(Integer.MAX_VALUE)) % 10 + 48;
			char num1 = (char) getNum;
			String dn = Character.toString(num1);
			n += dn;
		} while (n.length() < sum);

		return n;
	}

	/**
	 * 生成sum位数字字母随机码
	 * 
	 * @param sum
	 * @return
	 */
	public static String generateMixRandomCode(int sum) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < sum; ++i) {
			// [0,62)
			int number = random.nextInt(62);

			sb.append(RANDOM_STR.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成验证码(4位)
	 */
	public static String createCharacter() {
		char[] codeSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0, count = 4; i < count; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);
			s.append(r);
		}
		return s.toString();
	}

	/**
	 * 随机数(字母数字组合)
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = null;
		String strTable = numberFlag ? "1234567890" : "abcdefghijklmnopqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

}
