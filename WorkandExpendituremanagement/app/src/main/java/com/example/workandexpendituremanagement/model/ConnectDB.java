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
import java.util.Calendar;
import java.util.List;

public class ConnectDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "workandexpendDB";
    public static final int VERSION = 3;

    public ConnectDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);

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
        values.put("typename", "Công việc");
        sqLiteDatabase.insert("Type", null, values);

        values = new ContentValues();
        values.put("id", 2);
        values.put("typename", "Thể dục");
        sqLiteDatabase.insert("Type", null, values);

        values = new ContentValues();
        values.put("id", 3);
        values.put("typename", "Ăn uống");
        sqLiteDatabase.insert("Type", null, values);

        values = new ContentValues();
        values.put("id", 4);
        values.put("typename", "Cuộc họp");
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
// get number of work to determine current work id
            int workId = getNumberOfWork(db) +1;

            ContentValues values = new ContentValues();
            values.put("id", workId);
            values.put("name", work.getName());
            values.put("discription", work.getDescription());
            values.put("isFinish", work.isFinish()?0:1);
            values.put("idType", work.getType().getId());
            String message = "";
            boolean isInSertWork = db.insert("Work", null, values) != -1;
//            check for insert work successfully
            if(isInSertWork){
//                insert time of work
            values = new ContentValues();
            values.put("id", workId);
            values.put("startTime", work.getStartTime().toString());
            values.put("startDate", work.getStartDate().toString());
            values.put("endTime", work.getEndTime().toString());
            values.put("endDate", work.getEndDate().toString());
            boolean isInsertTimeOfWork = db.insert("Time", null, values) != -1;
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
    final Calendar c= Calendar.getInstance();
    final int year = c.get(Calendar.YEAR);
    final int month=c.get(Calendar.MONTH);
    final int day=c.get(Calendar.DAY_OF_MONTH);

    public List<Work> selectWorkInday(){
        List<Work> workList = new ArrayList<>();
        String sql="SELECT * FROM Time t JOIN Work w ON t.id=w.id JOIN Type t1 ON w.idType=t1.id";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{

                Date dateStart = new Date (Integer.parseInt(cursor.getString(2).split("/")[0]),Integer.parseInt(cursor.getString(2).split("/")[1]),Integer.parseInt(cursor.getString(2).split("/")[2]));
               if(dateStart.isEqualOther(new Date(day,month+1,year))) {
                   int id = cursor.getInt(0);
                   String[] startTimeString = cursor.getString(1).split(":");
                   Time startTime = new Time(Integer.parseInt(startTimeString[0]), Integer.parseInt(startTimeString[1]));
                   Time endTime = new Time(Integer.parseInt(cursor.getString(3).split(":")[0]), Integer.parseInt(cursor.getString(3).split(":")[1]));
                   Date dateEnd = new Date(Integer.parseInt(cursor.getString(4).split("/")[0]), Integer.parseInt(cursor.getString(4).split("/")[1]), Integer.parseInt(cursor.getString(4).split("/")[2]));
                   String name = cursor.getString(6);
                   String description = cursor.getString(7);
                   Type type = new Type(cursor.getInt(9), cursor.getString(11));
                   Work work = new Work(name, startTime, endTime, cursor.getInt(8) == 0 ? true : false, dateStart, dateEnd, type);
                   workList.add(work);
               }
               else{
                   continue;
               }
            }while (cursor.moveToNext());
        }else{

        }
        cursor.close();
        db.close();

        return workList;
    }


    public String doCreateTypeTable(){
            String script = "CREATE TABLE IF NOT EXISTS Type (";
            script += "id INTEGER primary key,";
            script += "typename TEXT)";
           return  script;

    }

    public String doCreateWorkTable() {
            String script = "CREATE TABLE IF NOT EXISTS Work(";
            script += "id INTEGER primary key,";
            script += "name TEXT,";
            script += "discription TEXT," ;
            script += "isFinish INTEGER," ;// 0 is true 1 is false
            script += "idType INTEGER," +
                    "FOREIGN KEY(idType) REFERENCES Type(id))";
            return script;
    }

    public String doCreateTimeTable(){
            String script = "CREATE TABLE IF NOT EXISTS Time (";
            script += "id INTEGER primary key,";
            script += "startTime TEXT,";
            script += "startDate TEXT,";
            script += "endTime TEXT,";
            script += "endDate TEXT," +
                    "FOREIGN KEY(id) REFERENCES Work(id))";
            return script;

    }

    public List<Type> selectAllType() {
        List<Type> typeList = new ArrayList<>();
        String sql="SELECT * FROM Type";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor=db.rawQuery(sql, null);
//        Cursor cursor = db.query("Type", new String[] {"id", "typename"}, null, null, null, null, null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Type t = new Type(cursor.getInt(0), cursor.getString(1));
            typeList.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return typeList;
    }

    private int getNumberOfWork(SQLiteDatabase db){
        String script = "SELECT COUNT(*) FROM Work;";
        Cursor cursor=db.rawQuery(script,null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
//        db.close();
        return result;
    }
}
