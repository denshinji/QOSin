package com.mobile.qosin.Dashboard;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.qosin.Adapter.RecyclerViewAdapterButton;
import com.mobile.qosin.Adapter.RecyclerViewAdapterCampus;
import com.mobile.qosin.Adapter.SliderAbout;
import com.mobile.qosin.Adapter.SliderPromo;
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
    private SessionManager sessionManager;
    private ArrayList<Integer> mImage3 = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();
    private ArrayList<Integer> mImage4 = new ArrayList<>();
    private ArrayList<String> mDesc2 = new ArrayList<>();
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
        sll.setSliderAdapter(new SliderAbout(getContext()));
        sll.startAutoCycle();
        sll.setScrollTimeInSec(6);
        sll.setIndicatorAnimation(IndicatorAnimations.WORM);
        sll.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

        SliderView slp = view.findViewById(R.id.slider_template_promo);
        slp.setSliderAdapter(new SliderPromo(getContext()));
        slp.setIndicatorAnimation(IndicatorAnimations.WORM);
        slp.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        HashMap<String , String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.USERNAME);
        String getname = "Hai "+mName;
        String getmail = "Mau cari apa hari ini ?";
        nama.setText(getname);
        email.setText(getmail);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_cv2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterCampus adapterf = new RecyclerViewAdapterCampus(getActivity(), mImage3,mDesc);
        recyclerView.setAdapter(adapterf);


        LinearLayoutManager layoutManagerbutton = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewbutton = view.findViewById(R.id.rv_button_menu);
        recyclerViewbutton.setLayoutManager(layoutManagerbutton);
        RecyclerViewAdapterButton adapterbutton = new RecyclerViewAdapterButton(getActivity(), mImage4,mDesc2);
        recyclerViewbutton.setAdapter(adapterbutton);
       initrvcampusandbutton();

    return view;
    }

    private void initrvcampusandbutton() {
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

        mImage4.add(R.mipmap.icon_kost2);
        mDesc2.add("KOST");
        mImage4.add(R.mipmap.icon_kontrakan2);
        mDesc2.add("KONTRAKAN");
        mImage4.add(R.mipmap.icon_event2);
        mDesc2.add("EVENT");
    }



}
