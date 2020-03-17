package com.mobile.qosin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.mobile.qosin.R;

public class RegisActivityPage2 extends AppCompatActivity {
    private EditText nama,username,password;
    private ImageView next;
    TextInputLayout tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_page1);
        nama = findViewById(R.id.r_in_namalengkap);
        username = findViewById(R.id.r_in_username);
        password = findViewById(R.id.r_in_password);
        next = findViewById(R.id.r_btn_next);
        tp = findViewById(R.id.r_layout_password);




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mNama = nama.getText().toString();
                String mUsername = username.getText().toString();
                String mPassword = password.getText().toString();
                if(!mNama.isEmpty()|| !mUsername.isEmpty() || !mPassword.isEmpty()) {
                Intent intent = new Intent(getApplicationContext(), RegisActivityPage1.class);
                intent.putExtra("nama",mNama);
                intent.putExtra("username",mUsername);
                intent.putExtra("password",mPassword);
                startActivity(intent); } else {
                    tp.setPasswordVisibilityToggleEnabled(false);
                    password.setTransformationMethod(new PasswordTransformationMethod());
                     nama.setError("Silahkan di isi");
                     username.setError("Silahkan di isi");
                     password.setError("Silahkan di isi");


                }
            }
        });
    }
}
