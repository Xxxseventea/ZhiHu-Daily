package com.example.asus.myhomework.View;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.asus.myhomework.Adapter.MyViewPagerAdapter;
import com.example.asus.myhomework.Bean.Other_Bean;
import com.example.asus.myhomework.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.asus.myhomework.View.MainActivity.realid;

public class comment_main extends AppCompatActivity {
    private TabLayout tabLayout;
    private List<String> titles;
    private ViewPager viewPager;
    private List<Fragment>fragments;
    public static String long_comments = null;
    public static String short_comments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_main);
        tabLayout = findViewById(R.id.tb_id);
        viewPager = findViewById(R.id.vp_id);


//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url("https://news-at.zhihu.com/api/4/story-extra/"+realid).build();
//        try {
//            Response response = client.newCall(request).execute();
//            String response1 = response.body().string();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        titles = new ArrayList<>();
        titles.add("短评论");
        titles.add("长评论");

        fragments = new ArrayList<>();
        fragments.add(new Comment_Short());
        fragments.add(new Comment_Long());

       // tabLayout.addTab(tabItem.setTooltipTe;);
//       tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
//       tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url("https://news-at.zhihu.com/api/4/story-extra/"+realid).build();
//        try {
//            Response response = client.newCall(request).execute();
//            String response1 = response.body().string();
//
//            JSONObject jsonObject = new JSONObject(response1);
//            Other_Bean other_bean = new Other_Bean();
//            other_bean.setLong_comment(jsonObject.getString("long_comments"));
//            other_bean.setShort_comment(jsonObject.getString("short_comments"));
//            long_comments = other_bean.getLong_comment();
//            short_comments = other_bean.getShort_comment();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
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
    }
}
