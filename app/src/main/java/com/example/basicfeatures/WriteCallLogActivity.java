package com.example.basicfeatures;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WriteCallLogActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_CALL_LOG = 0;
    private Button writeCallLog;
    private TextView resultMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_call_log);
        writeCallLog = findViewById(R.id.writeCallLog);
        resultMessage = findViewById(R.id.resultMessage);
        writeCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeCallLogToPhone();
            }
        });
        if (ContextCompat.checkSelfPermission(WriteCallLogActivity.this,
                Manifest.permission.WRITE_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(WriteCallLogActivity.this,
                    Manifest.permission.WRITE_CALL_LOG)) {
            } else {
                ActivityCompat.requestPermissions(WriteCallLogActivity.this,
                        new String[]{Manifest.permission.WRITE_CALL_LOG},
                        MY_PERMISSIONS_REQUEST_WRITE_CALL_LOG);
            }
        } else {
        }
    }
    public void writeCallLogToPhone(){
        String phoneNumber = "00465464";
        ContentValues values = new ContentValues();
        values.put(CallLog.Calls.NUMBER, phoneNumber);
        values.put(CallLog.Calls.DATE, System.currentTimeMillis());
        values.put(CallLog.Calls.DURATION, 69);
        values.put(CallLog.Calls.TYPE, CallLog.Calls.OUTGOING_TYPE);
        values.put(CallLog.Calls.NEW, 1);
        values.put(CallLog.Calls.CACHED_NAME, "");
        values.put(CallLog.Calls.CACHED_NUMBER_TYPE, 0);
        values.put(CallLog.Calls.CACHED_NUMBER_LABEL, "");
        ContentResolver contentResolver = getContentResolver();
        contentResolver.insert(CallLog.Calls.CONTENT_URI, values);
        resultMessage.setText("Added: "+phoneNumber);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_CALL_LOG: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }
}
