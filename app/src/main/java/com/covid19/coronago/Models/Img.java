package com.covid19.coronago.Models;

public class Img {
    private int ID;
    private String URL;

    public Img(int b, String u) {
        ID = b;
        URL = u;
    }

    public int getID() {
        return ID;
    }

    public String getURL() {
        return URL;
    }
}
