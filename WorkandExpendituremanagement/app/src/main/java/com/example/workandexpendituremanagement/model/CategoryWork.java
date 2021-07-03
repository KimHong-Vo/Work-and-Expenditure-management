package com.example.workandexpendituremanagement.model;

import com.example.workandexpendituremanagement.R;

import java.util.ArrayList;

public class CategoryWork {
    private int img;
    private String name;

    public CategoryWork(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }
    public static ArrayList<CategoryWork> categoryWorkArrayList(){
        ArrayList<CategoryWork> list = new ArrayList<>();
        list.add(new CategoryWork(R.drawable.icon_work,"Công việc"));
        list.add(new CategoryWork(R.drawable.icon_excercise,"Thể dục"));
        list.add(new CategoryWork(R.drawable.icon_eat,"Ăn uống"));
        list.add(new CategoryWork(R.drawable.icon_meet,"Cuộc hợp"));
        return list;
    }
}
