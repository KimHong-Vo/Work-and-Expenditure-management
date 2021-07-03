package com.example.workandexpendituremanagement.model;

import android.text.TextUtils;

import java.util.Calendar;

public class Check {
    Calendar c = Calendar.getInstance();
    public Check() {
    }

    public boolean checkName(String name){
        if(!TextUtils.isEmpty(name)) {
            return true;
        }else{
            return false;
        }
    }
   public boolean checkDate(int day,int month,int year){
        int daySetting = c.get(Calendar.DAY_OF_MONTH);
        int monthSetting = c.get(Calendar.MONTH);
        int yearSetting = c.get(Calendar.YEAR);
        if(year<=yearSetting){
            if(month<=monthSetting){
                if(day<=daySetting){
                    return false;
                }else{
                    return true;
                }
            }else{
                return  true;
            }
        }else {
            return true;
        }
    }
    public boolean checkDateEqual(int day,int month,int year){
        int daySetting = c.get(Calendar.DAY_OF_MONTH);
        int monthSetting = c.get(Calendar.MONTH);
        int yearSetting = c.get(Calendar.YEAR);
        if(year==yearSetting){
            if(month==monthSetting){
                if(day==daySetting){
                    return true;
                }else{
                    return false;
                }
            }else{
                return  false;
            }
        }else {
            return false;
        }
    }

    public boolean checkTime(int hour, int minute){
        int hourSetting = c.get(Calendar.HOUR_OF_DAY);
        int minuteSetting = c.get(Calendar.MINUTE);

        if(hour<hourSetting){
            if(minute<minuteSetting){
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }


    }


}

