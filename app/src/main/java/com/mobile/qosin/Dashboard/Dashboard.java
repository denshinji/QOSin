package com.mobile.qosin.Dashboard;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.qosin.Activity.ActivityKost;
import com.mobile.qosin.R;
import com.mobile.qosin.SessionManager;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {
    private TextView nama,email;
    private ImageView bt_kost;
    private SessionManager sessionManager;

    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_dashboard, container, false);
        sessionManager = new SessionManager(getActivity());
        nama = view.findViewById(R.id.get_nama);
        email = view.findViewById(R.id.get_email);
        HashMap<String , String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);
        String getname = "Hai "+mName+" !";
        String getmail = ""+mEmail;
        nama.setText(getname);
        email.setText(getmail);
        bt_kost=view.findViewById(R.id.bt_kost);
        bt_kost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityKost.class);
                startActivity(intent);
            }
        });

    return view;
    }

}
