package com.example.workandexpendituremanagement.presenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.workandexpendituremanagement.R;
import com.example.workandexpendituremanagement.model.ConnectDB;
import com.example.workandexpendituremanagement.model.Date;
import com.example.workandexpendituremanagement.model.Time;
import com.example.workandexpendituremanagement.model.TypeEntity;
import com.example.workandexpendituremanagement.model.Work;
import com.example.workandexpendituremanagement.model.WorkEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListView();
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.work);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.work:
                        return true;
//                    case R.id.spen:
//                        startActivity(new Intent(getApplicationContext(), SpenActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.report:
//                        startActivity(new Intent(getApplicationContext(), ReportActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.consume:
//                        startActivity(new Intent(getApplicationContext(), ConsumeActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
                }
                return false;
            }
        });

    }

    public void initListView(){
        List<Work> works = WorkEntity.getWorkInDay(this);
        WorkListAdaper adaper = new WorkListAdaper(this, works);
        ListView lv = findViewById(R.id.work_List_View);
        lv.setAdapter(adaper);
    }

    public void clickAddBtn(View addBtn) {
        // to do some thing
        Intent intent = new Intent(MainActivity.this, AddingWorkActivity.class);
        startActivity(intent);

    }
}