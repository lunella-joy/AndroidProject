package cn.edu.swpu.kotlintest.entity;

import java.io.Serializable;

public class Webdata implements Serializable{
    private String thistitleurl;
    private String pictureurl;
    private String title;
    private String date;
    private String nextpageurl;
    public Webdata(){
    }

    public Webdata(String thistitleurl, String pictureurl, String nextpageurl, String title, String date){
        this.date = date;
        this.thistitleurl = thistitleurl;
        this.pictureurl = pictureurl;
        this.nextpageurl = nextpageurl;
        this.title = title;
    }

    public String getThistitleurl() {
        return thistitleurl;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public String getNextpageurl() {
        return nextpageurl;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
