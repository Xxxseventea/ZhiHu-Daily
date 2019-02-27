package com.example.asus.myhomework.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toolbar;

import com.example.asus.myhomework.adapter.MyViewPagerAdapter;
import com.example.asus.myhomework.R;

import java.util.ArrayList;
import java.util.List;

public class Comment_main extends AppCompatActivity implements View.OnClickListener{
    private TabLayout tabLayout;
    private List<String> titles;
    private ViewPager viewPager;
    private List<Fragment>fragments;
    public static String long_comments = null;
    public static String short_comments = null;
    private PopupWindow popupWindow;
    private Button button;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_main);
        tabLayout = findViewById(R.id.tb_id);
        viewPager = findViewById(R.id.vp_id);
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {                              //设置菜单
            actionBar.setDisplayHomeAsUpEnabled(true);   //让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.mipmap.back);   //设置导航按钮的图标
        }



        titles = new ArrayList<>();
        titles.add("短评论");
        titles.add("长评论");

        fragments = new ArrayList<>();
        fragments.add(new Comment_Short());
        fragments.add(new Comment_Long());


        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            button = findViewById(R.id.btn_write);
            button.setOnClickListener(this);

//    private void showPopupWindow() {
//        View contentView = LayoutInflater.from(Comment_main.this).inflate(R.layout.popuwindow,null);
//        popupWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
//        popupWindow.setContentView(contentView);
//
//        EditText editText = findViewById(R.id.et_comment);
//        Button button = findViewById(R.id.release);
//
//        View rootview = LayoutInflater.from(Comment_main.this).inflate(R.layout.popuwindow,null);
//        popupWindow.showAtLocation(rootview,Gravity.BOTTOM,0,0);
//
//    }


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_write){
            Intent intent = new Intent(Comment_main.this,PopupWindow.class);
            startActivity(intent);
        }
    }
}