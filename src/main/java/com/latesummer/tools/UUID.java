package com.latesummer.tools;

import java.util.Arrays;
import java.util.Random;

public abstract class UUID {
	static Random r = new Random();

	private static final char[] _UU64 = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz"
			.toCharArray();
	private static final char[] _UU32 = "0123456789abcdefghijklmnopqrstuv".toCharArray();

	private static final char[] _UU16 = "0123456789abcdef".toCharArray();

	public static int random(int min, int max) {
		return r.nextInt(max - min + 1) + min;
	}

	public static String UU64() {
		return UU64(java.util.UUID.randomUUID());
	}

	public static String UU64(java.util.UUID uu) {
		int index = 0;
		char[] cs = new char[22];
		long L = uu.getMostSignificantBits();
		long R = uu.getLeastSignificantBits();
		long mask = 63L;

		for (int off = 58; off >= 4; off -= 6) {
			long hex = (L & mask << off) >>> off;
			cs[(index++)] = _UU64[((int) hex)];
		}

		int l = (int) ((L & 0xF) << 2 | (R & 0xC0000000) >>> 62);
		cs[(index++)] = _UU64[l];

		for (int off = 56; off >= 2; off -= 6) {
			long hex = (R & mask << off) >>> off;
			cs[(index++)] = _UU64[((int) hex)];
		}

		cs[(index++)] = _UU64[((int) (R & 0x3))];

		return new String(cs);
	}

	public static java.util.UUID fromUU64(String uu64) {
		String uu16 = UU16FromUU64(uu64);
		return java.util.UUID.fromString(UU(uu16));
	}

	public static String UU32(java.util.UUID uu) {
		StringBuilder sb = new StringBuilder();
		long m = uu.getMostSignificantBits();
		long l = uu.getLeastSignificantBits();
		for (int i = 0; i < 13; i++) {
			sb.append(_UU32[((int) (m >> (13 - i - 1) * 5) & 0x1F)]);
		}
		for (int i = 0; i < 13; i++) {
			sb.append(_UU32[((int) (l >> (13 - i - 1) * 5) & 0x1F)]);
		}
		return sb.toString();
	}

	public static String UU32() {
		return UU32(java.util.UUID.randomUUID());
	}

	public static java.util.UUID fromUU32(String u32) {
		return new java.util.UUID(parseUnsignedLong(u32.substring(0, 13), 32),
				parseUnsignedLong(u32.substring(13), 32));
	}

	public static long parseUnsignedLong(String s, int radix) {
		int len = s.length();
		long first = Long.parseLong(s.substring(0, len - 1), radix);
		int second = Character.digit(s.charAt(len - 1), radix);
		return first * radix + second;
	}

	public static String UU(String uu16) {
		StringBuilder sb = new StringBuilder();
		sb.append(uu16.substring(0, 8));
		sb.append('-');
		sb.append(uu16.substring(8, 12));
		sb.append('-');
		sb.append(uu16.substring(12, 16));
		sb.append('-');
		sb.append(uu16.substring(16, 20));
		sb.append('-');
		sb.append(uu16.substring(20));
		return sb.toString();
	}

	public static String UU16FromUU64(String uu64) {
		byte[] bytes = new byte[32];
		char[] cs = uu64.toCharArray();
		int index = 0;

		for (int i = 0; i < 10; i++) {
			int off = i * 2;
			char cl = cs[off];
			char cr = cs[(off + 1)];
			int l = Arrays.binarySearch(_UU64, cl);
			int r = Arrays.binarySearch(_UU64, cr);
			int n = l << 6 | r;
			bytes[(index++)] = ((byte) ((n & 0xF00) >>> 8));
			bytes[(index++)] = ((byte) ((n & 0xF0) >>> 4));
			bytes[(index++)] = ((byte) (n & 0xF));
		}

		char cl = cs[20];
		char cr = cs[21];
		int l = Arrays.binarySearch(_UU64, cl);
		int r = Arrays.binarySearch(_UU64, cr);
		int n = l << 2 | r;
		bytes[(index++)] = ((byte) ((n & 0xF0) >>> 4));
		bytes[(index++)] = ((byte) (n & 0xF));

		char[] names = new char[32];
		for (int i = 0; i < bytes.length; i++)
			names[i] = _UU16[bytes[i]];
		return new String(names);
	}

	public static String UU16() {
		return UU16(java.util.UUID.randomUUID());
	}

	public static String UU16(java.util.UUID uu) {
		return new StringBuilder().append(StringUtil.alignRight(Long.toHexString(uu.getMostSignificantBits()), 16, '0'))
				.append(StringUtil.alignRight(Long.toHexString(uu.getLeastSignificantBits()), 16, '0')).toString();
	}

	public static String captchaChar(int length) {
		return captchaChar(length, false);
	}

	public static String captchaChar(int length, boolean caseSensitivity) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		Random randdata = new Random();
		int data = 0;
		for (int i = 0; i < length; i++) {
			int index = rand.nextInt(caseSensitivity ? 3 : 2);

			switch (index) {
			case 0:
				data = randdata.nextInt(10);
				sb.append(data);
				break;
			case 1:
				data = randdata.nextInt(26) + 97;
				sb.append((char) data);
				break;
			case 2:
				data = randdata.nextInt(26) + 65;
				sb.append((char) data);
			}

		}

		return sb.toString();
	}

	public static String captchaNumber(int length) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
}