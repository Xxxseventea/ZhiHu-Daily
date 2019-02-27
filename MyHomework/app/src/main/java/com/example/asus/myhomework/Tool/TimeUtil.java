package com.example.asus.myhomework.tool;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;


public class TimeUtil {
/**  时间轴转换为yyyy-MM-dd HH:mm:ss类型**/
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.parseLong(s));
        res = simpleDateFormat.format(date);
        return res;
    }

    /**获取前一天的时间**/
    public static String getDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //向前走一天
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        return str;
    }
    /**
     * 获取当前时间的毫秒数
     */
    public static long getTimeLong(){
        return System.currentTimeMillis();
    }
    /**时间轴转换为年月日类型**/
    public static String stampToDate_ymd(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.parseLong(s));
        res = simpleDateFormat.format(date);
        return res;
    }
    /**

     * 获取现在时间 yyyy-MM-dd HH:mm:ss

     */

    public static String getStringDate() {

        Date currentTime = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateString = formatter.format(currentTime);

        return dateString;

    }



    /**

     * 获取现在时间  返回短时间字符串格式 yyyy-MM-dd

     */

    public static String getStringDateShort() {

        Date currentTime = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = formatter.format(currentTime);

        return dateString;

    }
    /**

     * 得到现在时间

     **/

    public static Date getNow() {

        Date currentTime = new Date();

        return currentTime;

    }


}
