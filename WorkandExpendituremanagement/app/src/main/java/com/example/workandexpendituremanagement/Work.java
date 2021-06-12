package com.example.workandexpendituremanagement;

public class Work {
    private  String name;
    private boolean isFinish;
    private Time startTime, endTime;
    public Work(String name, Time startTime, Time endTime, boolean isFinish){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFinish = isFinish;
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

    public boolean getIsfinish(){
        return  isFinish;
    }
}
