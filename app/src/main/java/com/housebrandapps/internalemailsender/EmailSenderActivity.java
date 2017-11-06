package com.housebrandapps.internalemailsender;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.housebrandapps.simplemaillibrary.MailListener;
import com.housebrandapps.simplemaillibrary.MailTask;

public class EmailSenderActivity extends AppCompatActivity implements MailListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sender);
        sendEmail();
    }

    private void sendMail() {
        new EmailSenderViewModel(new MailTask(this));
    }

    @Override
    public void startMail() {
        Toast.makeText(this, "Sending Email", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEmailSuccess() {
        Toast.makeText(this, "Email Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEmailFail(Exception e) {
        e.printStackTrace();
        Toast.makeText(this, "Email Failed", Toast.LENGTH_LONG).show();
    }

    private void sendEmail() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            sendMail();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendMail();
                } else {
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
