package com.latesummer.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class ConvertUtil {
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	private ConvertUtil() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	public static String bytes2HexString(byte[] bytes) {
		if (bytes == null)
			return null;
		int len = bytes.length;
		if (len <= 0)
			return null;
		char[] ret = new char[len << 1];
		int i = 0;
		for (int j = 0; i < len; i++) {
			ret[(j++)] = hexDigits[(bytes[i] >>> 4 & 0xF)];
			ret[(j++)] = hexDigits[(bytes[i] & 0xF)];
		}
		return new String(ret);
	}

	public static byte[] hexString2Bytes(String hexString) {
		if (isSpace(hexString))
			return null;
		int len = hexString.length();
		if (len % 2 != 0) {
			hexString = new StringBuilder().append("0").append(hexString).toString();
			len += 1;
		}
		char[] hexBytes = hexString.toUpperCase().toCharArray();
		byte[] ret = new byte[len >> 1];
		for (int i = 0; i < len; i += 2) {
			ret[(i >> 1)] = ((byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[(i + 1)])));
		}
		return ret;
	}

	public static int hex2Dec(char hexChar) {
		if ((hexChar >= '0') && (hexChar <= '9'))
			return hexChar - '0';
		if ((hexChar >= 'A') && (hexChar <= 'F')) {
			return hexChar - 'A' + 10;
		}
		throw new IllegalArgumentException();
	}

	public static byte[] chars2Bytes(char[] chars) {
		if ((chars == null) || (chars.length <= 0))
			return null;
		int len = chars.length;
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			bytes[i] = ((byte) chars[i]);
		}
		return bytes;
	}

	public static char[] bytes2Chars(byte[] bytes) {
		if (bytes == null)
			return null;
		int len = bytes.length;
		if (len <= 0)
			return null;
		char[] chars = new char[len];
		for (int i = 0; i < len; i++) {
			chars[i] = ((char) (bytes[i] & 0xFF));
		}
		return chars;
	}

	public static long memorySize2Byte(long memorySize, int unit) {
		if (memorySize < 0L)
			return -1L;
		return memorySize * unit;
	}

	public static double byte2MemorySize(long byteNum, int unit) {
		if (byteNum < 0L)
			return -1.0D;
		return byteNum / unit;
	}

	public static String byte2FitMemorySize(long byteNum) {
		if (byteNum < 0L)
			return "shouldn't be less than zero!";
		if (byteNum < 1024L)
			return String.format("%.3fB", new Object[] { Double.valueOf(byteNum) });
		if (byteNum < 1048576L)
			return String.format("%.3fKB", new Object[] { Double.valueOf(byteNum / 1024.0D) });
		if (byteNum < 1073741824L) {
			return String.format("%.3fMB", new Object[] { Double.valueOf(byteNum / 1048576.0D) });
		}
		return String.format("%.3fGB", new Object[] { Double.valueOf(byteNum / 1073741824.0D) });
	}

	public static String bytes2Bits(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte aByte : bytes) {
			for (int j = 7; j >= 0; j--) {
				sb.append((aByte >> j & 0x1) == 0 ? '0' : '1');
			}
		}
		return sb.toString();
	}

	public static byte[] bits2Bytes(String bits) {
		int lenMod = bits.length() % 8;
		int byteLen = bits.length() / 8;

		if (lenMod != 0) {
			for (int i = lenMod; i < 8; i++) {
				bits = new StringBuilder().append("0").append(bits).toString();
			}
			byteLen++;
		}
		byte[] bytes = new byte[byteLen];
		for (int i = 0; i < byteLen; i++) {
			for (int j = 0; j < 8; j++) {
				int tmp83_81 = i;
				byte[] tmp83_80 = bytes;
				tmp83_80[tmp83_81] = ((byte) (tmp83_80[tmp83_81] << 1));
				int tmp92_90 = i;
				byte[] tmp92_89 = bytes;
				tmp92_89[tmp92_90] = ((byte) (tmp92_89[tmp92_90] | bits.charAt(i * 8 + j) - '0'));
			}
		}
		return bytes;
	}

	public static ByteArrayOutputStream input2OutputStream(InputStream is) {
		if (is == null)
			return null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = is.read(b, 0, 1024)) != -1) {
				os.write(b, 0, len);
			}
			return os;
		} catch (IOException e) {
			//byte[] b;
			e.printStackTrace();
			return null;
		} finally {
			IOUtil.closeQuietly(is);
		}
	}

	public ByteArrayInputStream output2InputStream(OutputStream out) {
		if (out == null)
			return null;
		return new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
	}

	public static byte[] inputStream2Bytes(InputStream is) {
		if (is == null)
			return null;
		return input2OutputStream(is).toByteArray();
	}

	public static InputStream bytes2InputStream(byte[] bytes) {
		if ((bytes == null) || (bytes.length <= 0))
			return null;
		return new ByteArrayInputStream(bytes);
	}

	public static byte[] outputStream2Bytes(OutputStream out) {
		if (out == null)
			return null;
		return ((ByteArrayOutputStream) out).toByteArray();
	}

	public static OutputStream bytes2OutputStream(byte[] bytes) {
		if ((bytes == null) || (bytes.length <= 0))
			return null;
		ByteArrayOutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
			os.write(bytes);
			return os;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtil.closeQuietly(os);
		}
	}

	public static String inputStream2String(InputStream is, String charsetName) {
		if ((is == null) || (isSpace(charsetName)))
			return null;
		try {
			return new String(inputStream2Bytes(is), charsetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static InputStream string2InputStream(String string, String charsetName) {
		if ((string == null) || (isSpace(charsetName)))
			return null;
		try {
			return new ByteArrayInputStream(string.getBytes(charsetName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String outputStream2String(OutputStream out, String charsetName) {
		if ((out == null) || (isSpace(charsetName)))
			return null;
		try {
			return new String(outputStream2Bytes(out), charsetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static OutputStream string2OutputStream(String string, String charsetName) {
		if ((string == null) || (isSpace(charsetName)))
			return null;
		try {
			return bytes2OutputStream(string.getBytes(charsetName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static boolean isSpace(String s) {
		if (s == null)
			return true;
		int i = 0;
		for (int len = s.length(); i < len; i++) {
			if (!Character.isWhitespace(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}