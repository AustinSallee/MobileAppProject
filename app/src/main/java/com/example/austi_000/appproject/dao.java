package com.example.austi_000.appproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by austi_000 on 3/15/2018.
 */

@Dao
public interface dao {
    @Insert
    public long insertDate(date_entity toInsert);

    @Query("SELECT * FROM dates")
    public date_entity[] loadAllDates();

    @Query("SELECT * FROM dates where date = :selectedDate")
    public date_entity[] loadAllEvents(String selectedDate);

    @Query("UPDATE dates SET title =:title, des =:des, startTime =:start, endTime =:end, allDay =:allday WHERE id=:givenid")
    public int updateEvent(String title, String des,String start,String end, String allday, int givenid);

    @Query("SELECT * FROM dates WHERE date = :selectedDate")
    public int dateQuery(String selectedDate);

    @Query("DELETE FROM dates WHERE id =:givenid")
    public int deleteEvent(int givenid);
}