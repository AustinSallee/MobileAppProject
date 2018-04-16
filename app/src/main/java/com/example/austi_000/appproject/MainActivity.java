package com.example.austi_000.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button calBtn;
    private Button doBtn;
    private Button noteBtn;
    private Button alarmBtn;
    private Button settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting up buttons and references
        calBtn=findViewById(R.id.calanderBtn);
        doBtn=findViewById(R.id.todoBtn);
        noteBtn=findViewById(R.id.noteBtn);
        alarmBtn=findViewById(R.id.alarmBtn);
        settingsBtn=findViewById(R.id.prefBtn);

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CalActivity.class);
                startActivity(intent);

            }
        });
        doBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TodoActivity.class);
                startActivity(intent);

            }
        });
        noteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),NotesActivity.class);
                startActivity(intent);

            }
        });
        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AlarmActivity.class);
                startActivity(intent);
            }
        });
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Settings does not have navagation currently because settings will depend on features included in final project", Toast.LENGTH_LONG).show();

            }
        });
    }
}