package com.covid19.covid19india.Models;

public class Card {
    private String title, desc;
    String bmp;

    public Card(String title, String desc, String URL) {
        this.desc = desc;
        this.title = title;
        bmp = URL;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public String getBmp() {
        return bmp;
    }
}
