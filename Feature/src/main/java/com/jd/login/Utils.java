package com.jd.login;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static double timeDiff(String timeB, String timeA, String timeSignal) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date2 = simpleDateFormat.parse(timeB);
            Date date1 = simpleDateFormat.parse(timeA);
            long diffSecond = (date2.getTime() - date1.getTime())/1000;
            if (timeSignal.equals("minute")) {
                return diffSecond/60;
            } else if (timeSignal.equals("hour")) {
                return diffSecond/3600;
            } else if (timeSignal.equals("day")) {
                return diffSecond/(3600*24);
            } else {
                return diffSecond;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -99;
    }

    public static String timeDiff(String time, int minutes) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(time);
            Date date1 = new Date(date.getTime() - minutes * 60 * 1000);
            return simpleDateFormat.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-99";
    }
}
