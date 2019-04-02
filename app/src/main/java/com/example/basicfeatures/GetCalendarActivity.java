package com.example.basicfeatures;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class GetCalendarActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 0;
    private Button getCalendar;
    private TextView listCalendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_calendar);
        getCalendar = findViewById(R.id.getCalendar);
        listCalendar = findViewById(R.id.listCalendar);
        getCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListCalendar();
            }
        });
        if (ContextCompat.checkSelfPermission(GetCalendarActivity.this,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(GetCalendarActivity.this,
                    Manifest.permission.READ_CALENDAR)) {
            } else {
                ActivityCompat.requestPermissions(GetCalendarActivity.this,
                        new String[]{Manifest.permission.READ_CALENDAR},
                        MY_PERMISSIONS_REQUEST_READ_CALENDAR);
            }
        } else {
        }
    }
    public void getListCalendar(){
        StringBuffer buffer = new StringBuffer();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(CalendarContract.Events.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex(CalendarContract.Events._ID));
            String title = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));
            String location = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION));
            String description = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION));
            String dtStart = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTSTART));
            String dtEnd = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTEND));
            String allDay = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.ALL_DAY));
            buffer.append("ID: "+id+", Title: "+title+", Location: "+location+", Description: "+description+"\nDate Start: "+dtStart+
                    "\nDate End: "+dtEnd+", All Day: "+allDay+"\n----------------\n\n");
        }
        cursor.close();
        listCalendar.setText(buffer.toString());
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALENDAR: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }
}
