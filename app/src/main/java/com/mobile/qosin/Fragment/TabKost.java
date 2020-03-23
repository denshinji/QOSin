package com.mobile.qosin.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mobile.qosin.Adapter.ViewPageAdapter;
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

        ViewPager vp = view.findViewById(R.id.viewpager);
        ViewPageAdapter vAdapter = new ViewPageAdapter(getContext(), getFragmentManager());
        vp.setAdapter(vAdapter);
        TabLayout tb = view.findViewById(R.id.tab_layout);
        tb.setupWithViewPager(vp);


        return view;
    }


}
