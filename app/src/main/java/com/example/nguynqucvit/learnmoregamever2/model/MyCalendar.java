package com.example.nguynqucvit.learnmoregamever2.model;

import java.util.Calendar;

/**
 * Created by vaio on 17/03/2017.
 */

public class MyCalendar {
    private static Calendar mCalendar = Calendar.getInstance();

    public static int getDay() {
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth() {
        return mCalendar.get(Calendar.MONTH);
    }

    public static int getYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    public static int getSecond() {
        return mCalendar.get(Calendar.SECOND);
    }

    public static int getMinute() {
        return mCalendar.get(Calendar.MINUTE);
    }

    public static int getHour() {
        return mCalendar.get(Calendar.HOUR);
    }

    public static String getDate() {
        return getDay() + "/" + getMonth() + "/" + getYear();
    }

    public static String getTimeStamp() {
        return getHour() + ":" + getMinute() + ":" + getSecond();
    }
}
