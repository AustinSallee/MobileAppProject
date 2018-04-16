package com.example.austi_000.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class EditEventActivity extends AppCompatActivity {
    TextView title;
    TextView des;
    TextView start;
    TextView end;
    CheckBox allday;
    Button create;
    Button del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        title=findViewById(R.id.titleEdit);
        des=findViewById(R.id.desEdit);
        start=findViewById(R.id.startTimeText);
        end=findViewById(R.id.endTimeText);
        allday=findViewById(R.id.allDayCheck);
        create=findViewById(R.id.createBtn);
        del.findViewById(R.id.delBtn);

        title.setText(getIntent().getExtras().getString("title"));
        des.setText(getIntent().getExtras().getString("des"));
        start.setText(getIntent().getExtras().getString("start"));
        end.setText(getIntent().getExtras().getString("end"));
        if(getIntent().getExtras().getString("allday").equals("YES")){
            allday.setChecked(true);
        }
        else{
            allday.setChecked(false);
        }
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle=title.getText().toString();
                String newDes=des.getText().toString();
                String newStart=start.getText().toString();
                String newEnd=end.getText().toString();
                String newallday="";
                if(allday.isChecked()){
                    newallday="YES";
                }
                else{
                    newallday="NO";
                }
                int res= balanceDb.getAppDatabase(getApplicationContext()).dateDao().updateEvent(newTitle,newDes,newStart,newEnd,newallday,getIntent().getExtras().getInt("id"));
                Toast.makeText(EditEventActivity.this, "Event Updated", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), CalActivity.class);
                startActivity(intent);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res =balanceDb.getAppDatabase(getApplicationContext()).dateDao().deleteEvent(getIntent().getExtras().getInt("id"));
                if(res>0){
                    Toast.makeText(EditEventActivity.this, "Event Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),CalActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
