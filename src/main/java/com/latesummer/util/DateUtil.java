package com.latesummer.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 项目名称：账号安全中心(all)   
 * 类名称：DateUtil   
 * 类描述：   时间操作工具
 * 创建人：Jenvi Sue   
 * 创建时间：2017-09-21
 * @version
 */
public class DateUtil {

	private static final DateTimeFormatter GMT_FMT = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
	private static final ZoneId GMT_ZONE_ID = ZoneId.of("GMT");
	/**
	 * 生成ISO-8601 规范的时间格式
	 * @param date
	 * @return
	 */
	public static String formatISO8601DateString(Date date){
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		return  DateFormatUtils.format(date, pattern);
	}
	
	
	/**
	 * 获取反时间戳
	 * @return
	 */
	public static Long getCurrentReverseTime(){
		long longTime = System.currentTimeMillis()*1000000 + CalculateUtil.getNext(999999);
		return Long.MAX_VALUE - longTime;
	}
	
	/**
	 * 获取原时间戳
	 * @param reverseTime
	 * @return
	 */
	public static Long recoverReverseTime(Long reverseTime){
		long longTime = Long.MAX_VALUE - reverseTime;
		return longTime/1000000;
	}
	/**
	 * 生成页面普通展示时间
	 * @param date
	 * @return
	 */
	public static String formatNormalDateString(Date date){
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return DateFormatUtils.format(date, pattern);
	}
	
	/**
	 * 生成Unix时间
	 * @return
	 */
	public static int nowUnix() {
		return (int) Instant.now().getEpochSecond();
	}
	
	public static String toString(long unixTime, String pattern) {
		return Instant.ofEpochSecond(unixTime).atZone(ZoneId.systemDefault())
				.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String toString(Date date, String pattern) {
		Instant instant = new Date(date.getTime()).toInstant();
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String toString(LocalDateTime date, String pattern) {
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String toString(LocalDateTime time) {
		return toString(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static int toUnix(String time, String pattern) {
		LocalDateTime formatted = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
		return (int) formatted.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
	}

	public static int toUnix(String time) {
		return toUnix(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static int toUnix(Date date) {
		return (int) date.toInstant().getEpochSecond();
	}

	public static Date toDate(String time, String pattern) {
		LocalDate formatted = LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
		return Date.from(Instant.from(formatted.atStartOfDay(ZoneId.systemDefault())));
	}

	public static Date toDateTime(String time, String pattern) {
		LocalDateTime formatted = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
		return Date.from(formatted.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate toLocalDate(String time, String pattern) {
		return LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
	}

	public static LocalDateTime toLocalDateTime(String time, String pattern) {
		return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
	}

	public static Date toDate(long unixTime) {
		return Date.from(Instant.ofEpochSecond(unixTime));
	}

	public static String gmtDate() {
		return GMT_FMT.format(LocalDateTime.now().atZone(GMT_ZONE_ID));
	}

	public static String gmtDate(LocalDateTime localDateTime) {
		return GMT_FMT.format(localDateTime.atZone(GMT_ZONE_ID));
	}

	public static String gmtDate(Date date) {
		return GMT_FMT.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).atZone(GMT_ZONE_ID));
	}
}
