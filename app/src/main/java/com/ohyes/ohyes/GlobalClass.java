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
    public String medName;
    public String medQuan;
    public int songId;
    public int selectCode;
    public int countCode;
    public ArrayList<String> codeList;

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

    public String getMedName(){
        return medName;
    }

    public void setMedName(String medName){
        this.medName = medName;
    }

    public String getMedQuan(){
        return medQuan;
    }

    public void setMedQuan(String medQuan){
        this.medQuan = medQuan;
    }

    public int getSongId(){
        return songId;
    }

    public void setSongId(int songId){
        this.songId = songId;
    }

    public int getSelectCode(){
        return selectCode;
    }

    public void setSelectCode(int selectCode){
        this.selectCode = selectCode;
    }

    public int getCountCode(){
        return countCode;
    }

    public void setCountCode(int countCode){
        this.countCode = countCode;
    }

    public ArrayList<String> getCodeList(){
        return codeList;
    }

    public void setCodeList(ArrayList codeList){
        this.codeList = codeList;
    }

}
