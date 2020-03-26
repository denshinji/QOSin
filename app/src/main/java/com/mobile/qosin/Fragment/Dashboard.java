package com.mobile.qosin.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.qosin.Activity.ListCampusActivity;
import com.mobile.qosin.Adapter.RecyclerViewAdapterButton;
import com.mobile.qosin.R;
import com.mobile.qosin.Tools.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {
    private TextView nama, email;
    private SessionManager sessionManager;
    private ArrayList<Integer> mImage4 = new ArrayList<>();
    private ArrayList<String> mDesc2 = new ArrayList<>();
    private RecyclerView recyclerViewbutton;
    private ImageView bannerCampus, bannerAddkost;
    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        sessionManager = new SessionManager(getActivity());
        nama = view.findViewById(R.id.get_nama);
        email = view.findViewById(R.id.get_email);
        recyclerViewbutton = view.findViewById(R.id.rv_button_menu);
        bannerCampus = view.findViewById(R.id.bannercampus);
        bannerAddkost = view.findViewById(R.id.banneraddkost);

        initBannerCampus();
        initrvcampusandbutton();
        iniRelativePromo();
        initGetName();
        initAddKost();
        return view;
    }

    private void initAddKost() {
        bannerAddkost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+6281371855757";
                String message = "Hai min saya ingin mendaftarkan usaha tempat tinggal saya ";
                String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + message;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private void initBannerCampus() {
        bannerCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ListCampusActivity.class));
            }
        });
    }

    private void initGetName() {
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(SessionManager.USERNAME);
        String getname = "Hai " + mName;
        String getmail = "Mau cari apa hari ini ?";
        nama.setText(getname);
        email.setText(getmail);
    }

    private void iniRelativePromo() {
        LinearLayoutManager layoutManagerbutton = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewbutton.setLayoutManager(layoutManagerbutton);
        RecyclerViewAdapterButton adapterbutton = new RecyclerViewAdapterButton(getActivity(), mImage4, mDesc2);
        recyclerViewbutton.setAdapter(adapterbutton);
    }


    private void initrvcampusandbutton() {

        mImage4.add(R.mipmap.icon_kost2);
        mDesc2.add("KOST");
        mImage4.add(R.mipmap.icon_kontrakan2);
        mDesc2.add("KONTRAKAN");
        mImage4.add(R.mipmap.icon_event2);
        mDesc2.add("EVENT");
    }


}
