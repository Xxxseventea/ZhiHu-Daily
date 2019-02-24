package com.example.asus.myhomework.Bean;

public class Stories_Bean {
    private String images;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private int position;

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImages() {
        return images;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getTitle() {
        return title;
    }
}
