package com.example.basicfeatures;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class WriteMessageActivity extends AppCompatActivity {
    private Button writeMessage;
    private TextView resultMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_message);
        writeMessage = findViewById(R.id.writeMessage);
        resultMessage = findViewById(R.id.resultMessage);
        writeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeMessageToPhone();
                //openSMSappChooser(WriteMessageActivity.this);
            }
        });
    }
    public void writeMessageToPhone(){
        String address = "00465464";
        String body = "too foo";
        ContentValues values = new ContentValues();
        values.put("address", address);//sender name
        values.put("body", body);
        getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
        resultMessage.setText("Added ok");
    }
    public static void openSMSappChooser(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, WriteMessageActivity.class);
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        Intent selector = new Intent(Intent.ACTION_MAIN);
        selector.addCategory(Intent.CATEGORY_APP_MESSAGING);
        selector.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(selector);

        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT, PackageManager.DONT_KILL_APP);
    }
}
