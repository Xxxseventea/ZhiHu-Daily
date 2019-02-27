package com.example.asus.myhomework.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.myhomework.bean.Comment;
import com.example.asus.myhomework.R;
import com.example.asus.myhomework.tool.TimeUtil;

import java.util.ArrayList;

public class Comment_RecyclerViewAdapter extends RecyclerView.Adapter<Comment_RecyclerViewAdapter.ViewHoler> {
    private Context context;
    private ArrayList<Comment> commentdata;
    public Comment_RecyclerViewAdapter(ArrayList<Comment> commentdata, Context context){
        this.commentdata = commentdata;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.comment_item, viewGroup, false);
            ViewHoler viewHoler = new ViewHoler(view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.content.setText(commentdata.get(i).getContent());
        viewHoler.name.setText(commentdata.get(i).getAuthor());
        viewHoler.likes.setText(commentdata.get(i).getLikes());
        String date;
        TimeUtil timeUtil = new TimeUtil();
        date = timeUtil.stampToDate(commentdata.get(i).getTime()+"000");
        viewHoler.time.setText(date);
        Glide.with(context).load(commentdata.get(i).getAvatar()).into(viewHoler.head);
    }

    @Override
    public int getItemCount() {
        return commentdata.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView content,name,likes,time;
        ImageView head;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.comment_content);
            name = itemView.findViewById(R.id.name);
            head = itemView.findViewById(R.id.head);
            likes = itemView.findViewById(R.id.likes_count);
            time = itemView.findViewById(R.id.time);
        }
    }
}
