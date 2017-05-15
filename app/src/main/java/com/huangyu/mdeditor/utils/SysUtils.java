package com.huangyu.mdeditor.utils;

import android.content.Context;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by huangyu on 2017/5/15.
 */

public class SysUtils {

    private static String DATE_FORMAT_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    private static String DATE_FORMAT_yyyyMMdd = "yyyy-MM-dd";
    private static String DATE_FORMAT_MMdd = "MM-dd";

    public static String dateToString(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_yyyyMMddHHmmss, Locale.getDefault());
        return format.format(date);
    }

    public static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_yyyyMMddHHmmss, Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间转换成中文
     *
     * @param date date
     * @return the string
     */
    public static String getFormatDate(Date date) {
        if (date == null) {
            return "Unknown";
        }
        String time = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = new SimpleDateFormat(DATE_FORMAT_MMdd, Locale.getDefault()).format(cal.getTime());
        String paramDate = new SimpleDateFormat(DATE_FORMAT_MMdd, Locale.getDefault()).format(date);
        if (curDate.equals(paramDate)) {
            int inter = (int) (cal.getTimeInMillis() - date.getTime()) / 60000;
            int hour = inter / 60;
            if (inter == 0) {
                time = "刚刚";
            } else if (hour == 0)
                time = Math.max((cal.getTimeInMillis() - date.getTime()) / 60000, 1) + "分钟前";
            else
                time = hour + "小时前";
            return time;
        }

        long lt = date.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - date.getTime()) / 3600000);
            if (hour == 0)
                time = Math.max((cal.getTimeInMillis() - date.getTime()) / 60000, 1) + "分钟前";
            else
                time = hour + "小时前";
        } else if (days == 1) {
            time = "昨天";
        } else if (days == 2) {
            time = "前天";
        } else if (days > 2 && days < 31) {
            time = days + "天前";
        } else if (days < 365) {
            time = new SimpleDateFormat(DATE_FORMAT_MMdd, Locale.getDefault()).format(date);
        } else if (days >= 31 && days <= 2 * 31) {
            time = "一个月前";
        } else if (days > 2 * 31 && days <= 3 * 31) {
            time = "2个月前";
        } else if (days > 3 * 31 && days <= 4 * 31) {
            time = "3个月前";
        } else {
            time = new SimpleDateFormat(DATE_FORMAT_yyyyMMdd, Locale.getDefault()).format(date);
        }
        return time;
    }

    /**
     * 显示Toast
     *
     * @param context context
     * @param content 内容
     */
    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

}
