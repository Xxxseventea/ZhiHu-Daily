package com.example.asus.myhomework.tool;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;


public class TimeUtil {

    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.parseLong(s));
        res = simpleDateFormat.format(date);
        return res;
    }
    public static String getDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //向前走一天
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        return str;
    }
}
