package com.mobile.qosin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.mobile.qosin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisActivity extends AppCompatActivity {

    private Button btn_regist;
    private ProgressBar pr;
    private EditText nama,email,password,nickname,notelp;



    private String URL_REGIST = "https://qosin.id/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        btn_regist = findViewById(R.id.r_btn_regist);
        nama = findViewById(R.id.r_in_name);
        nickname = findViewById(R.id.r_in_username);
        pr = findViewById(R.id.r_pr);
        notelp = findViewById(R.id.r_in_nope);


        email = findViewById(R.id.r_in_email);
        password = findViewById(R.id.r_in_password);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = nickname.getText().toString().trim();
                String mPass = password.getText().toString().trim();
                String mNama = nama.getText().toString().trim();
                String mEmail = email.getText().toString().trim();
                String mNotelp = notelp.getText().toString().trim();

                if (!mUsername.isEmpty() || !mPass.isEmpty() || !mEmail.isEmpty() || !mNama.isEmpty() ||  !mNotelp.isEmpty() ) {
                    Regist();
                } else {
                    nickname.setError("REGISTRASI ERROR PERIKSA KEMBALI");
                }


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
        final String notelp = this.notelp.getText().toString().trim();


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
                params.put("username", nick);
                params.put("name", name);
                params.put("email", emails);
                params.put("password", passwords);
                params.put("nohp", notelp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
