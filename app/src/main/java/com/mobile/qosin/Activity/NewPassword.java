package com.mobile.qosin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewPassword extends AppCompatActivity {
    private static String URL_NEW_PASSWORD = "https://qosin.id/api_android/new_password.php";
    private TextInputLayout tNewpas;
    private EditText nPass;
    private Button btnToLogin;
    private String getEmail;
    private ProgressBar pbnp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        Intent i = getIntent();
        getEmail = i.getStringExtra("checkemail");
        pbnp = findViewById(R.id.pb_np);
        btnAction();
    }

    private void btnAction() {
        tNewpas = findViewById(R.id.r_layout_newpassword);
        btnToLogin = findViewById(R.id.btn_to_login);
        nPass = findViewById(R.id.edt_new_password);
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mPassword = nPass.getText().toString().trim();
                String mEmail = getEmail.trim();

                if (mPassword.isEmpty()) {
                    tNewpas.setHelperTextEnabled(true);
                    tNewpas.setHelperText("Email tidak boleh kosong");
                } else {
                    NewPassword(mEmail, mPassword);
                }
            }
        });


    }

    private void NewPassword(final String sEmail, final String nPassword) {
        pbnp.setVisibility(View.VISIBLE);
        btnToLogin.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_NEW_PASSWORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {

                                Intent intent = new Intent(NewPassword.this, LoginActivity.class);
                                startActivity(intent);
                                finish();

                                pbnp.setVisibility(View.VISIBLE);
                                btnToLogin.setVisibility(View.GONE);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(NewPassword.this, "Reset password gagal , silahkan check koneksi anda", Toast.LENGTH_LONG).show();
                            pbnp.setVisibility(View.GONE);
                            btnToLogin.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewPassword.this, "Reset password gagal , silahkan check koneksi anda" + toString(), Toast.LENGTH_LONG).show();
                        pbnp.setVisibility(View.GONE);
                        btnToLogin.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", sEmail);
                params.put("newpassword", nPassword);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
