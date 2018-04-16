package com.example.austi_000.appproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Calendar;
import java.util.Date;

import hirondelle.date4j.DateTime;

public class CalActivity extends AppCompatActivity {
    CalendarView cald;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.austi_000.appproject.R.layout.activity_cal);
        date_entity[] results=balanceDb.getAppDatabase(getApplicationContext()).dateDao().loadAllDates();
        final CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.cald, caldroidFragment);
        t.commit();

        ColorDrawable bg = new ColorDrawable(getResources().getColor(R.color.caldroid_holo_blue_dark));
        for(int i=0;i<results.length;i++){
            Long dateLong=Long.valueOf(results[i].date);
            Date resultDate=new Date(dateLong);
            caldroidFragment.setBackgroundDrawableForDate(bg,resultDate);

        }

        CaldroidListener list = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                final String dateTime=String.valueOf(date.getTime());
                //Checks if the selected date already has events created for it
                final int existingEventsQResult=balanceDb.getAppDatabase(getApplicationContext()).dateDao().dateQuery(String.valueOf(date.getTime()));
                //Creating the alert dialog for the user
                //BEGIN CODE FOR DIALOG
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CalActivity.this);
                alertDialogBuilder
                        .setTitle("Calender Event Menu")
                        .setIcon(R.drawable.event)
                        .setMessage("What would you like to do?")
                        .setPositiveButton("Create New Event", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent=new Intent(getApplicationContext(), CreateEventActivity.class);
                                intent.putExtra("date",String.valueOf(dateTime));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("View Events",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if(existingEventsQResult>0){
                                            Intent intent=new Intent(getApplicationContext(),eventsActivity.class);
                                            intent.putExtra("dateTime", dateTime);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(CalActivity.this, "This Date Has No Events", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                        }

                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                //END CODE FOR DIALOG

            }

        };
        caldroidFragment.setCaldroidListener(list);
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}



