package com.example.workandexpendituremanagement.presenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.workandexpendituremanagement.R;
import com.example.workandexpendituremanagement.model.Date;
import com.example.workandexpendituremanagement.model.Time;
import com.example.workandexpendituremanagement.model.Type;
import com.example.workandexpendituremanagement.model.TypeEntity;
import com.example.workandexpendituremanagement.model.Work;
import com.example.workandexpendituremanagement.model.WorkEntity;

import java.util.Calendar;

public class AddingWorkActivity extends AppCompatActivity  {
    ImageView ivBack;
    AppCompatSpinner spinner;
    SpinnerWorkAdapter adapter;
    TextView tvTimeBegin,tvTimeFinish,tvDateFinish,tvDateBegin,tvNameError;
    EditText txtNameWork,txtDescription;
    Button btnError, btnAdd ;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_work);

        dialog = new Dialog(this);

        ivBack=findViewById(R.id.ivBack);
        txtDescription=findViewById(R.id.txtDescription);
        txtNameWork=findViewById(R.id.txtNameWork);

        btnAdd =findViewById(R.id.tvBtnAdd);
        tvTimeBegin=findViewById(R.id.tvTimeBegin);
        tvTimeFinish=findViewById(R.id.tvTimeFinish);
        tvDateBegin=findViewById(R.id.tvDateBegin);
        tvDateFinish=findViewById(R.id.tvDateFinish);

        spinner=findViewById(R.id.spinnerCategory);
        adapter = new SpinnerWorkAdapter(TypeEntity.getType(this));
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

    }

    public void clickAddBtn(View view) {
//        get input infor
        String name = txtNameWork.getText().toString();
        String discription = txtDescription.getText().toString();
        Date startDate = null,endDate = null;
        Time startTime = null, endTime = null;

        String[] startStringDate = tvDateBegin.getText().toString().split("/");
        if(startStringDate.length==3) {
            startDate = new Date(Integer.parseInt(startStringDate[0].trim()), Integer.parseInt(startStringDate[1].trim()), Integer.parseInt(startStringDate[2].trim()));
        }
        String[] endStringDate = tvDateFinish.getText().toString().split("/");
        if(endStringDate.length==3) {
            endDate = new Date(Integer.parseInt(endStringDate[0].trim()), Integer.parseInt(endStringDate[1].trim()), Integer.parseInt(endStringDate[2].trim()));
        }
        String[] startStringTime = tvTimeBegin.getText().toString().split(":");
        if(startStringTime.length==2) {
            startTime = new Time(Integer.parseInt(startStringTime[0].trim()), Integer.parseInt(startStringTime[1].trim()));
        }
        String[] endStringTime = tvTimeFinish.getText().toString().split(":");
        if (endStringTime.length==2)
            endTime = new Time(Integer.parseInt(endStringTime[0].trim()),Integer.parseInt(endStringTime[1].trim()));
        Type type = (Type) adapter.getTypeList().get(spinner.getSelectedItemPosition());

        if(checkWorkInfor(name, discription, startDate, startTime, endDate,endTime)){
            Work work = new Work(name, startTime, endTime, false, startDate, endDate, type);
            work.setDescription(discription);
            if(WorkEntity.addWork(work,this)){
               finish();
            }
            else{
                tvNameError.setText("Th??m c??ng vi???c th???t b???i");
                dialog.show();
            }
        }

    }

    public boolean checkWorkInfor(String workName, String discription, Date startDate, Time startTime, Date endDate, Time endTime) {
//        to do something
        String stringError = "";

        if (workName == null || workName.equals(""))
            stringError += "T??n c??ng vi???c tr???ng!! \n";

        if(startDate==null|| endDate == null || startTime == null || endTime == null){
            stringError +="Ch??a nh???p ????? th???i gian, ng??y th??ng \n";
        }
        else{
            if (!startDate.checkDate() && !endDate.checkDate())
                stringError +="B???n ch???n ng??y th??ng sai!! \n";
            if(endDate.isEarlyThanOther(startDate))
                stringError += "Ng??y b???t ?????u ph???i c??ng ho???c sau ng??y k???t th??c \n";
            else if (endDate.isEqualOther(startDate)){
                if (endTime.isEarlyThanOther(startTime))
                    stringError += "Gi??? b???t ?????u ph???i tr?????c gi??? k???t th??c \n";
            }
        }
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