package demo.sdk.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author: dingran
 * Date: 2015/10/23
 * Description:日期工具类
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils{

    public static final String TIME_WITH_MINUTE_PATTERN = "HH:mm";

    public static final long DAY_MILLI = 24 * 60 * 60 * 1000; // 一天的MilliSecond
    public static final long YEAR_MILLI =  365; // 年

    public final static int LEFT_OPEN_RIGHT_OPEN = 1;

    /**
     * 要用到的DATE Format的定义
     */
    public static String DATE_FORMAT_DATEONLY = "yyyy-MM-dd"; // 年/月/日
    public static String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss"; // 年/月/日
    public static SimpleDateFormat sdfDateTime = new SimpleDateFormat(DATE_FORMAT_DATETIME);
    // Global SimpleDateFormat object
    public static SimpleDateFormat sdfDateOnly = new SimpleDateFormat(DATE_FORMAT_DATEONLY);
    public static final SimpleDateFormat SHORTDATEFORMAT = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat SHORT_TIME_FORMAT = new SimpleDateFormat("HHmmss");
    public static final SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat HMS_FORMAT = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * Cloudstack 特定时间格式转换
     * @param created
     * @return
     */
    public static Calendar parseDateString( String created ) {
        DateFormat formatter = null;
        Calendar cal = Calendar.getInstance();

        // -> for some unknown reason SimpleDateFormat does not properly handle the 'Z' timezone
        if (created.endsWith( "Z" )) created = created.replace( "Z", "+0000" );

        try {
            formatter = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );
            cal.setTime( formatter.parse( created ));
            return cal;
        } catch( Exception e ) {}

        try {
            formatter = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssZ" );
            cal.setTime( formatter.parse( created ));
            return cal;
        } catch( Exception e ) {}


        // -> the time zone is GMT if not defined
        try {
            formatter = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );
            cal.setTime( formatter.parse( created ));

            created = created + "+0000";
            formatter = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssZ" );
            cal.setTime( formatter.parse( created ));
            return cal;
        } catch( Exception e ) {}


        // -> including millseconds?
        try {
            formatter = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.Sz" );
            cal.setTime( formatter.parse( created ));
            return cal;
        } catch( Exception e ) {}

        try {
            formatter = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SZ" );
            cal.setTime( formatter.parse( created ));
            return cal;
        } catch( Exception e ) {}


        // -> the CloudStack API used to return this format for some calls
        try {
            formatter = new SimpleDateFormat( "EEE MMM dd HH:mm:ss z yyyy" );
            cal.setTime( formatter.parse( created ));
            return cal;
        } catch( Exception e ) {}

        return null;
    }

    /**
     * 根据日期格式字符串解析日期字符串
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String str) throws ParseException {
        return parseDate(str,DATE_FORMAT_DATETIME);
    }
    /**
     * 根据日期格式字符串解析日期字符串
     *
     * @param str           日期字符串
     * @param parsePatterns 日期格式字符串
     * @return 解析后日期
     * @throws ParseException
     */
    public static Date parseDate(String str, String parsePatterns) throws ParseException {
        return parseDate(str, new String[]{parsePatterns});
    }

    /**
     * 根据单位字段比较两个日期
     *
     * @param date      日期1
     * @param otherDate 日期2
     * @param withUnit  单位字段，从Calendar field取值
     * @return 等于返回0值, 大于返回大于0的值 小于返回小于0的值
     */
    public static int compareDate(Date date, Date otherDate, int withUnit) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        Calendar otherDateCal = Calendar.getInstance();
        otherDateCal.setTime(otherDate);

        switch (withUnit) {
            case Calendar.YEAR:
                dateCal.clear(Calendar.MONTH);
                otherDateCal.clear(Calendar.MONTH);
            case Calendar.MONTH:
                dateCal.set(Calendar.DATE, 1);
                otherDateCal.set(Calendar.DATE, 1);
            case Calendar.DATE:
                dateCal.set(Calendar.HOUR_OF_DAY, 0);
                otherDateCal.set(Calendar.HOUR_OF_DAY, 0);
            case Calendar.HOUR:
                dateCal.clear(Calendar.MINUTE);
                otherDateCal.clear(Calendar.MINUTE);
            case Calendar.MINUTE:
                dateCal.clear(Calendar.SECOND);
                otherDateCal.clear(Calendar.SECOND);
            case Calendar.SECOND:
                dateCal.clear(Calendar.MILLISECOND);
                otherDateCal.clear(Calendar.MILLISECOND);
            case Calendar.MILLISECOND:
                break;
            default:
                throw new IllegalArgumentException("withUnit 单位字段 " + withUnit + " 不合法！！");
        }
        return dateCal.compareTo(otherDateCal);
    }

    /**
     * 根据单位字段比较两个时间
     *
     * @param date      时间1
     * @param otherDate 时间2
     * @param withUnit  单位字段，从Calendar field取值
     * @return 等于返回0值, 大于返回大于0的值 小于返回小于0的值
     */
    public static int compareTime(Date date, Date otherDate, int withUnit) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        Calendar otherDateCal = Calendar.getInstance();
        otherDateCal.setTime(otherDate);

        dateCal.clear(Calendar.YEAR);
        dateCal.clear(Calendar.MONTH);
        dateCal.set(Calendar.DATE, 1);
        otherDateCal.clear(Calendar.YEAR);
        otherDateCal.clear(Calendar.MONTH);
        otherDateCal.set(Calendar.DATE, 1);
        switch (withUnit) {
            case Calendar.HOUR:
                dateCal.clear(Calendar.MINUTE);
                otherDateCal.clear(Calendar.MINUTE);
            case Calendar.MINUTE:
                dateCal.clear(Calendar.SECOND);
                otherDateCal.clear(Calendar.SECOND);
            case Calendar.SECOND:
                dateCal.clear(Calendar.MILLISECOND);
                otherDateCal.clear(Calendar.MILLISECOND);
            case Calendar.MILLISECOND:
                break;
            default:
                throw new IllegalArgumentException("withUnit 单位字段 " + withUnit + " 不合法！！");
        }
        return dateCal.compareTo(otherDateCal);
    }
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获得当前的日期毫秒
     *
     * @return
     */
    public static long nowTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获得当前的时间戳
     *
     * @return
     */
    public static Timestamp nowTimeStamp() {
        return new Timestamp(nowTimeMillis());
    }

    /**
     * yyyy-MM-dd 当前日期
     */
    public static String getReqDate() {
        return SHORTDATEFORMAT.format(new Date());
    }

    /**
     * HHmmss 当前时间
     * @return
     */
    public static String getHmsTime() {
        return SHORT_TIME_FORMAT.format(new Date());
    }

    /**
     * yyyymmddhhmmss 当前时间
     * @return
     */
    public static String getYmdHmsTime() {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date());
    }

    /**
     * yyyy-MM-dd 传入日期
     *
     * @param date
     * @return
     */
    public static String getReqDate(Date date) {
        if(null == date){
            return "";
        }
        return SHORT_DATE_FORMAT.format(date);
    }

    /**
     * yyyy : 得到指定时间的年份 ，参数为空返回当前时间年份
     * @param date
     * @return
     */
    public static String getYear(Date date) {
        if(null == date)
            date = new Date();
        return new SimpleDateFormat("yyyy").format(date);
    }

    /**
     * 获取当前年 yyyy
     * @return
     */
    public static int getCurrentYear() {
        return Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
    }

    /**
     * 获取当前月 MM
     * @return
     */
    public static int getCurrentMonth() {
        return Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
    }

    /**
     * 获取当前日 dd
     * @return
     */
    public static int getCurrentDay() {
        return Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
    }

    /**
     * 获取当前时 HH
     * @return
     */
        public static int getCurrentHour() {
        return Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
    }
    /**
     * yyyy-MM-dd  传入的时间戳
     *
     * @param tmp
     * @return
     */
    public static String TimestampToDateStr(Timestamp tmp) {
        return SHORT_DATE_FORMAT.format(tmp);
    }

    /**
     * HH:mm:ss 当前时间
     *
     * @return
     */
    public static String getReqTime() {
        return HMS_FORMAT.format(new Date());
    }

    /**
     * 得到时间戳格式字串
     *
     * @param date
     * @return
     */
    public static String getTimeStampStr(Date date) {
        if(null == date){
            date = new Date();
        }
        return LONG_DATE_FORMAT.format(date);
    }

    /**
     * 得到长日期格式字串
     *
     * @return
     */
    public static String getLongDateStr() {
        return LONG_DATE_FORMAT.format(new Date());
    }

    public static String getLongDateStr(Timestamp time) {
        return LONG_DATE_FORMAT.format(time);
    }

    /**
     * 得到短日期格式字串
     *
     * @param date
     * @return
     */
    public static String getShortDateStr(Date date) {
        return SHORT_DATE_FORMAT.format(date);
    }

    public static String getShortDateStr() {
        return SHORT_DATE_FORMAT.format(new Date());
    }

    /**
     * 计算 second 秒后的时间
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date,int second){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/NewYork"));
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }
    /**
     * 计算 minute 分钟后的时间
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 计算 hour 小时后的时间
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 得到day的起始时间点。
     *
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到day的终止时间点.
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 计算 day 天后的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 计算 month 月后的时间
     * @param date
     * @param month
     * @return
     * @throws ParseException
     */
    public static Date addMonth(Date date, int month){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }
    /**
     * 得到month的终止时间点.
     *
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 365 * year);
        return calendar.getTime();
    }

    public static Timestamp strToTimestamp(String dateStr) {
        return Timestamp.valueOf(dateStr);
    }

    public static Timestamp strToTimestamp(Date date) {
        return Timestamp.valueOf(formatTimestamp.format(date));
    }

    public static Timestamp getCurTimestamp() {
        return Timestamp.valueOf(formatTimestamp.format(new Date()));
    }

    /**
     * 取得两个日期之间的日数
     *
     * @return t1到t2间的日数，如果t2 在 t1之后，返回正数，否则返回负数
     */
    public static long daysBetween(Timestamp t1, Timestamp t2) {
        return (t2.getTime() - t1.getTime()) / DAY_MILLI;
    }

    /**
     * 获取两个日期之间的年数
     * @param t1
     * @param t2
     * @return
     */
    public static long yearBetween(Date t1, Date t2) {
        return (t2.getTime() - t1.getTime()) / DAY_MILLI / YEAR_MILLI;
    }

    /**
     * 返回java.sql.Timestamp型的SYSDATE
     *
     * @return java.sql.Timestamp型的SYSDATE
     * @history
     * @since 1.0
     */
    public static Timestamp getSysDateTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }



    /**
     * 根据指定的Format转化java.util.Date到String
     *
     * @param dt   java.util.Date instance
     * @param sFmt Date format , DATE_FORMAT_DATEONLY or DATE_FORMAT_DATETIME
     * @return
     * @history
     * @since 1.0
     */
    public static String toString(Date dt, String sFmt) {
        if (dt == null || sFmt == null || "".equals(sFmt)) {
            return "";
        }
        return toString(dt, new SimpleDateFormat(sFmt));
    }

    /**
     * 利用指定SimpleDateFormat instance转换java.util.Date到String
     *
     * @param dt        java.util.Date instance
     * @param formatter SimpleDateFormat Instance
     * @return
     * @history
     * @since 1.0
     */
    private static String toString(Date dt, SimpleDateFormat formatter) {
        String sRet = null;

        try {
            sRet = formatter.format(dt).toString();
        } catch (Exception e) {
            e.printStackTrace();
            sRet = null;
        }

        return sRet;
    }




    //得到当前日期的星期
    public static int getWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK);
        return w;
    }


    /**
     * 判断一个时间是否在某个时间区间内
     *
     * @param now   目标时间
     * @param start 时间区间开始
     * @param end   时间区间结束
     * @param model 区间模式
     * @return 是否在区间内
     */
    public static boolean isBetween(Date now, Date start, Date end, int model) {

        if (now == null || start == null || end == null)
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        switch (model) {
            case LEFT_OPEN_RIGHT_OPEN: {
                if (now.after(start) && now.before(end))
                    return true;
                break;
            }
            default: {
                return false;
            }
        }
        return false;
    }

    /**
     * 得到当前周起始时间
     *
     * @param date
     * @return
     */
    public static Date getWeekStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.WEEK_OF_YEAR);
        int firstDay = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, firstDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到当月起始时间
     *
     * @param date
     * @return
     */
    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到当前年起始时间
     *
     * @param date
     * @return
     */
    public static Date getYearStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 取得月天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得季度第一天
     *
     * @param date
     * @return
     */
    public static Date getSeasonStart(Date date) {
        return getDayStart(getFirstDateOfMonth(getSeasonDate(date)[0]));
    }

    /**
     * 取得季度最后一天
     *
     * @param date
     * @return
     */
    public static Date getSeasonEnd(Date date) {
        return getDayStart(getLastDateOfMonth(getSeasonDate(date)[2]));
    }

    /**
     * 取得季度月
     *
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 判断开始时间和结束时间，是否超出了当前时间的一定的间隔数限制
     * 如：开始时间和结束时间，不能超出距离当前时间90天
     *
     * @param startDate 开始时间
     * @param endDate   结束时间按
     * @param interval  间隔数
     * @param dateUnit  单位(如：月，日),参照Calendar的时间单位
     * @return
     */
    public static boolean isOverIntervalLimit(Date startDate, Date endDate, int interval, int dateUnit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(dateUnit, interval * (-1));
        Date curDate = getDayStart(cal.getTime());
        if (getDayStart(startDate).compareTo(curDate) < 0 || getDayStart(endDate).compareTo(curDate) < 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断开始时间和结束时间，是否超出了当前时间的一定的间隔数限制, 时间单位默认为天数
     * 如：开始时间和结束时间，不能超出距离当前时间90天
     *
     * @param startDate 开始时间
     * @param endDate   结束时间按
     * @param interval  间隔数
     * @return
     */
    public static boolean isOverIntervalLimit(Date startDate, Date endDate, int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, interval * (-1));
        Date curDate = getDayStart(cal.getTime());
        if (getDayStart(startDate).compareTo(curDate) < 0 || getDayStart(endDate).compareTo(curDate) < 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取今天还剩下多少秒
     * @return
     */
    public static int getTadaySurplusTime(){
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int)(tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000;
    }

    public static  String getMonday() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, -1 * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
       String monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return monday+" 00:00:00";
    }
    public static  String getSunday() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, -1*7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String sunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return sunday+" 23:59:59";
    }


    /**
     * 根据指定类型计算两个日期相差的时间
     * eg. dateDiff(birth, today, Calendar.MONTH) 孩子的月龄
     * @param sDate    开始时间
     * @param eDate    结束时间
     * @param diffType 日期类型
     * @return 根据 diffType计算的 eDate - sDate 时差
     */
    public static Long dateDiff(Date sDate, Date eDate, int diffType) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();

        calst.setTime(sDate);
        caled.setTime(eDate);

        long diffMill = caled.getTime().getTime() - calst.getTime().getTime();
        long rtn = 0;
        switch (diffType) {
            case Calendar.MILLISECOND:
                rtn = diffMill;
                break;
            case Calendar.SECOND:
                rtn = diffMill / 1000;
                break;
            case Calendar.MINUTE:
                rtn = diffMill / 1000 / 60;
                break;
            case Calendar.HOUR:
                rtn = diffMill / 1000 / 3600;
                break;
            case Calendar.DATE:
                rtn = diffMill / 1000 / 60 / 60 / 24;
                break;
            case Calendar.MONTH:
                rtn = diffMill / 1000 / 60 / 60 / 24 / 12;
                break;
            case Calendar.YEAR:
                rtn = diffMill / 1000 / 60 / 60 / 24 / 365;
                break;
        }
        return rtn;
    }

    /**
     * 获取指定长度的随机数字字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < length; i++) {
            str.append(new Random().nextInt(9));
        }
        return str.toString();
    }
    
    /**
     * 获取某一天是周几
     * @param date
     * @return
     */
    public static String  getDayofweek(String date){  
    	  Calendar cal = Calendar.getInstance();  
    	//   cal.setTime(new Date(System.currentTimeMillis()));  
    	  if (date.equals("")) {  
    	   cal.setTime(new Date(System.currentTimeMillis()));  
    	  }else {  
    	   cal.setTime(new Date(getDateByStr2(date).getTime()));  
    	  }  
    	  
    	  int month =cal.get(Calendar.MONTH)+1;
    	  int day =cal.get(Calendar.DATE);
    	  String result=month+"月 "+day+"日  "+getWeek(cal.get(Calendar.DAY_OF_WEEK)); 
    	   return result;  
    	 }  
    	  
    	public static Date getDateByStr2(String dd)  
    	 {  
    	  
    	  SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");  
    	  Date date;  
    	  try {  
    	   date = sd.parse(dd);  
    	  } catch (ParseException e) {  
    	   date = null;  
    	   e.printStackTrace();  
    	  }  
    	  return date;  
    	 }  
    	
    	  public static String getWeek(int key) {
    	        switch (key) {
    	        case 1:
    	            return "星期日";
    	        case 2:
    	            return "星期一";
    	        case 3:
    	            return "星期二";
    	        case 4:
    	            return "星期三";
    	        case 5:
    	            return "星期四";
    	        case 6:
    	            return "星期五";
    	        case 7:
    	            return "星期六";
    	        }
    	        return "";
    	    }

    /**
     * 计算一天的结束时间
     * @return
     */
    public static Date getDayEnd() {
        Calendar c2 = new GregorianCalendar();
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);
        Date time = c2.getTime();
        return time;
    }

    /**
     * 计算一天的开始时间
     * @return
     */
    public static Date getDayStart() {
        Calendar c1 = new GregorianCalendar();
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        Date time = c1.getTime();
        return time;
    }

    public static boolean dayIsWeekDay(Date date) {
        boolean flag = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
            flag = true;
        }
        return flag;
    }

    public static Date getLastMouthStart(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        return calendar1.getTime();
    }

    public static Date getLastMouthEnd(){
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 59);
        return calendar2.getTime();
    }

    /**
     * 获取上个月月份
     * @return
     */
    public static String getLastMouth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, -1);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
        return format.format(ca.getTime());
    }

    /**
     * 获取下个月一号
     * @param date
     * @return
     */
    public static Date getNextMouthStart(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, +1);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return ca.getTime();
    }

    public static void main(String[] args) {
        String time = "2017-09";
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        try {
            Date parse = sim.parse(time);
            Date nextMouthStart = getNextMouthStart(parse);
            String format = sim2.format(nextMouthStart);
            System.out.println("===" + format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
