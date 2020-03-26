package com.mobile.qosin.Activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mobile.qosin.ProfileActivity;
import com.mobile.qosin.R;
import com.mobile.qosin.Tools.SessionManager;
import com.mobile.qosin.UbahPasswordActivity;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment implements View.OnClickListener {

    private Button logout, account, changepass, about, calladmin;
    private TextView name_profile, email_profile;
    private SessionManager sessionManager;

    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sessionManager = new SessionManager(getActivity());
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        logout = view.findViewById(R.id.btn_logout);
        logout.setOnClickListener(this);
        account = view.findViewById(R.id.rv_profile);
        account.setOnClickListener(this);
        changepass = view.findViewById(R.id.rv_change_pw);
        changepass.setOnClickListener(this);
        about = view.findViewById(R.id.rv_website);
        about.setOnClickListener(this);
        calladmin = view.findViewById(R.id.rv_call);
        calladmin.setOnClickListener(this);
        name_profile = view.findViewById(R.id.name_profile);
        email_profile = view.findViewById(R.id.email_profile);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String name = user.get(SessionManager.USERNAME);
        String email = user.get(SessionManager.EMAIL);
        name_profile.setText(name);
        email_profile.setText(email);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rv_profile:
                startActivity(new Intent(getContext(), ProfileActivity.class));
                break;
            case R.id.rv_change_pw:
                startActivity(new Intent(getContext(), UbahPasswordActivity.class));
                break;
            case R.id.rv_call:
                String number = "+6281371855757";
                String message = "Hai min";
                String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + message;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.rv_website:
                String urlweb = "https://qosin.id/";
                Intent in = new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse(urlweb));
                startActivity(in);
                break;

            case R.id.btn_logout:
                sessionManager.logout();

        }

    }
}
