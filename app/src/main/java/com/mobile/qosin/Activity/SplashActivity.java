package com.mobile.qosin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.mobile.qosin.R;
import com.mobile.qosin.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private LazyLoader loader;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loader = (LazyLoader)findViewById(R.id.loader);
        Thread thread = new Thread() {
            public void run() {
                try {
                    LazyLoader loaders = new LazyLoader(SplashActivity.this, 30, 20,
                            ContextCompat.getColor(SplashActivity.this, R.color.loader_selected),
                            ContextCompat.getColor(SplashActivity.this, R.color.loader_selected),
                            ContextCompat.getColor(SplashActivity.this, R.color.loader_selected));
                    loader.setAnimDuration(250);
                    loader.setFirstDelayDuration(100);
                    loader.setSecondDelayDuration(200);
                    loader.setInterpolator(new LinearInterpolator());

                    loader.addView(loaders);
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
