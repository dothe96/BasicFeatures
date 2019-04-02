package com.example.basicfeatures;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class WriteCalendarActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 0;
    private Button writeCalendar;
    private TextView resultMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_calendar);
        writeCalendar = findViewById(R.id.writeCalendar);
        resultMessage = findViewById(R.id.resultMessage);
        writeCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeCalendarToPhone();
            }
        });
        if (ContextCompat.checkSelfPermission(WriteCalendarActivity.this,
                Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(WriteCalendarActivity.this,
                    Manifest.permission.WRITE_CALENDAR)) {
            } else {
                ActivityCompat.requestPermissions(WriteCalendarActivity.this,
                        new String[]{Manifest.permission.WRITE_CALENDAR},
                        MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
            }
        } else {
        }
    }
    public void writeCalendarToPhone(){
        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2019, 3, 24, 7, 30);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2019, 3, 24, 8, 45);
        endMillis = endTime.getTimeInMillis();
        ContentValues contentValues = new ContentValues();
        ContentResolver contentResolver = getContentResolver();
        contentValues.put(CalendarContract.Events.CALENDAR_ID, 1);
        contentValues.put(CalendarContract.Events.TITLE,"dcodka");
        contentValues.put(CalendarContract.Events.DESCRIPTION,"No Description");
        contentValues.put(CalendarContract.Events.DTSTART,startMillis);
        contentValues.put(CalendarContract.Events.DTEND,endMillis);
        contentValues.put(CalendarContract.Events.ALL_DAY,1);
        contentValues.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles");
        contentResolver.insert(CalendarContract.Events.CONTENT_URI,contentValues);
        resultMessage.setText("Added Events!!!");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_CALENDAR: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }
}
