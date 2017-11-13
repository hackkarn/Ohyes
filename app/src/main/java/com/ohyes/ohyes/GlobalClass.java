package com.ohyes.ohyes;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Xesnost on 2/11/2560.
 */

public class GlobalClass extends Application{

    public String timeHour;
    public String timeMin;
    public String username;
    public String userid;
    public String alarmStatus;
    public int count;
    public ArrayList<String> medlist;

    public String getTimeHour(){
        return timeHour;
    }

    public void setTimeHour(String timeHour){
        this.timeHour = timeHour;
    }

    public String getTimeMin(){
        return timeMin;
    }

    public void setTimeMin(String timeMin){
        this.timeMin = timeMin;
    }

    public String getUsername (){
        return username;
    }

    public void setUsername (String username){
        this.username = username;
    }

    public String getUserid(){
        return userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getAlarmStatus(){
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus){
        this.alarmStatus = alarmStatus;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public ArrayList<String> getMedlist(){
        return medlist;
    }

    public void setMedlist(ArrayList medlist){
        this.medlist = medlist;
    }
}
