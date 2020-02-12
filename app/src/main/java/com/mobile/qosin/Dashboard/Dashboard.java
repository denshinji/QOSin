package com.mobile.qosin.Dashboard;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.qosin.Activity.ActivityKontrakan;
import com.mobile.qosin.Activity.ActivityKost;
import com.mobile.qosin.Adapter.RecyclerViewAdapterCampus;
import com.mobile.qosin.Adapter.SliderAdapter;
import com.mobile.qosin.Adapter.SliderAdapter2;
import com.mobile.qosin.R;
import com.mobile.qosin.Tools.SessionManager;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {
    private TextView nama,email;
    private ImageButton bt_kost;
    private ImageView bt_event,bt_kontrakan;
    private SessionManager sessionManager;
    private ArrayList<Integer> mImage3 = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();
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

        SliderView slp = view.findViewById(R.id.slider_template_promo);
        slp.setSliderAdapter(new SliderAdapter2(getContext()));
        slp.setIndicatorAnimation(IndicatorAnimations.WORM);
        slp.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        HashMap<String , String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.USERNAME);
        String mEmail = user.get(sessionManager.NAME);
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
        bt_event = view.findViewById(R.id.bt_kost_event);
        bt_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Maaf saat ini fitur ini belum tersedia",Toast.LENGTH_SHORT).show();
            }
        });
        bt_kontrakan = view.findViewById(R.id.bt_kost_kontrakan);
        bt_kontrakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityKontrakan.class);
                startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_cv2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterCampus adapterf = new RecyclerViewAdapterCampus(getActivity(), mImage3,mDesc);
        recyclerView.setAdapter(adapterf);
       initrvcampus();


    return view;
    }

    private void initrvcampus() {
        mImage3.add(R.drawable.unand);
        mDesc.add("UNAND");
        mImage3.add(R.drawable.poli);
        mDesc.add("PNP");
        mImage3.add(R.drawable.unp);
        mDesc.add("UNP");
        mImage3.add(R.drawable.eka);
        mDesc.add("UNES");
        mImage3.add(R.drawable.bunghatta);
        mDesc.add("BUNG HATTA");
    }


}
