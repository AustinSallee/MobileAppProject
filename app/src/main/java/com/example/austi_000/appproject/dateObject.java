package com.example.austi_000.appproject;

/**
 * Created by austi_000 on 3/15/2018.
 */

public class dateObject {
    public String id;
    public String dateTime;
    public String startTime;
    public String endTime;
    public String allDay;
    public String title;
    public String des;

    public dateObject(){

    }

    public dateObject(String t,String d, String x){
        title=t;
        des=d;
    }

    public dateObject(String dateTime,String startTime, String endTime, String allDay, String title, String des, String date){
        this.dateTime=dateTime;
        this.startTime=startTime;
        this.endTime=endTime;
        this.allDay=allDay;
        this.title=title;
        this.des=des;
    }
}
