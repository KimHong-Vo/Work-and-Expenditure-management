package com.example.workandexpendituremanagement.model;

import com.example.workandexpendituremanagement.model.Time;

public class Work {
    private  String name;
//    private String type;
    private  String description;
    private boolean isFinish;
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private  Date endDate;
    private Type type;

    // empty constructor
    public Work(){}


    public Work(String name, Time startTime, Time endTime, boolean isFinish, Date startDate, Date endDate, Type type){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFinish = isFinish;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public Work(String name, Time startTime, Time endTime, boolean isFinish, Date startDate, Date endDate){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFinish = isFinish;
        this.startDate = startDate;
        this.endDate = endDate;
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
    public String getDescription(){
        return this.description;
    }

    public Time getStartTime(){
        return  startTime;
    }

    public Time getEndTime(){
        return  endTime;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Type getType() {
        return type;
    }

    public boolean getIsfinish(){
        return  isFinish;
    }
}
