package com.example.workandexpendituremanagement.model;

import java.util.Calendar;

public class Date {
    private int day;
    private  int month;
    private  int year;
    public Date(){}
    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean isEarlyThanOther(Date date){
        if(this.year < date.year)
            return true;
        else {
            if (this.year > date.year)
                return false;
            if (this.month < date.month)
                return true;
            else
                return (this.month == date.month && this.day < date.day);
        }
    }

    public boolean isEqualOther(Date other){
        return (this.day == other.day && this.month == other.month && this.year == other.year);
    }

    public String toString(){
        return day + "/" + month +"/" +year;
    }

    public boolean checkDate(){
//        is this date later than current date
        Calendar c = Calendar.getInstance();
        Date currentDate = new Date(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
        return (isEqualOther(currentDate) || !isEarlyThanOther(currentDate));

    }
}
