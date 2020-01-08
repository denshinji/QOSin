package com.mobile.qosin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisActivity extends AppCompatActivity {

    private Button btn_regist;
    private EditText nama,email,password,nickname;
    private TextView s_login;
    private LazyLoader pr;
    private String URL_REGIST = "https://qosin.id/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        btn_regist = findViewById(R.id.r_btn_regist);
        nama = findViewById(R.id.r_in_nama);
        nickname = findViewById(R.id.r_in_nickname);
        pr = findViewById(R.id.r_pr);
        LazyLoader loaders = new LazyLoader(RegisActivity.this, 30, 20, ContextCompat.getColor(RegisActivity.this, R.color.loader_selected),
                ContextCompat.getColor(RegisActivity.this, R.color.loader_selected),
                ContextCompat.getColor(RegisActivity.this, R.color.loader_selected));
        pr.setAnimDuration(500);
        pr.setFirstDelayDuration(10);
        pr.setSecondDelayDuration(25);
        pr.setInterpolator(new LinearInterpolator());

        pr.addView(loaders);
        email = findViewById(R.id.r_in_email);
        password = findViewById(R.id.r_in_password);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = nickname.getText().toString().trim();
                String mPass = password.getText().toString().trim();
                String mNama = nama.getText().toString().trim();
                String mEmail = email.getText().toString().trim();

                if (!mUsername.isEmpty() || !mPass.isEmpty() || !mEmail.isEmpty() || !mNama.isEmpty() ) {
                    Regist();
                } else {
                    nickname.setError("Please insert Nickname");
                    password.setError("Please insert Password");
                    nama.setError("Please insert Name");
                    email.setError("Please insert Email");
                }


            }
        });
        s_login = findViewById(R.id.r_bt_login);
        s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_left,R.anim.slide_ou_right);
            }
        });
    }

    private void Regist(){
        pr.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);
        final String name = this.nama.getText().toString().trim();
        final String nick = this.nickname.getText().toString().trim();
        final String emails = this.email.getText().toString().trim();
        final String passwords = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(RegisActivity.this, "Hai Pendaftaran Berhasil! Silahkan Login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisActivity.this, "Pendaftaran Gagal! Periksa Kembali " + e.toString(), Toast.LENGTH_SHORT).show();
                            pr.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisActivity.this, "Register Gagal! " + error.toString(), Toast.LENGTH_SHORT).show();
                        pr.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", nick);
                params.put("nickname", name);
                params.put("email", emails);
                params.put("password", passwords);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
