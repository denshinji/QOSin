package com.mobile.qosin.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

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

public class UbahPasswordActivity extends AppCompatActivity {
    private static String URL_CHANGEPASS = "https://qosin.id/api_android/change_password.php";
    private EditText edtNewpass, edtOldpass;
    private ProgressBar pb;
    private Button btn_done;
    private String id;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);
        btn_done = findViewById(R.id.btn_to_change_pass);
        initGetSessionManager();
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initChangePassword(v);
            }
        });
    }

    private void initGetSessionManager() {
        edtNewpass = findViewById(R.id.edt_newpass);
        edtOldpass = findViewById(R.id.edt_oldpass);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID);

    }

    private void initChangePassword(final View v) {
        pb = findViewById(R.id.pb_up);
        pb.setVisibility(View.VISIBLE);
        btn_done.setVisibility(View.GONE);
        final String ids = id;
        final String newpassword = edtNewpass.getText().toString().trim();
        final String oldpassword = edtOldpass.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CHANGEPASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Snackbar.make(v, "Password berhasil di update", Snackbar.LENGTH_SHORT).show();
                                pb.setVisibility(View.GONE);
                                btn_done.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Snackbar.make(v, "Password gagal di update, periksa koneksi anda", Snackbar.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                            btn_done.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(v, "Data gagal di update, periksa koneksi anda", Snackbar.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                        btn_done.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", ids);
                params.put("oldpass", oldpassword);
                params.put("newpass", newpassword);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}

