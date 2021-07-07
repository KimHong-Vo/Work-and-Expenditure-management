package com.example.workandexpendituremanagement.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class WorkEntity {

    public static boolean addWork(Work work, Context context){
      ConnectDB db = new ConnectDB(context,ConnectDB.DATABASE_NAME,null,ConnectDB.VERSION);
        return db.insertWork(work);
    }
    public static List<Work> getWorkInDay(Context context){
        List<Work> listWork = new ArrayList<>();
        ConnectDB db = new ConnectDB(context,ConnectDB.DATABASE_NAME,null,ConnectDB.VERSION);
        listWork=db.selectWorkInday();
        return listWork;
    }
}
