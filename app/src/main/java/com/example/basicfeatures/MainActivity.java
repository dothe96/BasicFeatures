package com.example.basicfeatures;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button getContacts;
    private Button writeContacts;
    private Button getCallLog;
    private Button writeCallLog;
    private Button getMessage;
    private Button writeMessage;
    private Button getCalendar;
    private Button writeCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContacts = findViewById(R.id.getContacts);
        writeContacts = findViewById(R.id.writeContacts);
        getCallLog = findViewById(R.id.getCallLog);
        writeCallLog = findViewById(R.id.writeCallLog);
        getMessage = findViewById(R.id.getMessage);
        writeMessage = findViewById(R.id.writeMessage);
        getCalendar = findViewById(R.id.getCalendar);
        writeCalendar = findViewById(R.id.writeCalendar);
        getContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GetContactsActivity.class);
                startActivity(intent);
            }
        });
        getCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GetCallLogActivity.class);
                startActivity(intent);
            }
        });
        getMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetMessageActivity.class);
                startActivity(intent);
            }
        });
        getCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetCalendarActivity.class);
                startActivity(intent);
            }
        });
        writeContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteContactsActivity.class);
                startActivity(intent);
            }
        });
        writeCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteCallLogActivity.class);
                startActivity(intent);
            }
        });
        writeCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteCalendarActivity.class);
                startActivity(intent);
            }
        });
        writeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteMessageActivity.class);
                startActivity(intent);
            }
        });
    }
}
