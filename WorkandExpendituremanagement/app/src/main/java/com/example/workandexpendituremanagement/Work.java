package com.example.workandexpendituremanagement;

public class Work {
    String name;
    Time startTime, endTime;
    public Work(String name, Time startTime, Time endTime){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Work(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getSpaceStringTime(){
        return startTime.getStringTime() + " - " + endTime.getStringTime();
    }
}
