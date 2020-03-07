package com.dccj.uitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * common data util tools for handle the date conversion.
 */
public class DateUtils {


    private static ThreadLocal<Map<String, SimpleDateFormat>> dateFormatMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        public Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<String, SimpleDateFormat>();
        }
    };

    private static SimpleDateFormat getSimpleDateFormat(String format) {
        Map<String, SimpleDateFormat> map = dateFormatMap.get();
        SimpleDateFormat simpleDateFormat = map.get(format);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(format);
            map.put(format, simpleDateFormat);
        }
        return simpleDateFormat;
    }

    public static String format(Date date, String format) {
        if (date == null)
            return "";
        return getSimpleDateFormat(format).format(date);
    }

    public static Date parse(String date, String format) {
        try {
            return getSimpleDateFormat(format).parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Date getDate(String sourceStr) {
        Date ret = getStr2LDate(sourceStr);
        if (ret == null)
            ret = getStr2ShortDate(sourceStr);
        return ret;
    }


    /**
     * @return String
     * @throws Exception
     * @author CPH
     * @author CPH
     * get Date format Example：2008-05-15
     */
    public static String getDateLong(Date date) {
        String nowDate = "";
        try {
            if (date != null)
                nowDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            return nowDate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @return 当前时间
     */
    public static Date getNowDate() {
        int offsetDays = 0;
        int offsetMinutes = 0;
        if (offsetDays == 0 && offsetMinutes == 0) {
            return new Date();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + offsetDays);
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + offsetMinutes);
            return calendar.getTime();
        }
    }

    private static final SimpleDateFormat formatterUTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+08:00");

    public static synchronized String getUTCString(Date date) {
        String formatDate = formatterUTC.format(date);
        return formatDate;
    }


    /**
     * get current date,fomart:yyyy-MM-dd HH:mm:ss
     *
     * @return String
     * @throws Exception
     */
    public static String getNowPlusTime() {
        String nowDate = "";
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(DateUtils.getNowDate().getTime());
            nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            return nowDate;
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * get specified date,fomart:yyyy-MM-dd HH:mm:ss
     *
     * @return String
     * @throws Exception
     */
    public static String getPlusTime(Date date) {
        if (date == null) return null;
        try {
            String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            return nowDate;
        } catch (Exception e) {
            // do nothing for this;
            return "";
        }
    }

    /**
     * difference between days
     *
     * @param newDate
     * @param oldDate
     * @return newDate-oldDate
     */
    public static int daysBetweenDates(Date newDate, Date oldDate) {
        int days = 0;
        Calendar calo = Calendar.getInstance();
        Calendar caln = Calendar.getInstance();
        calo.setTime(oldDate);
        caln.setTime(newDate);
        int oday = calo.get(Calendar.DAY_OF_YEAR);
        int nyear = caln.get(Calendar.YEAR);
        int oyear = calo.get(Calendar.YEAR);
        while (nyear > oyear) {
            calo.set(Calendar.MONTH, 11);
            calo.set(Calendar.DATE, 31);
            days = days + calo.get(Calendar.DAY_OF_YEAR);
            oyear = oyear + 1;
            calo.set(Calendar.YEAR, oyear);
        }
        int nday = caln.get(Calendar.DAY_OF_YEAR);
        days = days + nday - oday;
        return days;
    }

    public static void main(String[] args) {
        System.out.println(daysBetweenDates(new Date(),addDays(new Date(),15)));
    }


    public static long secondsBetweenDates(Date newDate, Date oldDate) {
        return (newDate.getTime() - oldDate.getTime()) / 1000;
    }

    public static String getFormattedDateUtil(Date dtDate, String strFormatTo) {
        if (dtDate == null) {
            return "";
        }
        strFormatTo = strFormatTo.replace('/', '-');
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(strFormatTo);
            return formatter.format(dtDate);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 将一个字符串转化成日期对象，如果转化失败，返回null
     *
     * @param sourceString
     * @param pattern
     * @return
     */
    public static Date parseDateFromString(String sourceString, String pattern) {
        Date re = null;
        if (sourceString != null && !sourceString.equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            try {
                re = dateFormat.parse(sourceString);
            } catch (ParseException e) {
                return null;
            }
        }
        return re;
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm:ss的时间
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LineDate(String str) {
        return parseDateFromString(str, "yyyy-MM-dd_HH:mm");
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm:ss的时间
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LDate(String str) {
        return parseDateFromString(str, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将某指定的字符串转换为型如：yyyymmdd 的时间
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2ShortDate(String str) {
        return parseDateFromString(str, "yyyy-MM-dd");
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm的时间
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LDateWithoutSecond(String str) {
        return parseDateFromString(str, "yyyy-MM-dd HH:mm");
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2SDate(String str) {
        return parseDateFromString(str, "yyyy-MM-dd");
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-M
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2YearMonth(String str) {
        return parseDateFromString(str, "yyyy-M");
    }

    /**
     * 得到平台开始时间
     */
    public static Date getPlatformStartDate() {
        return parseDateFromString("2013-01-01", "yyyy-MM-dd");
    }

    /**
     * 得到平台最大时间
     */
    public static Date getPlatformMaxDate() {
        return parseDateFromString("2200-01-01", "yyyy-MM-dd");
    }

    /**
     * 得到平台上线时间
     */
    public static Date getPlatformOnboardDate() {
        return parseDateFromString("2013-08-07", "yyyy-MM-dd");
    }

    /**
     * 返回日期加X天后的日期
     *
     * @param date
     * @param i
     * @return
     * @author CPH
     * @date Mar 11, 2012
     */
    public static String addDays(String date, int i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(
                    Integer.parseInt(date.substring(0, 4)),
                    Integer.parseInt(date.substring(5, 7)) - 1,
                    Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.DATE, i);
            return new SimpleDateFormat("yyyy-MM-dd").format(gCal.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回日期加X月后的日期
     *
     * @param date
     * @param i
     * @return
     * @author CPH
     * @date Mar 11, 2012
     */
    public static String addMonths(String date, int i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(
                    Integer.parseInt(date.substring(0, 4)),
                    Integer.parseInt(date.substring(5, 7)) - 1,
                    Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.MONTH, i);
            return new SimpleDateFormat("yyyy-MM-dd").format(gCal.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 计算两个时间相差秒数
     *
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        long s = diff / 1000;
        return s + "";
    }

    /**
     * 获取基准日的前后偏移日
     *
     * @param count 偏移数量
     * @param date  基准日期
     * @return Date
     */
    public static Date addDays(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, count);
        return calendar.getTime();
    }


    public static Date addMinutes(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, count);
        return calendar.getTime();
    }

    /**
     * 友好的方式显示时间
     */
    public static String getFriendlyFormat(Date date) {
        if (date == null) {
            return "";
        }
        Date now = DateUtils.getNowDate();
        String time = DateUtils.getFormattedDateUtil(date, "HH:mm");
        int days = DateUtils.daysBetweenDates(now, date);
        if (days < 0) {
            return DateUtils.getFormattedDateUtil(date, "yyyy-MM-dd HH:mm");
        } else if (days == 0) {
            int hour = (int) ((now.getTime() - date.getTime()) / 3600000);
            if (hour > 0) {
                return hour + "小时前";
            }
            int minute = (int) ((now.getTime() - date.getTime()) / 60000);
            if (minute < 1) {
                minute = 1;
            }
            return minute + "分钟前";
        } else if (days == 1) {
            return "昨天" + time;
        } else if (days == 2) {
            return "前天" + time;
        } else {
            return days + "天前";
        }
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static Date getDate(int year, int natural_month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, natural_month - 1, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取前月的第一天
     *
     * @param
     * @return Date
     */
    public static String getFirstDayLastMonth() {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return getDateLong(cal.getTime());
    }

    public static String getYesterdayDay() {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.add(Calendar.DATE, -1);
        return getDateLong(cal.getTime());
    }

    public static String getFirstDayNextMonth() {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return getDateLong(cal.getTime());
    }

    public static String getFirstDayThisMonth() {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return getDateLong(cal.getTime());
    }

    /**
     * 计算两个时间相差年月日
     * @param startDate
     * @param endDate
     * @return
     */
    public static String remainDateToString(Date startDate,Date endDate){
        Calendar calS=Calendar.getInstance();
        calS.setTime(startDate);

        int startY = calS.get(Calendar.YEAR);
        int startM = calS.get(Calendar.MONTH);
        int startD = calS.get(Calendar.DATE);
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        calS.setTime(endDate);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);
        //处理2011-01-10到2011-01-10，认为服务为一天
        int endD = calS.get(Calendar.DATE)+1;
        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        StringBuilder sBuilder = new StringBuilder();
        if (endDate.compareTo(startDate)<0) {
            return sBuilder.append("过期").toString();
        }
        int lday = endD-startD;
        if (lday<0) {
            endM = endM -1;
            lday = startDayOfMonth+ lday;
        }
        //处理天数问题，如：2011-01-01 到 2013-12-31  2年11个月31天     实际上就是3年
        if (lday == endDayOfMonth) {
            endM = endM+1;
            lday =0;
        }
        int mos = (endY - startY)*12 + (endM- startM);
        int lyear = mos/12;
        int lmonth = mos%12;
        if (lyear >0) {
            sBuilder.append(lyear+"年");
        }
        if (lmonth > 0) {
            sBuilder.append(lmonth+"个月");
        }
        if (lday >0 ) {
            sBuilder.append(lday+"天");
        }
        return sBuilder.toString();
    }




    public static String getDatePoors(Date nowDate,Date endDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        if(day <= 0 && hour <= 0 && min <= 0){
            return "已过期";
        }else if(day == 0){
            return hour + "小时" + min + "分钟"  ;
        }else{
            return day + "天" + hour + "小时" + min + "分钟";
        }
    }





}
