package com.covid19.covid.Models;

public class State {
    private String nam;
    private int tot, act, rec, dea;

    public State(String name, int total, int active, int recoverd, int death) {
        nam = name;
        tot = total;
        act = active;
        rec = recoverd;
        dea = death;
    }

    public String getNam() {
        return nam;
    }

    public int getAct() {
        return act;
    }

    public int getDea() {
        return dea;
    }

    public int getRec() {
        return rec;
    }

    public int getTot() {
        return tot;
    }
}
