package com.example.asus.myhomework.View;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

import com.example.asus.myhomework.R;

public class Log_in extends AppCompatActivity{
   private Button button_log;
   private Button button_register;
   private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        button_log = findViewById(R.id.btn_log);
        button_register = findViewById(R.id.btn_register);
        toolbar = findViewById(R.id.log_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back);
        }
    }
}
