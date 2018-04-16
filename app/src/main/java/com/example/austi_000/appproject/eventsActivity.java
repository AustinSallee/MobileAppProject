package com.example.austi_000.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class eventsActivity extends AppCompatActivity {
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        listview=findViewById(R.id.list);

        String dateTime=getIntent().getExtras().getString("dateTime");
        date_entity []results=balanceDb.getAppDatabase(getApplicationContext()).dateDao().loadAllEvents(dateTime);
        dateObject [] datesToList=new dateObject[results.length];

        for(int i=0;i<results.length;i++){
            datesToList[i]=new dateObject();
            datesToList[i].id=String.valueOf(results[i].id);
            datesToList[i].title=results[i].title;
            datesToList[i].dateTime=results[i].date;
            datesToList[i].des=results[i].des;
            datesToList[i].allDay=results[i].allDay;
            datesToList[i].startTime=results[i].startTime;
            datesToList[i].endTime=results[i].endTime;
        }
        ArrayList<dateObject> toEnterView = new ArrayList<>();
        for(int i=0;i<results.length;i++){
            toEnterView.add(datesToList[i]);
        }

        Toast.makeText(this, String.valueOf(results.length),
                Toast.LENGTH_LONG).show();
        ListAdapter customAdapter = new ListAdapter(this, R.layout.itemlist, toEnterView);

        listview.setAdapter(customAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listview.getItemAtPosition(i);
                dateObject d =null;
                if(o instanceof dateObject){
                    d = (dateObject) o;
                    Intent intent=new Intent(getApplicationContext(), EditEventActivity.class);
                    intent.putExtra("id", Integer.valueOf(d.id));
                    intent.putExtra("title", d.title);
                    intent.putExtra("des", d.des);
                    intent.putExtra("start", d.startTime);
                    intent.putExtra("end", d.endTime);
                    intent.putExtra("allday", d.allDay);
                    startActivity(intent);

                }
            }
        });
    }

}