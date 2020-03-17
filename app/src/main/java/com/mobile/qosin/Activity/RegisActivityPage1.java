package com.mobile.qosin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class RegisActivityPage1 extends AppCompatActivity {

    String mNamalengkaps, mPasswords, mUsernames;
    private ProgressBar pr;
    private ImageView btn_regist;
    private EditText email, notelp;
    private String URL_REGIST = "https://qosin.id/api_android/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        btn_regist = findViewById(R.id.r_btn_regist);
        pr = findViewById(R.id.r_pr);
        notelp = findViewById(R.id.r_in_nope);
        email = findViewById(R.id.r_in_email);
        mNamalengkaps = getIntent().getStringExtra("nama");
        mPasswords = getIntent().getStringExtra("password");
        mUsernames = getIntent().getStringExtra("username");

        email = findViewById(R.id.r_in_email);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = mUsernames;
                String mPass = mPasswords;
                String mNama = mNamalengkaps;
                String mEmail = email.getText().toString().trim();
                String mNotelp = notelp.getText().toString().trim();

                if (!mUsername.isEmpty() || !mPass.isEmpty() || !mEmail.isEmpty() || !mNama.isEmpty() ||  !mNotelp.isEmpty() ) {
                    Regist();
                } else {
                    Toast.makeText(getApplicationContext(), "Pendaftaran gagal silahkan check kembali", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void Regist(){
        pr.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);
        final String name = this.mNamalengkaps.trim();
        final String nick = this.mUsernames.trim();
        final String emails = this.email.getText().toString().trim();
        final String passwords = this.mPasswords.trim();
        final String notelp = this.notelp.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(RegisActivityPage1.this, "Hai Pendaftaran Berhasil! Silahkan Login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisActivityPage1.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisActivityPage1.this, "Pendaftaran Gagal! Periksa Kembali " + e.toString(), Toast.LENGTH_SHORT).show();
                            pr.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisActivityPage1.this, "Register Gagal! " + error.toString(), Toast.LENGTH_SHORT).show();
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
