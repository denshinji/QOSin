package com.mobile.qosin.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.qosin.Adapter.RecyclerViewAdapterCampus;
import com.mobile.qosin.R;

import java.util.ArrayList;

public class ListCampusActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Integer> mImage3 = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_campus);
        int numRow = 3;
        LinearLayoutManager gridManager = new GridLayoutManager(this, numRow);
        recyclerView = findViewById(R.id.rv_campus);
        recyclerView.setLayoutManager(gridManager);
        RecyclerViewAdapterCampus adapterf = new RecyclerViewAdapterCampus(getApplicationContext(), mImage3, mDesc);
        recyclerView.setAdapter(adapterf);

        initListCampus();
    }

    private void initListCampus() {
        mImage3.add(R.drawable.unand);
        mDesc.add("UNAND");
        mImage3.add(R.drawable.unp);
        mDesc.add("UNP");
        mImage3.add(R.drawable.segera);
        mDesc.add("");
    }
}
