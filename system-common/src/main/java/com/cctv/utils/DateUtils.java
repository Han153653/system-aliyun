package com.cctv.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 * 
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	// 获取当前时间的LocalDateTime对象
    // LocalDateTime.now()

    // 根据年月日构建
    // LocalDateTime.of()

    // 比较日期先后
    // LocalDateTime.now().isBefore()
    // LocalDateTime.now().isAfter()

	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    /**
     * 获取零点
     * @param d
     * @return
     */
    public static Date getZeroClock(Date d){
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    } 
    
    /**
     * Date转换为LocalDateTime.
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date.
     *
     * @param time
     * @return java.util.Date
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取指定日期的毫秒.
     *
     * @param time
     * @return java.lang.Long
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒.
     *
     * @param time
     * @return java.lang.Long
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式.
     *
     * @param time
     * @param pattern
     * @return java.lang.String
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式.
     *
     * @param pattern
     * @return java.lang.String
     */
    public static String formatNow(String pattern) {
        return  formatTime(LocalDateTime.now(), pattern);
    }
    /**
     * 获取当前时间 
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static String formatNow() {
        return  formatTime(LocalDateTime.now(), DATE_TIME_PATTERN);
    }
    /**
     * 获取当天零点
     * @return
     */
    public static String formatZero(){
    	return formatTime(LocalDateTime.of(LocalDate.now(), LocalTime.MIN), DATE_TIME_PATTERN);
    }
    /**
     * 获取当天23点59分59秒
     * @return
     */
    public static String formatEnd(){
    	return formatTime(LocalDateTime.of(LocalDate.now(), LocalTime.MAX), DATE_TIME_PATTERN);
    }
    /**
     * 获取指定天数前日期
     * @return
     */
    public static String getBeforeDay(Integer count){
    	LocalDateTime format = LocalDateTime.now().minus(count, ChronoUnit.DAYS);
    	return formatTime(format, DATE_TIME_PATTERN);
    }
    
    /**
     * 获取指定天数后日期
     * @return
     */
    public static String getAfterDay(Integer count,String pattern){
    	LocalDateTime format = LocalDateTime.now().plus(count, ChronoUnit.DAYS);
    	return formatTime(format, pattern);
    }
    
    /**
     * String类型时间转为 LocalDateTime
     * @param count
     * @param pattern
     * @return
     */
    public static LocalDateTime parse(String time,String pattern){
    	// LocalDateTime format = LocalDateTime.now().plus(count, ChronoUnit.DAYS);
    	return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }
    
    /**
     * 获取指定小时后日期
     * @param count
     * @param pattern
     * @return
     */
    public static String plus(LocalDateTime ldt,Integer count,String pattern){
    	LocalDateTime format = ldt.plus(count, ChronoUnit.HOURS);
    	return formatTime(format, pattern);
    }
    
    /**
     * 依据指定时间获取指定小时后日期
     * @param time
     * @param count
     * @return
     */
    public static String getplus(String time ,Integer count) {
        return plus(parse(time,DATE_TIME_PATTERN),count,DATE_TIME_PATTERN);
    }
    
    /**
     * 获取指定小时前日期
     * @return
     */
    public static String getBeforeHour(Integer count){
    	LocalDateTime format = LocalDateTime.now().minus(count, ChronoUnit.HOURS);
    	return formatTime(format, DATE_TIME_PATTERN);
    }
    
    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*.
     *
     * @param time
     * @param number
     * @param field
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*.
     *
     * @param time
     * @param number
     * @param field
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*.
     *
     * @param startTime
     * @param endTime
     * @param field
     * @return long
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12L + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00.
     *
     * @param time
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999.
     *
     * @param time
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }
    
    public static LocalDateTime getDayEnd(Long L) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(L), ZoneId.systemDefault());
    }
    
    /*public static void main(String[] args) {
    	Date d = new Date();
		System.out.println(getZeroClock(d));
		LocalDateTime dayStart = getDayStart(LocalDateTime.now());
		System.out.println(dayStart);
		LocalDateTime dayEnd = getDayEnd(System.currentTimeMillis());
		System.out.println(dayEnd);
		System.out.println(getBeforeHour(5));
	}*/
    
}
