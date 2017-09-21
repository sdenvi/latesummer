package com.latesummer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hashids {
	public static final long MAX_NUMBER = 9007199254740992L;
	private static final String DEFAULT_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final String DEFAULT_SEPS = "cfhistuCFHISTU";
	private static final String DEFAULT_SALT = "";
	private static final int DEFAULT_MIN_HASH_LENGTH = 0;
	private static final int MIN_ALPHABET_LENGTH = 16;
	private static final double SEP_DIV = 3.5D;
	private static final int GUARD_DIV = 12;
	private final String salt;
	private final int minHashLength;
	private final String alphabet;
	private final String seps;
	private final String guards;

	public Hashids() {
		this("");
	}

	public Hashids(String salt) {
		this(salt, 0);
	}

	public Hashids(String salt, int minHashLength) {
		this(salt, minHashLength, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
	}

	public Hashids(String salt, int minHashLength, String alphabet) {
		this.salt = (salt != null ? salt : "");
		this.minHashLength = (minHashLength > 0 ? minHashLength : 0);

		StringBuilder uniqueAlphabet = new StringBuilder();
		for (int i = 0; i < alphabet.length(); i++) {
			if (uniqueAlphabet.indexOf(String.valueOf(alphabet.charAt(i))) == -1) {
				uniqueAlphabet.append(alphabet.charAt(i));
			}
		}

		alphabet = uniqueAlphabet.toString();

		if (alphabet.length() < 16) {
			throw new IllegalArgumentException("alphabet must contain at least 16 unique characters");
		}

		if (alphabet.contains(" ")) {
			throw new IllegalArgumentException("alphabet cannot contains spaces");
		}

		String seps = "cfhistuCFHISTU";
		for (int i = 0; i < seps.length(); i++) {
			int j = alphabet.indexOf(seps.charAt(i));
			if (j == -1)
				seps = new StringBuilder().append(seps.substring(0, i)).append(" ").append(seps.substring(i + 1))
						.toString();
			else {
				alphabet = new StringBuilder().append(alphabet.substring(0, j)).append(" ")
						.append(alphabet.substring(j + 1)).toString();
			}
		}

		alphabet = alphabet.replaceAll("\\s+", "");
		seps = seps.replaceAll("\\s+", "");
		seps = consistentShuffle(seps, this.salt);

		if ((seps.isEmpty()) || (alphabet.length() / seps.length() > 3.5D)) {
			int seps_len = (int) Math.ceil(alphabet.length() / 3.5D);

			if (seps_len == 1) {
				seps_len++;
			}

			if (seps_len > seps.length()) {
				int diff = seps_len - seps.length();
				seps = new StringBuilder().append(seps).append(alphabet.substring(0, diff)).toString();
				alphabet = alphabet.substring(diff);
			} else {
				seps = seps.substring(0, seps_len);
			}
		}

		alphabet = consistentShuffle(alphabet, this.salt);

		int guardCount = (int) Math.ceil(alphabet.length() / 12.0D);
		String guards;
		if (alphabet.length() < 3) {
			guards = seps.substring(0, guardCount);
			seps = seps.substring(guardCount);
		} else {
			guards = alphabet.substring(0, guardCount);
			alphabet = alphabet.substring(guardCount);
		}
		this.guards = guards;
		this.alphabet = alphabet;
		this.seps = seps;
	}

	public String encode(long[] numbers) {
		if (numbers.length == 0) {
			return "";
		}

		for (long number : numbers) {
			if (number < 0L) {
				return "";
			}
			if (number > 9007199254740992L) {
				throw new IllegalArgumentException("number can not be greater than 9007199254740992L");
			}
		}
		return _encode(numbers);
	}

	public long[] decode(String hash) {
		if (hash.isEmpty()) {
			return new long[0];
		}

		String validChars = new StringBuilder().append(this.alphabet).append(this.guards).append(this.seps).toString();
		for (int i = 0; i < hash.length(); i++) {
			if (validChars.indexOf(hash.charAt(i)) == -1) {
				return new long[0];
			}
		}

		return _decode(hash, this.alphabet);
	}

	public String encodeHex(String hexa) {
		if (!hexa.matches("^[0-9a-fA-F]+$")) {
			return "";
		}

		List matched = new ArrayList();
		Matcher matcher = Pattern.compile("[\\w\\W]{1,12}").matcher(hexa);

		while (matcher.find()) {
			matched.add(Long
					.valueOf(Long.parseLong(new StringBuilder().append("1").append(matcher.group()).toString(), 16)));
		}

		long[] result = new long[matched.size()];
		for (int i = 0; i < matched.size(); i++) {
			result[i] = ((Long) matched.get(i)).longValue();
		}

		return encode(result);
	}

	public String decodeHex(String hash) {
		StringBuilder result = new StringBuilder();
		long[] numbers = decode(hash);

		for (long number : numbers) {
			result.append(Long.toHexString(number).substring(1));
		}

		return result.toString();
	}

	public static int checkedCast(long value) {
		int result = (int) value;
		if (result != value) {
			throw new IllegalArgumentException(new StringBuilder().append("Out of range: ").append(value).toString());
		}
		return result;
	}

	private String _encode(long[] numbers) {
		long numberHashInt = 0L;
		for (int i = 0; i < numbers.length; i++) {
			numberHashInt += numbers[i] % (i + 100);
		}
		String alphabet = this.alphabet;
		char ret = alphabet.charAt((int) (numberHashInt % alphabet.length()));

		StringBuilder ret_strB = new StringBuilder(this.minHashLength);
		ret_strB.append(ret);

		for (int i = 0; i < numbers.length; i++) {
			long num = numbers[i];
			String buffer = new StringBuilder().append(ret).append(this.salt).append(alphabet).toString();

			alphabet = consistentShuffle(alphabet, buffer.substring(0, alphabet.length()));
			String last = hash(num, alphabet);

			ret_strB.append(last);

			if (i + 1 < numbers.length) {
				long sepsIndex;
				if (last.length() > 0) {
					num %= (last.charAt(0) + i);
					sepsIndex = (int) (num % this.seps.length());
				} else {
					sepsIndex = 0L;
				}
				ret_strB.append(this.seps.charAt((int) sepsIndex));
			}
		}

		String ret_str = ret_strB.toString();
		if (ret_str.length() < this.minHashLength) {
			long guardIndex = (numberHashInt + ret_str.charAt(0)) % this.guards.length();
			char guard = this.guards.charAt((int) guardIndex);

			ret_str = new StringBuilder().append(guard).append(ret_str).toString();

			if (ret_str.length() < this.minHashLength) {
				guardIndex = (numberHashInt + ret_str.charAt(2)) % this.guards.length();
				guard = this.guards.charAt((int) guardIndex);

				ret_str = new StringBuilder().append(ret_str).append(guard).toString();
			}
		}

		int halfLen = alphabet.length() / 2;
		while (ret_str.length() < this.minHashLength) {
			alphabet = consistentShuffle(alphabet, alphabet);
			ret_str = new StringBuilder().append(alphabet.substring(halfLen)).append(ret_str)
					.append(alphabet.substring(0, halfLen)).toString();
			int excess = ret_str.length() - this.minHashLength;
			if (excess > 0) {
				int start_pos = excess / 2;
				ret_str = ret_str.substring(start_pos, start_pos + this.minHashLength);
			}
		}

		return ret_str;
	}

	private long[] _decode(String hash, String alphabet) {
		ArrayList ret = new ArrayList();

		int i = 0;
		String regexp = new StringBuilder().append("[").append(this.guards).append("]").toString();
		String hashBreakdown = hash.replaceAll(regexp, " ");
		String[] hashArray = hashBreakdown.split(" ");

		if ((hashArray.length == 3) || (hashArray.length == 2)) {
			i = 1;
		}

		if (hashArray.length > 0) {
			hashBreakdown = hashArray[i];
			if (!hashBreakdown.isEmpty()) {
				char lottery = hashBreakdown.charAt(0);

				hashBreakdown = hashBreakdown.substring(1);
				hashBreakdown = hashBreakdown
						.replaceAll(new StringBuilder().append("[").append(this.seps).append("]").toString(), " ");
				hashArray = hashBreakdown.split(" ");

				for (String aHashArray : hashArray) {
					String subHash = aHashArray;
					String buffer = new StringBuilder().append(lottery).append(this.salt).append(alphabet).toString();
					alphabet = consistentShuffle(alphabet, buffer.substring(0, alphabet.length()));
					ret.add(unhash(subHash, alphabet));
				}
			}

		}

		long[] arr = new long[ret.size()];
		for (int k = 0; k < arr.length; k++) {
			arr[k] = ((Long) ret.get(k)).longValue();
		}

		if (!encode(arr).equals(hash)) {
			arr = new long[0];
		}

		return arr;
	}

	private static String consistentShuffle(String alphabet, String salt) {
		if (salt.length() <= 0) {
			return alphabet;
		}

		char[] tmpArr = alphabet.toCharArray();
		int i = tmpArr.length - 1;
		int v = 0;
		for (int p = 0; i > 0; v++) {
			v %= salt.length();
			int asc_val = salt.charAt(v);
			p += asc_val;
			int j = (asc_val + v + p) % i;
			char tmp = tmpArr[j];
			tmpArr[j] = tmpArr[i];
			tmpArr[i] = tmp;

			i--;
		}

		return new String(tmpArr);
	}

	private static String hash(long input, String alphabet) {
		String hash = "";
		int alphabetLen = alphabet.length();
		do {
			int index = (int) (input % alphabetLen);
			if ((index >= 0) && (index < alphabet.length())) {
				hash = new StringBuilder().append(alphabet.charAt(index)).append(hash).toString();
			}
			input /= alphabetLen;
		} while (input > 0L);

		return hash;
	}

	private static Long unhash(String input, String alphabet) {
		long number = 0L;

		for (int i = 0; i < input.length(); i++) {
			long pos = alphabet.indexOf(input.charAt(i));
			number = number * alphabet.length() + pos;
		}

		return Long.valueOf(number);
	}

	public String getVersion() {
		return "1.0.0";
	}
}