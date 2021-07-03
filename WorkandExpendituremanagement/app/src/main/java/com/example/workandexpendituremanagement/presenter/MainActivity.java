package com.example.workandexpendituremanagement.presenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.workandexpendituremanagement.R;
import com.example.workandexpendituremanagement.model.Date;
import com.example.workandexpendituremanagement.model.Time;
import com.example.workandexpendituremanagement.model.Work;
import com.example.workandexpendituremanagement.model.WorkEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        initListView();
    }

    public void initListView(){
//        List<Work> works = WorkEntity.getWorkInDay();
//        WorkListAdaper adaper = new WorkListAdaper(this, works);
//        ListView lv = findViewById(R.id.work_List_View);
//        lv.setAdapter(adaper);

        Work[] listWork = new Work[2];
        listWork[0] = new Work("Tập thể dục", new Time(0, 3), new Time(30, 3), true, new Date(), new Date());
        listWork[1] = new Work("Học trực tuyến", new Time(0, 8), new Time(30, 9), false, new Date(), new Date());
        WorkListAdaper adaper = new WorkListAdaper(this, listWork);
        ListView lv = findViewById(R.id.work_List_View);
        lv.setAdapter(adaper);
    }

    public void clickAddBtn(View addBtn) {
        // to do some thing
        Intent intent = new Intent(MainActivity.this, AddingWorkActivity.class);
        startActivity(intent);
    }
}