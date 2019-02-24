package com.example.asus.myhomework.Bean;

public class Top_stories_Bean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

    public void setType(int type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String images) {
        this.image = images;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getImages() {
            return image;
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
