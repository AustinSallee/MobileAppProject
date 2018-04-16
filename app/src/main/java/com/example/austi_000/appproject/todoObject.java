package com.example.austi_000.appproject;

/**
 * Created by austi_000 on 3/19/2018.
 */

public class todoObject {
    private String title;
    private String des;
    private String importance;

    public todoObject(String t, String d, String i) {
        title = t;
        des = d;
        importance = i;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public String getImportance(){
        return importance;
    }
}
