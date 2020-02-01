package com.mobile.qosin.Dashboard;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobile.qosin.Activity.ActivityKost;
import com.mobile.qosin.Adapter.SliderAdapter;
import com.mobile.qosin.R;
import com.mobile.qosin.SessionManager;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {
    private TextView nama,email;
    private ImageView bt_kost;
    private FloatingActionButton fab;
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
        SliderView sll = view.findViewById(R.id.slider_template);
        sll.setSliderAdapter(new SliderAdapter(getContext()));
        sll.startAutoCycle();
        sll.setScrollTimeInSec(6);
        sll.setIndicatorAnimation(IndicatorAnimations.WORM);
        sll.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        HashMap<String , String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.USERNAME);
        String mEmail = user.get(sessionManager.NAME);
        String getname = "Hai "+mName+" !";
        String getmail = ""+mEmail;

        nama.setText(getname);
        fab = view.findViewById(R.id.fab_add);
        String mJenis = user.get(sessionManager.JENIS_AKUN);
        if (TextUtils.isEmpty(mJenis)) {
            fab.hide();
        } else {
            fab.show();
        }
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
