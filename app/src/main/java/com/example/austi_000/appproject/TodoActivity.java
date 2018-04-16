package com.example.austi_000.appproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        listview=findViewById(R.id.todoList);

        todoObject[] toList=new todoObject[15];
        ArrayList toEnterView=new ArrayList<todoObject>();
        for(int i=0;i<toList.length;i++){
            toList[i]=new todoObject("Sample Title", "Sample Des.", "Sample Importance");
            toEnterView.add(toList[i]);

        }
        TodoListAdapter customAdapter = new TodoListAdapter(this, R.layout.todoitemlist, toEnterView);

        listview.setAdapter(customAdapter);
    }
}
