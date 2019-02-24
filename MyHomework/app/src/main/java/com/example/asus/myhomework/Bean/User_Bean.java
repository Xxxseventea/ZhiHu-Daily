package com.example.asus.myhomework.Bean;

public class User_Bean {
    private int date;
    private String stories;
    private String top_stoires;
    public User_Bean(int date,String stories,String top_stoires){
        this.date = date;
        this.stories = stories;
        this.top_stoires = top_stoires;
    }

    public int getDate() {
        return date;
    }

    public String getStories() {
        return stories;
    }

    public String getTop_stoires() {
        return top_stoires;
    }
}
