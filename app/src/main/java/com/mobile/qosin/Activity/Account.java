package com.mobile.qosin.Activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.qosin.R;
import com.mobile.qosin.Tools.SessionManager;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {

    private RelativeLayout logout;
    private TextView name_profile,email_profile;
    private SessionManager sessionManager;
    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sessionManager = new SessionManager(getActivity());
        View view =inflater.inflate(R.layout.fragment_account, container, false);
        logout = view.findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager .logout();
            }
        });
        name_profile = view.findViewById(R.id.name_profile);
        email_profile = view.findViewById(R.id.email_profile);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String name = user.get(sessionManager.USERNAME);
        String email = user.get(sessionManager.EMAIL);
        name_profile.setText(name);
        email_profile.setText(email);

    return view;
    }



}
