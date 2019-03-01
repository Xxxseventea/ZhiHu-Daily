package com.example.asus.myhomework.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.asus.myhomework.R;
import com.sackcentury.shinebuttonlib.ShineButton;

import static com.example.asus.myhomework.view.MainActivity.REALID;


public class Details extends AppCompatActivity {
    private WebView webView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_view);
        toolbar = findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){                              //设置菜单
            actionBar.setDisplayHomeAsUpEnabled(true);   //让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.mipmap.back);//设置导航按钮的图标
            actionBar.setHomeButtonEnabled(true);
        }

        ShineButton shineButton = findViewById(R.id.btn_like);
        shineButton.init(this);
       initView();

    }

    private void initView() {
        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);//支持javascript脚本
        webView.setWebViewClient(new WebViewClient());//从一个页面跳到另一个页面时，仍在当前页面显示
        Intent intent = getIntent();
        webView.loadUrl("http://daily.zhihu.com/story/"+REALID);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.detail_item,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.discuss:
                Intent intent = new Intent(Details.this,Comment_main.class);
                startActivity(intent);
                break;
                case android.R.id.home:
                    this.finish();
                    break;
        }
        return true;
    }
}
