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
import java.util.List;

public class SpinnerWorkAdapter extends BaseAdapter {
private List<Type> typeList;
private Context context;

    public SpinnerWorkAdapter(List<Type> list) {
        this.typeList = list;
    }

    @Override
    public int getCount() {
        return typeList.size();
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.spinner_work,viewGroup,false);

        Type type = typeList.get(i);

        ImageView ivCategory = view.findViewById(R.id.ivCategory);
        TextView tvCategory = view.findViewById(R.id.tvNameWorkCategory);

        switch (type.getId()){
            case 1 :  ivCategory.setImageResource(R.drawable.icon_work); break;
            case 2 :  ivCategory.setImageResource(R.drawable.icon_excercise); break;
            case 3 :  ivCategory.setImageResource(R.drawable.icon_eat); break;
            case 4 :  ivCategory.setImageResource(R.drawable.icon_meet); break;
            default: ivCategory.setImageResource(R.drawable.icon_work); break;
        }

        tvCategory.setText(type.getName());

        return view;
    }

    public List<Type> getTypeList(){
        return this.typeList;
    }
}
