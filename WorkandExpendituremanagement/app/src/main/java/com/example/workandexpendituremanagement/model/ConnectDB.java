package com.example.workandexpendituremanagement.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConnectDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "workandexpendDB";
    public static final int VERSION = 1;

    public ConnectDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("SQLite", "MyDatabaseHelper.onCreate ... ");
        sqLiteDatabase.execSQL(doCreateTypeTable());
        sqLiteDatabase.execSQL(doCreateWorkTable());
        sqLiteDatabase.execSQL(doCreateTimeTable());
        ContentValues values = new ContentValues();
//
//        Add type
        values.put("id", 1);
        values.put("name", "sự kiện");
        sqLiteDatabase.insert("Type", null, values);

        values = new ContentValues();
        values.put("id", 2);
        values.put("name", "Họp");
        sqLiteDatabase.insert("Type", null, values);

        values = new ContentValues();
        values.put("id", 3);
        values.put("name", "Học tập");
        sqLiteDatabase.insert("Type", null, values);

        values = new ContentValues();
        values.put("id", 4);
        values.put("name", "Giải trí");
        sqLiteDatabase.insert("Type", null, values);

        values = new ContentValues();
        values.put("id", 5);
        values.put("name", "Routine");
        sqLiteDatabase.insert("Type", null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("SQLite", "MyDatabaseHelper.onUpgrade... ");
    }

    public ConnectDB getConnection(){
        // to do some thing
        return null;
    }

    public boolean insertWork(Work work){
        // to do some thing
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            int workId = 0;
            ContentValues values = new ContentValues();
            values.put("id", workId);
            values.put("name", work.getName());
            values.put("discription", work.getDescription());
            values.put("idType", work.getType().getId());
            String message = "";
            boolean isInSertWork = db.insert("Work", null, values) >0;
//            check for insert work successfully
            if(isInSertWork){
//                insert time of work
            values = new ContentValues();
            values.put("id", work.getType().getId());
            values.put("startTime", work.getStartTime().toString());
            values.put("startDate", work.getStartDate().toString());
            values.put("endTime", work.getEndTime().toString());
            values.put("endDate", work.getEndDate().toString());
            boolean isInsertTimeOfWork = db.insert("Work", null, values) >0;
            if (isInsertTimeOfWork)
                return true;
            else
            {
                db.delete("work", "id = ?", new String[] {"" + workId});
                return false;
            }
            }
            else  return  false;

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public List<Work> selectWorkInday(){
        List<Work> workList = new ArrayList<>();
        String sql="SELECT * FROM Time t JOIN Work w ON t.id=w.id JOIN Type t1 ON w.idType=w.id";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String[] startTimeString = cursor.getString(1).split(":");
                Time startTime = new Time(Integer.parseInt(startTimeString[0]), Integer.parseInt(startTimeString[2]));
                Date dateStart = new Date (Integer.parseInt(cursor.getString(2).split("/")[0]),Integer.parseInt(cursor.getString(2).split("/")[1]),Integer.parseInt(cursor.getString(2).split("/")[2]));
                Time endTime  =  new Time (Integer.parseInt(cursor.getString(3).split(":")[0]),Integer.parseInt(cursor.getString(3).split(":")[1]));
                Date dateEnd = new Date (Integer.parseInt(cursor.getString(4).split("/")[0]),Integer.parseInt(cursor.getString(4).split("/")[1]),Integer.parseInt(cursor.getString(4).split("/")[2]));
                String name = cursor.getString(5);
                String description = cursor.getString(6);
             Type type = new Type(cursor.getInt(7),cursor.getString(8));
                Work work = new Work( name,  startTime,  endTime, true ,  dateStart,  dateEnd,  type);
                workList.add(work);
            }while (cursor.moveToFirst());
        }else{

        }
        cursor.close();
        db.close();
        return workList;
    }

    public String doCreateTypeTable(){
            String script = "CREATE TABLE Type (";
            script += "id INTEGER primary key,";
            script += "typename TEXT)";
           return  script;

    }

    public String doCreateWorkTable() {
            String script = "CREATE TABLE Work (";
            script += "id INTEGER primary key,";
            script += "name TEXT,";
            script += "discription TEXT," ;
            script += "idType INTEGER," +
                    "FOREIGN KEY(idType) REFERENCES Type(id))";
            return script;
    }

    public String doCreateTimeTable(){
            String script = "CREATE TABLE Time (";
            script += "id INTEGER primary key,";
            script += "startTime TEXT,";
            script += "startDate TEXT,";
            script += "endTime TEXT,";
            script += "endDate TEXT," +
                    "FOREIGN KEY(id) REFERENCES Work(id))";
            return script;

    }

}
