package com.mobile.qosin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.mobile.qosin.R;

public class RegisActivityPage2 extends AppCompatActivity {
    private TextInputLayout tp, tn, tu;
    private EditText nama, username, password;
    private ImageView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_page1);
        nama = findViewById(R.id.r_in_namalengkap);
        username = findViewById(R.id.r_in_username);
        password = findViewById(R.id.r_in_password);
        next = findViewById(R.id.r_btn_next);
        tp = findViewById(R.id.r_layout_password);
        tn = findViewById(R.id.r_layout_namalengkap);
        tu = findViewById(R.id.r_layout_username);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mNama = nama.getText().toString();
                String mUsername = username.getText().toString();
                String mPassword = password.getText().toString();
                if (mNama.isEmpty()) {
                    tn.setHelperTextEnabled(true);
                    tn.setHelperText("Nama tidak boleh kosong");
                } else if (mUsername.isEmpty()) {
                    tu.setHelperTextEnabled(true);
                    tu.setHelperText("Username tidak boleh kosong");
                } else if (mPassword.isEmpty()) {
                    tp.setHelperTextEnabled(true);
                    tp.setHelperText("Password tidak boleh kosong");

                } else {
                    Intent intent = new Intent(getApplicationContext(), RegisActivityPage1.class);
                    intent.putExtra("nama", mNama);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("password", mPassword);
                    startActivity(intent);
                }


            }
        });
    }
}
