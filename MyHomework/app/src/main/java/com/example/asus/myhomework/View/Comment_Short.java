package com.example.asus.myhomework.View;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asus.myhomework.Adapter.Comment_RecyclerViewAdapter;
import com.example.asus.myhomework.Bean.Comment_Bean;
import com.example.asus.myhomework.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.asus.myhomework.View.MainActivity.realid;


//author	"andy小陆"
//content	"《小仙有毒》里，温家的入室弟子考题有一年是“硕鼠”。温家大伯温吞海当年应试的毒方是采尽天下至甜至香之物，密炼熬成一碗甜羹，无毒且馥郁甘甜。但人一旦饮下此羹，尝到了那绝世甜香，之后哪怕是喝蜂蜜也会觉得腥臭苦涩无比，止不住的呕吐，从此世上可食之物就只剩一个味道：苦，最终竟把人活活饿死！正应了考题“硕鼠”。。"
//avatar	"http://pic3.zhimg.com/4953f864a_im.jpg"
//time	1479737963
//id	27279755
//likes	 9
public class Comment_Short extends Fragment{
    private Toolbar toolbar;
    private Button button;
    private RecyclerView recyclerView;
    private ArrayList<Comment_Bean> commentdata;
    private Comment_RecyclerViewAdapter comment_recyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    // private String response;

    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_coment_short_frag);
//         button = findViewById(R.id.btn_comment);
//       toolbar = findViewById(R.id.toolbar_comment);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {                              //设置菜单
//            actionBar.setDisplayHomeAsUpEnabled(true);   //让导航按钮显示出来
//            actionBar.setHomeAsUpIndicator(R.mipmap.back);   //设置导航按钮的图标
//        }


    @Nullable
   // @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_coment_short_frag,container,false);

//    swipeRefreshLayout = view.findViewById(R.id.comment_fresh);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                       // runOnUiThread(new Runnable() {
//                           // @Override
//                           // public void run() {
//                                swipeRefreshLayout.setRefreshing(false);
//                            }
//                      //  });
//                   // }
//                }).start();
//            }
//        });
        initData();
        recyclerView =view. findViewById(R.id.comment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        comment_recyclerViewAdapter = new Comment_RecyclerViewAdapter(commentdata, getContext());
        recyclerView.setAdapter(comment_recyclerViewAdapter);
        //initView();
        return view;
    }
//        ShineButton shineButton = findViewById(R.id.btn_like);
//        shineButton.init(this);

//        public void initView () {
//            recyclerView = findViewById(R.id.comment_recyclerview);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            comment_recyclerViewAdapter = new Comment_RecyclerViewAdapter(commentdata, Comment_Short.this);
//            recyclerView.setAdapter(comment_recyclerViewAdapter);
//        }

        private void initData () {
            commentdata = new ArrayList<>();
            getcomment();
        }

       // @Override
//        public void onClick (View v){
//            switch (v.getId()) {
//                case R.id.btn_comment:
//                    Intent intent = new Intent();
//                    startActivity(intent);
//            }
//        }


        public void getcomment () {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // HttpURLConnection connection = null;
                    //BufferedReader reader = null;
                    try {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        Request request = new Request.Builder().url("https://news-at.zhihu.com/api/4/story/" + realid + "/short-comments").build();
                        Response response1 = okHttpClient.newCall(request).execute();
                        String response = response1.body().string();


                        try {
                            // response = String.valueOf(response1);
                            JSONObject jsonObject = new JSONObject(response);
                            String comments = jsonObject.getString("comments");

                            JSONArray commentArray = new JSONArray(comments);
                            for (int i = 0; i < commentArray.length(); i++) {
                                JSONObject jsonObject1 = commentArray.getJSONObject(i);
                                Comment_Bean comment_bean = new Comment_Bean();
                                comment_bean.setAuthor(jsonObject1.getString("author"));
                                comment_bean.setAvatar(jsonObject1.getString("avatar"));
                                comment_bean.setContent(jsonObject1.getString("content"));
                                // comment_bean.setId(jsonObject1.getInt("id"));
                                //comment_bean.setTime(jsonObject1.getInt("time"));
                                comment_bean.setTime(jsonObject1.getString("time"));
                                comment_bean.setLikes(jsonObject1.getString("likes"));
                                commentdata.add(comment_bean);
                            }
                            comment_recyclerViewAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
