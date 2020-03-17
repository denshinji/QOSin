package com.mobile.qosin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.qosin.R;

public class SplashMenu extends AppCompatActivity {
    private boolean doubleBackToExitPressedOnce = false;
    private Button masuk, daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);
        daftar = findViewById(R.id.bt_daftar);
        masuk = findViewById(R.id.bt_masuk);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashMenu.this, RegisActivityPage2.class));
                overridePendingTransition(R.anim.slide_right, R.anim.slide_ou_left);
            }
        });
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashMenu.this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_right, R.anim.slide_ou_left);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar dari Qosin", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
