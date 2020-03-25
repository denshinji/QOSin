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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LupaPasswordActivity extends AppCompatActivity {
    private static String URL_CHECK_EMAIL = "https://qosin.id/api_android/check_email.php";
    private TextInputLayout tEmail, tPassword;
    private EditText email;
    private Button btnTopassword;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);
        btnTopassword = findViewById(R.id.btn_to_newpassword);
        pb = findViewById(R.id.pb_lp);
        email = findViewById(R.id.edt_check_email);
        tEmail = findViewById(R.id.r_layout_checkemail);
        checkAction();
    }

    private void checkAction() {
        btnTopassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();

                if (mEmail.isEmpty()) {
                    tEmail.setHelperTextEnabled(true);
                    tEmail.setHelperText("Email tidak boleh kosong");
                } else {
                    CheckEmail(mEmail);
                }
            }
        });
    }

    private void CheckEmail(final String sEmail) {

        pb.setVisibility(View.VISIBLE);
        btnTopassword.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CHECK_EMAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("checkemail");

                            if (success.equals("1")) {

                                Intent intent = new Intent(LupaPasswordActivity.this, NewPassword.class);
                                intent.putExtra("checkemail", email.getText().toString().trim());
                                startActivity(intent);
                                finish();

                                pb.setVisibility(View.VISIBLE);
                                btnTopassword.setVisibility(View.GONE);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LupaPasswordActivity.this, "Email tidak terdaftar", Toast.LENGTH_LONG).show();
                            pb.setVisibility(View.GONE);
                            btnTopassword.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LupaPasswordActivity.this, "Email tidak terdaftar" + toString(), Toast.LENGTH_LONG).show();
                        pb.setVisibility(View.GONE);
                        btnTopassword.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", sEmail);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
