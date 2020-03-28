package com.mobile.qosin.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.mobile.qosin.R;
import com.mobile.qosin.Tools.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private static String URL_UPDATE = "https://qosin.id/api_android/update_account.php";
    private Button btnEdit, btnSimpan;
    private EditText edtName, edtEmail, edtUsername, edtNope;
    private SessionManager sessionManager;
    private String name, username, email, nope, id;
    private ConstraintLayout layout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnEdit = findViewById(R.id.btn_edit);
        progressBar = findViewById(R.id.pr_pb_update);
        getDataUser();
        initEdit();
        initSave();

    }

    private void initEdit() {
        layout = findViewById(R.id.cons_profile);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setEnabled(true);
                edtEmail.setEnabled(true);
                edtUsername.setEnabled(true);
                edtNope.setEnabled(true);
                btnEdit.setVisibility(View.GONE);
                btnSimpan.setVisibility(View.VISIBLE);
                layout.setFocusable(false);
                layout.setFocusableInTouchMode(false);
                edtName.setFocusable(true);
                edtName.setFocusableInTouchMode(true);
            }
        });
    }

    private void getDataUser() {
        edtName = findViewById(R.id.pr_edt_namalengkap);
        edtEmail = findViewById(R.id.pr_edt_email);
        edtUsername = findViewById(R.id.pr_edt_username);
        edtNope = findViewById(R.id.pr_edt_nope);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        name = user.get(SessionManager.NAME);
        email = user.get(SessionManager.EMAIL);
        username = user.get(SessionManager.USERNAME);
        nope = user.get(SessionManager.NOTELP);
        id = user.get(SessionManager.ID);
        edtName.setText(name);
        edtEmail.setText(email);
        edtUsername.setText(username);
        edtNope.setText(nope);
    }

    private void initSave() {
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update(v);
            }
        });

    }

    private void Update(final View view) {
        progressBar.setVisibility(View.VISIBLE);
        btnSimpan.setVisibility(View.GONE);
        final String names = edtName.getText().toString().trim();
        final String emails = edtEmail.getText().toString().trim();
        final String usernames = edtUsername.getText().toString().trim();
        final String nopes = edtNope.getText().toString().trim();
        final String ids = id.trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Snackbar.make(view, "Data berhasil di update", Snackbar.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                btnSimpan.setVisibility(View.GONE);
                                btnEdit.setVisibility(View.VISIBLE);
                                edtName.setEnabled(false);
                                edtEmail.setEnabled(false);
                                edtUsername.setEnabled(false);
                                edtNope.setEnabled(false);
                                layout.setFocusable(true);
                                layout.setFocusableInTouchMode(true);
                                edtName.setFocusable(false);
                                edtName.setFocusableInTouchMode(false);
                                sessionManager.createSession(names, emails, usernames, nopes, id);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Snackbar.make(view, "Data gagal di update, periksa koneksi anda", Snackbar.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            btnSimpan.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(view, "Data gagal di update, periksa koneksi anda", Snackbar.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        btnSimpan.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", ids);
                params.put("name", names);
                params.put("username", usernames);
                params.put("email", emails);
                params.put("nohp", nopes);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
