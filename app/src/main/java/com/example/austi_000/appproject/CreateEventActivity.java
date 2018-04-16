package com.example.austi_000.appproject;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CreateEventActivity extends AppCompatActivity {
    private TextView startTime;
    private TextView endTime;
    private TextView dateView;
    private EditText titleEdit;
    private EditText desEdit;
    private CheckBox allDayCheck;
    private Button createBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        //Finding the view of all important UI elements
        startTime = findViewById(R.id.startTimeText);
        endTime = findViewById(R.id.endTimeText);
        dateView = findViewById(R.id.dateView);
        titleEdit = findViewById(R.id.titleEdit);
        desEdit = findViewById(R.id.desEdit);
        allDayCheck = findViewById(R.id.allDayCheck);
        createBtn = findViewById(R.id.createBtn);
        //Pulling the date from the intent and displaying it at top of page for user
        final String passedDate = getIntent().getStringExtra("date");
        final String dateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(Long.valueOf(passedDate)));
        dateView.setText(dateString);
        //Setting the time picker dialog for start time
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //eReminderTime.setText( selectedHour + ":" + selectedMinute);

                        if (selectedMinute < 10) {
                            startTime.setText(selectedHour + ":" + "0" + selectedMinute);
                        } else {
                            startTime.setText(selectedHour + ":" + selectedMinute);
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        //Setting the time picker dialog for end time
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedMinute < 10) {
                            endTime.setText(selectedHour + ":" + "0" + selectedMinute);
                        } else {
                            endTime.setText(selectedHour + ":" + selectedMinute);
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        //Setting events for button click
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fieldCheck()) {
                    String title = titleEdit.getText().toString();
                    String des = desEdit.getText().toString();
                    String start = startTime.getText().toString();
                    String end = endTime.getText().toString();
                    String dateTime = dateString;
                    String allday = "NO";
                    if (allDayCheck.isChecked()) {
                        allday = "YES";
                    }
                    date_entity toInsert = new date_entity();
                    toInsert.title = title;
                    toInsert.des = des;
                    toInsert.startTime = start;
                    toInsert.endTime = end;
                    toInsert.date = passedDate;
                    toInsert.allDay = allday;
                    Toast.makeText(CreateEventActivity.this, toInsert.endTime, Toast.LENGTH_SHORT).show();


                    balanceDb.getAppDatabase(getApplicationContext()).dateDao().insertDate(toInsert);
                    Toast.makeText(CreateEventActivity.this, "Event Has Been Created", Toast.LENGTH_SHORT).show();
                    finishAffinity();
                    Intent intent = new Intent(getApplicationContext(), CalActivity.class);
                    startActivity(intent);
                }//end if check for fields
                else{
                    Toast.makeText(CreateEventActivity.this, "Please Fill Out All Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public boolean fieldCheck() {
        if (titleEdit.length() > 0 && desEdit.length() > 0 && ((startTime.length() > 0 && endTime.length() > 0) || allDayCheck.isChecked())) {
            return true;
        }
        return false;
    }
}

