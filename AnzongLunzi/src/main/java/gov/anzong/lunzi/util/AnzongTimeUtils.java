package gov.anzong.lunzi.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2016-12-26 09:41<br>
 * //   这玩意的用处：<br>
 */
@SuppressLint("SimpleDateFormat")
public class AnzongTimeUtils {

    public static final String yearSdfString = "yyyy";
    public static final String shortSdfString = "yyyy-MM-dd";
    public static final String longHourSdfString = "yyyy-MM-dd HH";
    public static final String longSdfString = "yyyy-MM-dd HH:mm:ss";


    public static final String yearSdfStringChinese = "yyyy年";
    public static final String shortSdfStringChinese = "yyyy年MM月dd日";
    public static final String longHourSdfStringChinese = "yyyy年MM月dd日 HH时";
    public static final String longSdfStringChinese = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 获取  当前年、半年、季度、月、日、小时 开始结束时间
     */

    private final SimpleDateFormat shortSdf;
    private final SimpleDateFormat longHourSdf;
    private final SimpleDateFormat longSdf;

    public AnzongTimeUtils() {
        this.shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        this.longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
        this.longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获得本周的第一天，周一
     *
     * @return
     */
    public Date getCurrentWeekDayStartTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本周的第一天，周一
     *
     * @return
     */
    public static String getCurrentYearSeasonString() {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3)
            return currentYear + "年第一季度";
        else if (currentMonth >= 4 && currentMonth <= 6)
            return currentYear + "年第二季度";
        else if (currentMonth >= 7 && currentMonth <= 9)
            return currentYear + "年第三季度";
        else if (currentMonth >= 10 && currentMonth <= 12)
            return currentYear + "年第四季度";
        return currentYear + "年";
    }

    /**
     * 获得本周的第一天，周一
     *
     * @return
     */
    public static String getCurrentYearMonthString() {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        return currentYear + "年" + currentMonth + "月";
    }

    /**
     * 获得本周的最后一天，周日
     *
     * @return
     */
    public Date getCurrentWeekDayEndTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 8 - weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本天的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public Date getCurrentDayStartTime() {
        Date now = new Date();
        try {
            now = shortSdf.parse(shortSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本天的结束时间，即2012-01-01 23:59:59
     *
     * @return
     */
    public Date getCurrentDayEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本小时的开始时间，即2012-01-01 23:59:59
     *
     * @return
     */
    public Date getCurrentHourStartTime() {
        Date now = new Date();
        try {
            now = longHourSdf.parse(longHourSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本小时的结束时间，即2012-01-01 23:59:59
     *
     * @return
     */
    public Date getCurrentHourEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(longHourSdf.format(now) + ":59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本月的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public Date getCurrentMonthStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前月的结束时间，即2012-01-31 23:59:59
     *
     * @return
     */
    public Date getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public Date getCurrentYearStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     *
     * @return
     */
    public Date getCurrentYearEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     *
     * @return
     */
    public Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取前/后半年的开始时间
     *
     * @return
     */
    public Date getHalfYearStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;

    }

    /**
     * 获取前/后半年的结束时间
     *
     * @return
     */
    public Date getHalfYearEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String unixStampToDate(long s) {
        return unixStampToDate(s, "yyyy-MM-dd");
    }

    /*
     * 将时间戳转换为时间
     */
    public static String unixStampToDate(long s, String format) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取今天的日期
     *
     * @return
     */
    public static String getTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    /**
     * 获取今天的日期
     *
     * @return
     */
    public static String getNowDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static String getYesterDayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date(System.currentTimeMillis() - 86400 * 1000L));
    }

    /**
     * 时间转字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String dateToString(Date time, String format) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(format);
        return formatter.format(time);
    }

    /**
     * 字符串转时间
     *
     * @param timeString
     * @param format
     * @return
     * @throws Exception
     */
    public static Date stringToDate(String timeString,
                                    String format) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(timeString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串转时间
     *
     * @param timeString
     * @param format
     * @return
     * @throws Exception
     */
    public static long stringToUnix(String timeString,
                                    String format) {
        Date date = null;
        try {
            date = stringToDate(timeString, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }


    /**
     * 获取月月日日时时分分
     *
     * @param fullTime
     * @return
     */
    public static String getMMDDHHmmString(String fullTime) {
        if (AnzongStringUtils.isBlank(fullTime)) {
            return "";
        }
        try {
            Date date = stringToDate(fullTime, longSdfString);
            return dateToString(date, "MM-dd HH:mm");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取时时分分
     *
     * @param fullTime
     * @return
     */
    public static String getHHmmString(String fullTime) {
        if (AnzongStringUtils.isBlank(fullTime)) {
            return "";
        }
        try {
            Date date = stringToDate(fullTime, longSdfString);
            return dateToString(date, "HH:mm");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取月月日日时时分分
     *
     * @param fullTime
     * @return
     */
    public static String getMMDDString(String fullTime) {
        if (AnzongStringUtils.isBlank(fullTime)) {
            return "";
        }
        try {
            Date date = stringToDate(fullTime, longSdfString);
            return dateToString(date, "MM-dd");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isToday(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            return false;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        if (year1 == year2 && month1 == month2 && day1 == day2) {
            return true;
        }
        return false;
    }

    public static String getWeek(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            return "";
        }
        String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    public static boolean isYesterday(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            return false;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        c2.add(Calendar.DAY_OF_MONTH, -1);
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        if (year1 == year2 && month1 == month2 && day1 == day2) {
            return true;
        }
        return false;
    }
}