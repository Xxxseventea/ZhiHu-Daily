package com.example.asus.myhomework.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.myhomework.adapter.Home_RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.example.asus.myhomework.bean.TopStories;
import com.example.asus.myhomework.bean.Stories;
import com.example.asus.myhomework.R;
import com.example.asus.myhomework.tool.TimeUtil;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import cn.bingoogolapple.bgabanner.BGABanner;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Button button;
    private RecyclerView home_recyclerview;
    private Home_RecyclerViewAdapter home_recyclerViewAdapter;
    private ArrayList<Stories> data;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static ArrayList<Integer> ids = new ArrayList<>();
    public static int REALID;
    private NavigationView navigationView;
    private static String path = "/sdcard/myHead/";//从sd中找图片
    private Bitmap headSet;
    private CircleImageView head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBar actionBar = getSupportActionBar();
        navigationView = findViewById(R.id.nav_view);
        //button = findViewById(R.id.log_in);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
        initData();
        initView();
        if (actionBar != null) {                              //设置菜单
            actionBar.setDisplayHomeAsUpEnabled(true);   //让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.mipmap.meun);   //设置导航按钮的图标
        }
        navigationView.setCheckedItem(R.id.main);    //main菜单为默认选中
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

        View headview = navigationView.inflateHeaderView(R.layout.nav_header_before);
        head = headview.findViewById(R.id.de_head);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(MainActivity.this,"23333",Toast.LENGTH_SHORT).show();
                showTypeDialog();


            }
        });
    }



    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_select, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();

    }





    private void setHeader(RecyclerView home_recyclerview, ArrayList<String> image,ArrayList<String> title) {
        View header = LayoutInflater.from(this).inflate(R.layout.home_head_item,home_recyclerview,false);
        BGABanner banner = (BGABanner) header.findViewById(R.id.banner);
        banner.setAdapter(new BGABanner.Adapter<ImageView,String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String modol1,int position) {
                Glide.with(MainActivity.this)
                        .load(modol1)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }

        });
        banner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                //此处可设置banner子项点击事件
            }
        });
        banner.setData(image,title);
        home_recyclerViewAdapter.setHeadView(header);
    }

    private void inithome_item() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL( "https://news-at.zhihu.com/api/4/news/latest");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer response1 = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response1.append(line);
                    }

                    String response = String.valueOf(response1);
                    JSONObject jsonObject = new JSONObject(response);
                    try {
                        int date = jsonObject.getInt("date");
                        String stories = jsonObject.getString("stories");
                        String top_stories = jsonObject.getString("top_stories");

                        JSONArray StoriesArray = new JSONArray(stories);
                        for (int i = 0; i < StoriesArray.length(); i++) {
                            JSONObject jsonObjectData = StoriesArray.getJSONObject(i);
                            Stories stories_2 = new Stories();
                            stories_2.setType(jsonObjectData.getInt("type"));
                            stories_2.setId(jsonObjectData.getInt("id"));
                            stories_2.setGa_prefix(jsonObjectData.getString("ga_prefix"));
                            stories_2.setTitle(jsonObjectData.getString("title"));
                            JSONArray imageArray = jsonObjectData.getJSONArray("images");
                            stories_2.setImages(imageArray.getString(0));
                            data.add(stories_2);
                           ids.add(jsonObjectData.getInt("id"));
                        }


                        JSONArray Top_stories = new JSONArray(top_stories);
                        ArrayList<TopStories> bannArrayList = new ArrayList<>();
                        ArrayList<String> title = new ArrayList<>();
                        ArrayList<String> image = new ArrayList<>();
                        for (int i = 0; i <Top_stories.length(); i++) {
                            JSONObject jsonObjectData = Top_stories.getJSONObject(i);
                            TopStories top_stories_bean = new TopStories();
                            top_stories_bean.setId(jsonObjectData.getInt("id"));
                            top_stories_bean.setTitle(jsonObjectData.getString("title"));
                            top_stories_bean.setImage(jsonObjectData.getString("image"));
                            bannArrayList.add(top_stories_bean);
                            title.add(top_stories_bean.getTitle());
                            image.add(top_stories_bean.getImages());
                        }
                        setHeader(home_recyclerview,image,title);
                        home_recyclerViewAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    OkHttpClient httpClient = new OkHttpClient();
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("https://news-at.zhihu.com/api/4/news/before/"+new TimeUtil().getDate()).build();
                    Response response3 = okHttpClient.newCall(request).execute();
                    String response2 = response3.body().string();
                    JSONObject jsonObject1 = new JSONObject(response2);
                    JSONArray jsonArray = new JSONArray(jsonObject1.getString("stories"));
                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject beforestories = jsonArray.getJSONObject(i);
                        Stories stories_ = new Stories();
                        stories_.setTitle(beforestories.getString("title"));
                        JSONArray imageArray = beforestories.getJSONArray("images");
                        stories_.setImages(imageArray.getString(0));
                        data.add(stories_);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }

                }
            }
        }).start();
    }

    private void initView() {
        home_recyclerview = (RecyclerView) findViewById(R.id.home_recyclerview);
        home_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        home_recyclerViewAdapter = new Home_RecyclerViewAdapter(data,MainActivity.this);
        home_recyclerview.setAdapter(home_recyclerViewAdapter);
        home_recyclerViewAdapter.setOnItemClickListener(new Home_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(MainActivity.this,Details.class);
////                startActivity(intent);
                REALID = ids.get(position);
                Intent intent = new Intent(MainActivity.this,Details.class);
               // intent.putExtra("id",stories_bean2.getId());
                startActivity(intent);
            }
        });
    }


    private void initData() {
        data = new ArrayList<>();
        inithome_item();
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this,"this is settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.msg:
                Toast.makeText(this,"this is msg",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(headSet);// 保存在SD卡中
                        head.setImageBitmap(headSet);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

