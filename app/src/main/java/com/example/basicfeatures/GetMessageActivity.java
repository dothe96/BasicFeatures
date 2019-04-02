package com.example.basicfeatures;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GetMessageActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 0;
    private Button getMessage;
    private TextView listMessage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_message);
        getMessage = findViewById(R.id.getMessage);
        listMessage = findViewById(R.id.listMessage);
        getMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListMessage();
            }
        });
        if (ContextCompat.checkSelfPermission(GetMessageActivity.this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(GetMessageActivity.this,
                    Manifest.permission.READ_SMS)) {
            } else {
                ActivityCompat.requestPermissions(GetMessageActivity.this,
                        new String[]{Manifest.permission.READ_SMS},
                        MY_PERMISSIONS_REQUEST_READ_SMS);
            }
        } else {
        }
    }
    public void getListMessage(){
        StringBuffer buffer = new StringBuffer();
        Cursor cursor = getContentResolver().query(Telephony.Sms.CONTENT_URI, null, null, null, null);

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx)+"\n";
                }
                buffer.append(msgData).append("\n---------\n\n");
            } while (cursor.moveToNext());
        } else {
            buffer.append("No SMS");
        }
        cursor.close();
        listMessage.setText(buffer.toString());
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }
}
