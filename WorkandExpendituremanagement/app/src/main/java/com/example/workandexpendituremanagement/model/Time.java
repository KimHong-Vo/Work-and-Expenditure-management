package com.example.workandexpendituremanagement.model;

public class Time {
    int minute;
    int hour;
    public Time( int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public String toString(){
        return hour + ":" + minute;
    }
    public String getStringTime(){
        if (hour <10 && minute < 10){
            return "0" + hour + ":" + "0" + minute;
        }
        if (hour <10 && minute >= 10){
            return "0" + hour + ":" + minute;
        }
        if (hour >10 && minute < 10){
            return hour + ":" + "0" + minute;
        }
        return hour + ":" + minute;
    }

    public boolean isEarlyThanOther(Time endTime) {
        if(this.hour < endTime.hour)
            return true;
        else
            if (this.hour > endTime.hour || this.minute > endTime.minute)
                return false;
            return true;

    }
}
