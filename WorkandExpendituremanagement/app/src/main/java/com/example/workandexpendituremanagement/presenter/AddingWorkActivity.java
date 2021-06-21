package com.example.workandexpendituremanagement.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workandexpendituremanagement.R;
import com.example.workandexpendituremanagement.model.Date;
import com.example.workandexpendituremanagement.model.Time;
import com.example.workandexpendituremanagement.model.Work;
import com.example.workandexpendituremanagement.model.WorkEntity;

public class AddingWorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_work);
    }


    public void clickAddBtn(){
        EditText workName = findViewById(R.id.txtTen);
        EditText discription = findViewById(R.id.txtMota);
        EditText startDate = findViewById(R.id.txtStartDate);
//        EditText startTime = findViewById(R.id.txtStartTime);
//        EditText endDate = findViewById(R.id.txtEnđate);
//        EditText endTime = findViewById(R.id.txtEndTime);
//        to do something
        if (checkWorkInfor(null, null, null, null, null, null)){
            if(WorkEntity.addWork(new Work())){
                startActivity(new Intent(this, MainActivity.class));
            }
//           Alternal 3
            else {
                Toast.makeText(this, "Them khong thanh cong", Toast.LENGTH_SHORT).show();
            }
        }
        else
            return;
    }

    public boolean checkWorkInfor(String workName, String discription, Date startDate, Time startTime, Date endDate, Time endTime){
//        to do something
        if(workName == null || discription == null || startDate == null || startTime == null || endDate == null || endTime == null)
            return false;
        if (startDate.isEqualOther(endDate)) {
            return startTime.isEarlyThanOther(endTime);
        }
        else {
            if (startDate.isEarlyThanOther(endDate)){
                return true;
            }
            else return false;
        }
    }

    public void clickCancelBtn(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

}