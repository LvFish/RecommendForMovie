package com.newclass.bean;

/**
 * Created by liujiawang on 2019/3/5.
 */
public class MovieDetail {
    private String name;
    private String url;
    private int year;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MovieDetail(String name, String url, int year, String type) {
        this.name = name;
        this.url = url;
        this.year = year;
        this.type = type;
    }
}
