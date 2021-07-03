package com.example.workandexpendituremanagement.presenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.workandexpendituremanagement.R;
import com.example.workandexpendituremanagement.model.CategoryWork;
import com.example.workandexpendituremanagement.model.Check;
import com.example.workandexpendituremanagement.model.SpinnerWorkAdapter;
import com.example.workandexpendituremanagement.model.Work;

import java.util.ArrayList;
import java.util.Calendar;

public class AddingWorkActivity extends AppCompatActivity  {
    ImageView ivBack;
    ArrayList<CategoryWork> list;
    AppCompatSpinner spinner;
    SpinnerWorkAdapter adapter;
    TextView tvTimeBegin,tvTimeFinish,tvDateFinish,tvDateBegin,tvBtnAdd,tvNameError;
    EditText txtNameWork,txtDescription;
    Button btnError ;
    Work work;
//    ConnectDb db = new ConnectDb();
    String nameWork="";
    int img =0;
    String category ="";
    String description="";
    int dayBegin=0;
    int monthBegin=0;
    int yearBegin=0;
    int hourBegin =0;
    int minuteBegin=0;
    int dayFinish=0;
    int monthFinish=0;
    int yearFinish=0;
    int hourFinish =0;
    int minuteFinish=0;

    String [] categories = {"Công việc","Thể dục","Ăn uống","Cuộc họp"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_work);

        work = new Work();


        ivBack=findViewById(R.id.ivBack);
        txtDescription=findViewById(R.id.txtDescription);
        txtNameWork=findViewById(R.id.txtNameWork);



        tvBtnAdd=findViewById(R.id.tvBtnAdd);
        tvTimeBegin=findViewById(R.id.tvTimeBegin);
        tvTimeFinish=findViewById(R.id.tvTimeFinish);
        tvDateBegin=findViewById(R.id.tvDateBegin);
        tvDateFinish=findViewById(R.id.tvDateFinish);

        list = CategoryWork.categoryWorkArrayList();
        spinner=findViewById(R.id.spinnerCategory);
        adapter = new SpinnerWorkAdapter(list);
        spinner.setAdapter(adapter);




        final Calendar c= Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month=c.get(Calendar.MONTH);
        final int day=c.get(Calendar.DAY_OF_MONTH);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute =c.get(Calendar.MINUTE);



        //Dialog
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.check_error);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        tvNameError=dialog.findViewById(R.id.tvNameError);
        btnError = dialog.findViewById(R.id.btnCloseDialog);
        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvDateBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddingWorkActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tvDateBegin.setText(day+" / "+(month+1)+" / "+year);
                        dayBegin=day;
                        monthBegin=month+1;
                        yearBegin=year;
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        tvDateFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddingWorkActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tvDateFinish.setText(day+" / "+(month+1)+" / "+year);
                        dayFinish=day;
                        monthFinish=month+1;
                        yearFinish=year;
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        tvTimeBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddingWorkActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        tvTimeBegin.setText(hour+":"+minute);
                        hourBegin=hour;
                        minuteBegin=minute;
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });
        tvTimeFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddingWorkActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        tvTimeFinish.setText(hour+":"+minute);
                        hourFinish=hour;
                        minuteFinish=minute;
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });
        tvBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameWork=String.valueOf(txtNameWork.getText());
                description=String.valueOf(txtDescription.getText());
                Check check= new Check();
                if(check.checkName(String.valueOf(txtNameWork.getText()))){
                    if(check.checkDate(dayBegin,monthBegin,yearBegin) && check.checkDate(dayFinish,monthFinish,yearFinish)){
                        returnMainacitvity();
                    }else if(check.checkDateEqual(dayBegin,monthBegin,yearBegin)&& check.checkDateEqual(dayFinish,monthFinish,yearFinish))
                        if(check.checkTime(hourBegin,minuteBegin)&& check.checkTime(hourFinish,minuteFinish)){
                            returnMainacitvity();
                        }else{
                            tvNameError.setText("Bạn chọn sai giờ hoặc phút");
                            dialog.show();
                        }
                    else{
                        tvNameError.setText("Bạn chọn sai ngày hoặc tháng hoặc năm");
                        dialog.show();
                    }
                }else{
                    tvNameError.setText("Tên công việc không được để trống");
                    dialog.show();
                }


            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category=categories[i];
                img=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    public void returnMainacitvity(){
        Intent data = new Intent();
        data.putExtra("NAME_WORK",nameWork);
        data.putExtra("IMG",img);
        data.putExtra("CATEGORY",category);
        data.putExtra("DESCRIPTION",description);
        data.putExtra("DAYBEGIN",dayBegin);
        data.putExtra("MONTHBEGIN",monthBegin);
        data.putExtra("YEARBEGIN",yearBegin);
        data.putExtra("HOURBEGIN",hourBegin);
        data.putExtra("MINUTEBEGIN",minuteBegin);
        data.putExtra("DAYFINISH",dayFinish);
        data.putExtra("MONTHFINISH",monthFinish);
        data.putExtra("YEARFINISH",yearFinish);
        data.putExtra("HOURFINISH",hourFinish);
        data.putExtra("MINUTE",minuteFinish);
        setResult(Activity.RESULT_OK,data);
        finish();
    }



}