package com.mobile.qosin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.qosin.R;
import com.mobile.qosin.Tools.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button btnlogin;
    private ProgressBar pr;
    private EditText username,password;
    private SessionManager sessionManager;
    private static String URL_LOGIN = "https://qosin.id/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        pr = findViewById(R.id.pr);
        username = findViewById(R.id.in_nama);
        password = findViewById(R.id.in_password);
        btnlogin = findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = username.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (!mUsername.isEmpty() || !mPass.isEmpty()) {
                    Login(mUsername, mPass);
                } else {
                    username.setError("Masukan Username anda");
                    password.setError("Masukan Password anda");
                }
            }
        });



    }

    private void Login(final String username, final String password) {
        pr.setVisibility(View.VISIBLE);
        btnlogin.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);


                                    String jenis_akun = object.getString("jenis_akun").trim();
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String username = object.getString("username").trim();
                                    String notelp = object.getString("nope").trim();


                                    sessionManager.createSession(jenis_akun, name, email, username, notelp);
                                    Toast.makeText(LoginActivity.this,"Welcome "+name,Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                    startActivity(intent);
                                    finish();

                                    pr.setVisibility(View.VISIBLE);
                                    btnlogin.setVisibility(View.GONE);


                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Email atau Password " + "\n Salah"+toString(), Toast.LENGTH_SHORT).show();
                            pr.setVisibility(View.GONE);
                            btnlogin.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Email atau Password " + "\n Salah"+toString(), Toast.LENGTH_SHORT).show();
                        pr.setVisibility(View.GONE);
                        btnlogin.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
