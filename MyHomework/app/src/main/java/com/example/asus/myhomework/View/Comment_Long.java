package com.example.asus.myhomework.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.myhomework.adapter.Comment_RecyclerViewAdapter;
import com.example.asus.myhomework.bean.Comment;
import com.example.asus.myhomework.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.asus.myhomework.view.MainActivity.REALID;

public class Comment_Long extends Fragment {
    private ArrayList<Comment> arrayList;
    private Comment_RecyclerViewAdapter comment_recyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_coment_long_frag,container,false);
//        RecyclerView recyclerView = view.findViewById(R.id.comment_recyclerview_long);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        comment_recyclerViewAdapter = new Comment_RecyclerViewAdapter(arrayList,getContext());
//        recyclerView.setAdapter(comment_recyclerViewAdapter);
        initData();
        RecyclerView recyclerView = view.findViewById(R.id.comment_recyclerview_long);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        comment_recyclerViewAdapter = new Comment_RecyclerViewAdapter(arrayList,getContext());
        recyclerView.setAdapter(comment_recyclerViewAdapter);
        return view;
    }

    private void initData () {
        arrayList = new ArrayList<>();
        getcomment();
    }

    public void getcomment () {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // HttpURLConnection connection = null;
                //BufferedReader reader = null;
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("https://news-at.zhihu.com/api/4/story/" + REALID + "/long-comments").build();
                    Response response1 = okHttpClient.newCall(request).execute();
                    String response = response1.body().string();


                    try {
                        // response = String.valueOf(response1);
                        JSONObject jsonObject = new JSONObject(response);
                        String comments = jsonObject.getString("comments");

                        JSONArray commentArray = new JSONArray(comments);
                        for (int i = 0; i < commentArray.length(); i++) {
                            JSONObject jsonObject1 = commentArray.getJSONObject(i);
                            Comment comment_ = new Comment();
                            comment_.setAuthor(jsonObject1.getString("author"));
                            comment_.setAvatar(jsonObject1.getString("avatar"));
                            comment_.setContent(jsonObject1.getString("content"));
                            // comment_.setId(jsonObject1.getInt("id"));
                            //comment_.setTime(jsonObject1.getInt("time"));
                            comment_.setTime(jsonObject1.getString("time"));
                            comment_.setLikes(jsonObject1.getString("likes"));
                            arrayList.add(comment_);
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
