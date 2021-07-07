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
import com.example.workandexpendituremanagement.model.Date;
import com.example.workandexpendituremanagement.model.SpinnerWorkAdapter;
import com.example.workandexpendituremanagement.model.Time;
import com.example.workandexpendituremanagement.model.Type;
import com.example.workandexpendituremanagement.model.Work;
import com.example.workandexpendituremanagement.model.WorkEntity;

import java.sql.Array;
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
//    ConnectDb db = new ConnectDb();
    int img =0;
    String category ="";
    String [] categories = {"Công việc","Thể dục","Ăn uống","Cuộc họp"};
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_work);

        dialog = new Dialog(this);

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

        tvDateBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddingWorkActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tvDateBegin.setText(day+" / "+(month+1)+" / "+year);
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
                    }
                },hour,minute,true);
                timePickerDialog.show();
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

    public void clickAddBtn(View view) {
        String name = txtNameWork.getText().toString();
        String discription = txtDescription.getText().toString();
        String[] startStringDate = tvDateBegin.getText().toString().split("/");
        Date startDate = new Date(Integer.parseInt(startStringDate[0].trim()), Integer.parseInt(startStringDate[1].trim()), Integer.parseInt(startStringDate[2].trim()));
        String[] endStringDate = tvDateFinish.getText().toString().split("/");
        Date endDate = new Date(Integer.parseInt(endStringDate[0].trim()), Integer.parseInt(endStringDate[1].trim()), Integer.parseInt(endStringDate[2].trim()));
        String[] startStringTime = tvTimeBegin.getText().toString().split(":");
        Time startTime = new Time(Integer.parseInt(startStringTime[0].trim()),Integer.parseInt(startStringTime[1].trim()));
        String[] endStringTime = tvTimeBegin.getText().toString().split(":");
        Time endTime = new Time(Integer.parseInt(endStringTime[0].trim()),Integer.parseInt(endStringTime[1].trim()));

        if(checkWorkInfor(name, discription, startDate, startTime, endDate,endTime)){
            Work work = new Work(name, startTime, endTime, false, startDate, endDate, null);
            if(WorkEntity.addWork(work,this)){
               finish();
            }
            else{
                tvNameError.setText("Thêm công việc thất bại");
                dialog.show();
            }
        }

    }

    public boolean checkWorkInfor(String workName, String discription, Date startDate, Time startTime, Date endDate, Time endTime) {
//        to do something
        String stringError = "";

        if (workName == null || workName.equals(""))
            stringError += "Tên công việc trống!!";

        if (!startDate.checkDate() && !endDate.checkDate())
             stringError += "\n" + "Bạn chọn ngày tháng sai!!";

        if(endDate.isEarlyThanOther(startDate) || endDate.isEqualOther(startDate))
            stringError += "\n" + "Ngày bắt đầu phải cùng hoặc sau ngày kết thúc";

        if(endDate.isEqualOther(startDate) && endTime.isEarlyThanOther(startTime))
            stringError += "\n" + "Giờ bắt đầu phải trước giờ kết thúc";
        if(stringError.equals(""))
            return true;
        else {
            tvNameError.setText(stringError);
            dialog.show();
            return false;
        }
    }

    public void clickCancelBtn(View view) {
                finish();
    }
}