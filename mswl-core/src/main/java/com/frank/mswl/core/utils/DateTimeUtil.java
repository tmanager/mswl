package com.frank.mswl.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 日期时间格式化类.
 *
 * @author 张孝党 2019/06/03.
 * @version V0.0.2.
 * <p>
 * 更新履历： V0.0.1 2019/06/03 张孝党 创建.
 */
public class DateTimeUtil {

    /**
     * 日期格式化.
     */
    public final static String FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 时间格式化.
     */
    public final static String FORMAT_HHMMSS = "HHmmss";

    /**
     *  保存时间格式化.
     */
    public final static String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String getCurrentDate() {

        Date currentDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYYMMDD);
        return df.format(currentDate);
    }

    public static String getCurrentTime() {

        Date currentDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_HHMMSS);
        return df.format(currentDate);
    }

    public static String getTimeformat() {

        Date currentDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS);
        return df.format(currentDate);
    }

    /**
     * 获取固定间隔时刻集合
     *
     * @param start    开始时间
     * @param end      结束时间
     * @param interval 时间间隔(单位：分钟)
     */
    public static List<String> getIntervalTimeList(String start, String end, int interval) throws Exception {

        Date startDate = DateTimeUtil.convertString2Date("HH:mm", start);
        Date endDate = DateTimeUtil.convertString2Date("HH:mm", end);

        List<String> list = new ArrayList<>();

        while (startDate.getTime() <= endDate.getTime()) {
            list.add(DateTimeUtil.convertDate2String("HHmm", startDate));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MINUTE, interval);
            if (calendar.getTime().getTime() > endDate.getTime()) {
                if (!startDate.equals(endDate)) {
                    list.add(DateTimeUtil.convertDate2String("HHmm", endDate));
                }
                startDate = calendar.getTime();
            } else {
                startDate = calendar.getTime();
            }
        }
        return list;
    }

    /**
     * 两个时间相差距离多少秒.
     */
    public static long getDistanceTimes(String starttime, String endtime) {

        long diff = 0;
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            Date one = df.parse(starttime);
            Date two = df.parse(endtime);

            long time1 = one.getTime();
            long time2 = two.getTime();

            diff = time2 - time1 / 1000;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return diff;
    }

    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        //String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 指示一个星期中的某天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 从指定日期加减月数.
     */
    public static String stepMonth(String bDate, int months) throws Exception {

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date bdate = df.parse(bDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        cal.add(Calendar.MONTH, -3);

        return df.format(cal.getTime());
    }

    /**
     * 从指定日期加减天数.
     */
    public static String stepDays(String bDate, int days) throws Exception {

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date bdate = df.parse(bDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        cal.add(Calendar.DATE, days);

        return df.format(cal.getTime());
    }

    public static Date convertString2Date(String format, String time) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(time);
    }

    public static String convertDate2String(String format, Date dt) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(dt);
    }
}
