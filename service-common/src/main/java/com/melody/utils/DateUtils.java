package com.melody.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author konghang 
 */
public class DateUtils {

    /**
     * 最小的时间戳
     */
    private static int MIN_TIMESTAMP = 0;
    /**
     * 最大的时间戳
     */
    private static int MAX_TIMESTAMP = 1999999999;
    /**
     * 一天小时数
     */
    private static int HOUR = 24;
    /**
     * 一分钟秒数
     */
    private static int SECOND = 60;
    /**
     * 一个月天数
     */
    private static int MONTH_DAY = 30;
    /**
     * 一小时的秒数 3600
     */
    private static int SECOND_OF_HOUR = SECOND * SECOND;
    /**
     * 一天的秒数 86400
     */
    private static int SECOND_OF_DAY = SECOND_OF_HOUR * HOUR;
    /**
     * 一个月秒数:一个月这里按固定30天算 2592000
     */
    private static int SECOND_OF_MONTH = SECOND_OF_DAY * MONTH_DAY;
    /**
     * 一年中的月份数
     */
    private static int MONTH = 12;
    private static int OFFSET = 0;

    public enum DATE_FORMAT {
        SIMPLE("yyyy-MM-dd"),
        SIMPLE_YEAR_MONTH_DAY("yyyy年MM月dd日"),
        DATE_AND_TIME("yyyy-MM-dd HH:mm:ss");

        String format;

        DATE_FORMAT(String format) {
            this.format = format;
        }

        @Override
        public String toString() {
            return format;
        }
    }

    /**
     * 返回unix时间戳 (1970年至今的秒数)
     * @return
     */
    public static Long getUnixStamp(){
        return System.currentTimeMillis() /1000;
    }

    /**
     * 得到昨天的日期
     * @return
     */
    public static String getYestodayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT.SIMPLE.format);
        String yestoday = sdf.format(calendar.getTime());
        return yestoday;
    }

    /**
     * 时间戳转化为时间格式
     * @param timeStamp
     * @return
     */
    public static String timeStampToStr(long timeStamp) {
        return timeStampToStr(timeStamp, DATE_FORMAT.SIMPLE.format);
    }

    public static String timeStampToStr(long timeStamp,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * 得到时间  HH:mm:ss
     * @param timeStamp   时间戳
     * @return
     */
    public static String getTime(long timeStamp) {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT.DATE_AND_TIME.format);
        String date = sdf.format(timeStamp * 1000);
        String[] split = date.split("\\s");
        if ( split.length > 1 ){
            time = split[1];
        }
        return time;
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = getUnixStamp();
        long time = curTime - timeStamp;

        if (time < SECOND && time >= MIN_TIMESTAMP) {
            return "刚刚";
        } else if (time >= SECOND && time < SECOND_OF_HOUR) {
            return time / SECOND + "分钟前";
        } else if (time >= SECOND_OF_HOUR && time < SECOND_OF_DAY) {
            return time / SECOND_OF_HOUR + "小时前";
        } else if (time >= SECOND_OF_DAY && time < SECOND_OF_MONTH) {
            return time / SECOND_OF_HOUR / HOUR + "天前";
        } else if (time >= SECOND_OF_MONTH && time < SECOND_OF_HOUR * HOUR * MONTH_DAY * MONTH) {
            return time / SECOND_OF_HOUR / HOUR / MONTH_DAY + "个月前";
        } else if (time >= SECOND_OF_HOUR * HOUR * MONTH_DAY * MONTH) {
            return time / SECOND_OF_HOUR / HOUR / MONTH_DAY / MONTH + "年前";
        } else {
            return "刚刚";
        }
    }

    /**
     * 获取当天00:00:00时间戳
     * @return
     */
    public static Long getDayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Long result = calendar.getTimeInMillis() / 1000;
        return result;
    }

    /**
     * 获取某月最后一天星期几-从周日到周六1-7
     * instance.add(Calendar.MONTH, 1);
     * instance.set(Calendar.DAY_OF_MONTH, 0);
     * 某月第一天
     * instance.add(Calendar.MONTH, 0);
     * instance.set(Calendar.DAY_OF_MONTH, 1);
     * @param date
     * @return
     */
    public static int getWeek(Date date){
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.setTime(date);
        instance.add(Calendar.MONTH, 1);
        instance.set(Calendar.DAY_OF_MONTH, 0);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        int week = instance.get(Calendar.DAY_OF_WEEK);
        /*if(a == 1){
            //周日
            instance.add(Calendar.DATE, -2);
        }else if (a == 7){
            //周六
            instance.add(Calendar.DATE, -1);
        }*/
        //return instance.getTime();
        return week;
    }

    /**
     * 计算两个时间戳所代表的日期之间相隔的天数
     * @param begin
     * @param end
     * @return
     */
    public static int getBetweenDay(Long begin, Long end){
        /**
         * 时间戳长度,精确到毫秒
         */
        int timestampsLength = 13;
        Calendar bc =  Calendar.getInstance();
        Calendar ec =  Calendar.getInstance();
        if (begin.toString().length() < timestampsLength){
            bc.setTimeInMillis(begin * 1000);
            ec.setTimeInMillis(end * 1000);
        }else {
            bc.setTimeInMillis(begin);
            ec.setTimeInMillis(end);
        }
        LocalDate lStart = LocalDate.of(bc.get(Calendar.YEAR), bc.get(Calendar.MONTH) + 1, bc.get(Calendar.DATE));
        LocalDate lEnd = LocalDate.of(ec.get(Calendar.YEAR), ec.get(Calendar.MONTH) + 1, ec.get(Calendar.DATE));
        Long days = lEnd.toEpochDay() - lStart.toEpochDay();
        return days.intValue();
    }

    /**
     * 获取当月最后一个工作日
     * @param date
     * @return
     */
    public static Date lastWorkDayTime(Date date){
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.setTime(date);
        instance.add(Calendar.MONTH, 1);
        instance.set(Calendar.DAY_OF_MONTH, 0);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        int a = instance.get(Calendar.DAY_OF_WEEK);
        if(a == 1){
            //周日
            instance.add(Calendar.DATE, -2);
        }else if (a == 7){
            //周六
            instance.add(Calendar.DATE, -1);
        }
        return instance.getTime();
    }

}
