package com.example.workandexpendituremanagement.presenter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.workandexpendituremanagement.R;
import com.example.workandexpendituremanagement.model.Work;

import java.util.List;

public class WorkListAdaper extends ArrayAdapter<Work> {
    private Activity context;
    private List<Work> data;
    public WorkListAdaper(@NonNull Activity context, @NonNull List<Work> data) {
        super(context, 0, data);
        this.context = context;
        this.data = data;
    }

//    public WorkListAdaper(@NonNull Context context, @NonNull Work[] objects) {
//        super(context, 0, objects);
//    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row =inflater.inflate(R.layout.work_list_view_layout, null);
//        get view in listItem
        TextView tv1 = (TextView) row.findViewById(R.id.work_name_TV);
        TextView tv2 = (TextView) row.findViewById(R.id.work_time_TV);
        ImageView img = (ImageView) row.findViewById(R.id.work_state_img);
        Work work = (Work) getItem(position);
        tv1.setText(work.getName());
        tv2.setText(work.getSpaceStringTime());
        if (work.getIsfinish())
            img.setImageResource(R.drawable.finish_work);
        else
            img.setImageResource(R.drawable.un_finish_work);
        return row;
    }
}
