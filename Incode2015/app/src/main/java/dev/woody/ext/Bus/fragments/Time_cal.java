package dev.woody.ext.Bus.fragments;

import android.util.Log;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kimsehyung on 2016-02-29.
 */
public class Time_cal {
    private String am_start = "08:00";
    private String am_end = "10:30";
    private String pm_start = "16:30";
    private String pm_end = "18:30";


    SimpleDateFormat df = new SimpleDateFormat("kk:mm");
    String nowTime = df.format(new Date(System.currentTimeMillis())); //현재시간

    private String time;

    public Time_cal(String time){
        this.time = time;
    }

    public int getTime() throws ParseException {
        return sub_time(nowTime, time);
    }

    public int sub_time(String start, String end) throws ParseException {
        Log.i("start", start + " -  " + end);
        long duration = (getSecond(end) - getSecond(start)) / (1000 * 60);
        Log.i("start", duration + "..");

        return (int)duration;
    }

    public long getSecond(String time) throws android.net.ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("kk:mm");
        Date _time = sf.parse(time, new ParsePosition(0));
        return _time.getTime();
    }

    public int tcheck() throws ParseException {
        return sub_time(nowTime, time);
    }
}