package com.mobile.qosin.Kost;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.mobile.qosin.Kost.KostPria.FragmentKostPria;
import com.mobile.qosin.Kost.KostWanita.FragmentKostWanita;
import com.mobile.qosin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabKost extends Fragment {


    public TabKost() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_kost, container, false);


        TabLayout tabLayout = view.findViewById(R.id.tab_movie);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new FragmentKostPria();
                        break;
                    case 1:
                        fragment = new FragmentKostWanita();
                        break;
                }
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fram_tab_movie, fragment);
                ft.setCustomAnimations(R.anim.slide_left, R.anim.slide_ou_right);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FragmentKostPria kostPria = new FragmentKostPria();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fram_tab_movie, kostPria);
        transaction.addToBackStack(null);
        transaction.commit();


        return view;
    }



}
