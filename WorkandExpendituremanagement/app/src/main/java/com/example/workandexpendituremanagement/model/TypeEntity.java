package com.example.workandexpendituremanagement.model;

import android.content.Context;

import java.util.List;

public class TypeEntity {
    public static List<Type> getType(Context c){
        ConnectDB connectdb = new ConnectDB(c);
        return connectdb.selectAllType();
    }
    public static void toTypesString(Context c){
        List<Type> tl = getType(c);
        for (int i =0; i< tl.size(); i++){
            System.out.println(tl.get(i).toString());
        }
    }
}
