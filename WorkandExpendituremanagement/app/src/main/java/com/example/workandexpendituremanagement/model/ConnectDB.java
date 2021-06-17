package com.example.workandexpendituremanagement.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

public class ConnectDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "workandexpendDB";
    public static final int VERSION = 1;

    public ConnectDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ConnectDB getConnection(){
        // to do some thing
        return null;
    }

    public boolean insertWork(Work work){
        // to do some thing
        return false;
    }

    public List<Work> selectWorkInday(){
        //to do some thing
        return null;
    }
}
