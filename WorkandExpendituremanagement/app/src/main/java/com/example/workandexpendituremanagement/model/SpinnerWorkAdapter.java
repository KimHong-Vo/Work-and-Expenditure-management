package com.example.workandexpendituremanagement.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workandexpendituremanagement.R;

import java.util.ArrayList;

public class SpinnerWorkAdapter extends BaseAdapter {
ArrayList<CategoryWork> list;
Context context;

    public SpinnerWorkAdapter(ArrayList<CategoryWork> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CategoryWork categoryWork = list.get(i);
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.spinner_work,viewGroup,false);

        ImageView ivCategory = view.findViewById(R.id.ivCategory);
        TextView tvCategory = view.findViewById(R.id.tvNameWorkCategory);

        ivCategory.setImageResource(categoryWork.getImg());
        tvCategory.setText(categoryWork.getName());

        return view;
    }
}
