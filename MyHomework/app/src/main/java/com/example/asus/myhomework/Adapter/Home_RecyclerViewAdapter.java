package com.example.asus.myhomework.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.myhomework.R;

import java.util.ArrayList;

import com.example.asus.myhomework.Bean.Stories_Bean;

public class Home_RecyclerViewAdapter extends RecyclerView.Adapter<Home_RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<Stories_Bean> data;

    public Home_RecyclerViewAdapter(ArrayList<Stories_Bean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (headView != null && i == TYPE_HEADER) return new ViewHolder(headView);
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.home_item, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        //此方法内可以对布局中的控件进行操作

        if (getItemViewType(i) == TYPE_HEADER) return;
        else {
            final int pos = getRealPosition(viewHolder);

            viewHolder.title.setText(data.get(pos).getTitle());

            Glide.with(context).load(data.get(pos).getImages()).into(viewHolder.imageView);
            if(mOnItemClickListener != null){
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getRealPosition(viewHolder);
                        mOnItemClickListener.onItemClick(viewHolder.itemView,pos);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return headView == null ? data.size() : data.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View headView;

    public void setHeadView(View headView) {
        this.headView = headView;
        notifyItemInserted(0);
    }

    public View getHeadView() {
        return headView;
    }

    public int getItemViewType(int position) {
        if (headView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headView == null ? position : position - 1;
    }




    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private OnItemClickListener mOnItemClickListener;;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}