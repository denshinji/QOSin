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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button btnlogin;
    private TextView s_regis;
    private LazyLoader pr;
    private EditText username,password;
    private SessionManager sessionManager;
    private static String URL_LOGIN = "https://qosin.id/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        pr = findViewById(R.id.l_pr);
        LazyLoader loaders = new LazyLoader(LoginActivity.this, 30, 20, ContextCompat.getColor(LoginActivity.this, R.color.loader_selected),
                ContextCompat.getColor(LoginActivity.this, R.color.loader_selected),
                ContextCompat.getColor(LoginActivity.this, R.color.loader_selected));
        pr.setAnimDuration(500);
        pr.setFirstDelayDuration(100);
        pr.setSecondDelayDuration(250);
        pr.setInterpolator(new LinearInterpolator());

        pr.addView(loaders);

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
                    username.setError("Please insert email");
                    password.setError("Please insert password");
                }
            }
        });

        s_regis = findViewById(R.id.l_bt_regist);
        s_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisActivity.class));
                overridePendingTransition(R.anim.slide_right,R.anim.slide_ou_left);
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


                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();


                                    sessionManager.createSession(name, email);
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
