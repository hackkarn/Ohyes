package com.ohyes.ohyes;

import android.app.Application;
import java.util.ArrayList;

/**
 * Created by Xesnost on 2/11/2560.
 */

public class GlobalClass extends Application{

    public ArrayList<String> timeHour;
    public ArrayList<String> timeMin;
    public String username;
    public String userid;
    public ArrayList<String> alarmStatus;
    public int count;
    public ArrayList<String> medlist;
    public String medName;
    public String medQuan;
    public int songId;
    public int selectCode;
    public int countCode;
    public ArrayList<String> codeList; //alarm number in addalarm page

    public ArrayList<String> getTimeHour(){
        return timeHour;
    }

    public void setTimeHour(ArrayList<String> timeHour){
        this.timeHour = timeHour;
    }

    public ArrayList<String> getTimeMin(){
        return timeMin;
    }

    public void setTimeMin(ArrayList<String> timeMin){
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

    public ArrayList<String> getAlarmStatus(){
        return alarmStatus;
    }

    public void setAlarmStatus(ArrayList<String> alarmStatus){
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
