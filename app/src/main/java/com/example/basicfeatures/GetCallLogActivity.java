package com.example.basicfeatures;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class GetCallLogActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CALL_LOG = 0;
    private Button getCallLog;
    private TextView listCallLog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_call_log);
        getCallLog = findViewById(R.id.getCallLog);
        listCallLog = findViewById(R.id.listCallLog);
        getCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListCallLog();
            }
        });
        if (ContextCompat.checkSelfPermission(GetCallLogActivity.this,
                Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(GetCallLogActivity.this,
                    Manifest.permission.READ_CALL_LOG)) {
            } else {
                ActivityCompat.requestPermissions(GetCallLogActivity.this,
                        new String[]{Manifest.permission.READ_CALL_LOG},
                        MY_PERMISSIONS_REQUEST_READ_CALL_LOG);
            }
        } else {
        }
    }
    public void getListCallLog(){
        StringBuffer buffer = new StringBuffer();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI, null, null,null, null);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        buffer.append("Call Details :");
        while (cursor.moveToNext()) {
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = cursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            buffer.append("\nPhone Number: " + phNumber + " \nCall Type: "
                    + dir + " \nCall Date: " + callDayTime
                    + " \nCall duration in sec: " + callDuration);
            buffer.append("\n----------------------------------");
        }
        cursor.close();
        listCallLog.setText(buffer.toString());
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALL_LOG: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }
}
