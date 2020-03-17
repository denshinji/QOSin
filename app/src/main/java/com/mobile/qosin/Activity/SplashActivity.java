package com.mobile.qosin.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.mobile.qosin.R;
import com.mobile.qosin.Tools.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private LazyLoader loader;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}
