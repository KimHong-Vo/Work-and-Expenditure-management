package com.example.workandexpendituremanagement.presenter;


import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workandexpendituremanagement.R;

public class AddingWorkActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.adding_work);
    }

    public void clickAddBtn(){
//        to do something
    }

    public boolean checkWorkInfor(){
//        to do something
        return  false;
    }
}
