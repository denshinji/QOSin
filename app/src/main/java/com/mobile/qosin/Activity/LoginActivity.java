package com.mobile.qosin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.android.material.textfield.TextInputLayout;
import com.mobile.qosin.R;
import com.mobile.qosin.Tools.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static String URL_LOGIN = "https://qosin.id/api_android/login.php";
    private ProgressBar pr;
    private EditText username, password;
    private SessionManager sessionManager;
    private ImageView btnlogin;
    private TextInputLayout tus, tpas;
    private Button lPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        pr = findViewById(R.id.pr);
        tus = findViewById(R.id.layout_nama);
        tpas = findViewById(R.id.layout_password);
        username = findViewById(R.id.in_nama);
        password = findViewById(R.id.in_password);
        btnlogin = findViewById(R.id.btn_login);
        loginAction();
        lupaPassword();


    }

    private void lupaPassword() {
        lPassword = findViewById(R.id.btn_lupapassword);
        lPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, LupaPasswordActivity.class);
                startActivity(i);
            }
        });

    }

    private void loginAction() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = username.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (mUsername.isEmpty()) {
                    tus.setHelperTextEnabled(true);
                    tus.setHelperText("Username tidak boleh kosong");
                } else if (mPass.isEmpty()) {
                    tpas.setHelperTextEnabled(true);
                    tpas.setHelperText("Password tidak boleh kosong");
                } else {
                    Login(mUsername, mPass);
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


                                    String id = object.getString("id").trim();
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String username = object.getString("username").trim();
                                    String notelp = object.getString("nope").trim();


                                    sessionManager.createSession(name, email, username, notelp, id);
                                    Toast.makeText(LoginActivity.this, "Welcome " + name, Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                    startActivity(intent);
                                    finish();

                                    pr.setVisibility(View.VISIBLE);
                                    btnlogin.setVisibility(View.GONE);


                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_LONG).show();
                            pr.setVisibility(View.GONE);
                            btnlogin.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Email atau Password " + "\n Salah" + toString(), Toast.LENGTH_LONG).show();
                        pr.setVisibility(View.GONE);
                        btnlogin.setVisibility(View.VISIBLE);
                    }
                }) {
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
