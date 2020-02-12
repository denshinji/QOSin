package com.mobile.qosin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobile.qosin.R;

public class MenuLogin extends AppCompatActivity {

    private Button masuk,daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);
        daftar = findViewById(R.id.bt_daftar);
        masuk = findViewById(R.id.bt_masuk);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuLogin.this, RegisActivity.class));
                overridePendingTransition(R.anim.slide_right,R.anim.slide_ou_left);
            }
        });
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuLogin.this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_right,R.anim.slide_ou_left);
            }
        });
    }
}
