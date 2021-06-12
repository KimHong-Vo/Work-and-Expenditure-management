package com.example.workandexpendituremanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WorkListAdaper extends ArrayAdapter<Work> {

    public WorkListAdaper(@NonNull Context context, int resource, @NonNull Work[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){// convertView is layout for listView
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.work_list_view_layout, parent, false);
        }
//        get view in listItem
        TextView tv1 = (TextView) convertView.findViewById(R.id.work_name_TV);
        TextView tv2 = (TextView) convertView.findViewById(R.id.work_time_TV);
        ImageView img = (ImageView) convertView.findViewById(R.id.work_state_img);
        Work work = (Work) getItem(position);
        tv1.setText(work.getName());
        tv2.setText(work.getSpaceStringTime());
        if (work.getIsfinish())
            img.setImageResource(R.drawable.finish_work);
        else
            img.setImageResource(R.drawable.un_finish_work);
        return convertView;
    }
}
