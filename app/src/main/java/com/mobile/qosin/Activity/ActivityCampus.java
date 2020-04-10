package com.mobile.qosin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.qosin.API.ApiClient;
import com.mobile.qosin.API.ApiInterface;
import com.mobile.qosin.Adapter.Adapter;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCampus extends AppCompatActivity {
    String campus;
    Adapter.RecyclerViewClickListener listener;
    TextView nama_kampus;
    private RecyclerView recyclerView_campus;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapterKampus;
    private ImageView img_empty_kampus;
    private List<Item> KampusList;
    private static final String BUNDLE_RECYCLER_LAYOUT = "recycler_layout";
    private static String LIST_STATE = "list_state";
    private Parcelable savedRecyclerLayoutState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);
        if (savedInstanceState != null) {
            restoreLayoutManagerPosition();
            KampusList = savedInstanceState.getParcelableArrayList(LIST_STATE);
            savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            restoreLayoutManagerPosition();
            displayRestore();
        } else {
            display();
        }


    }

    private void displayRestore() {
        recyclerView_campus = findViewById(R.id.list_view_all);
        adapterKampus = new Adapter(KampusList, getApplicationContext(), listener);
        recyclerView_campus.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView_campus.setAdapter(adapterKampus);
        restoreLayoutManagerPosition();
        adapterKampus.notifyDataSetChanged();
    }

    private void restoreLayoutManagerPosition() {
        if (savedRecyclerLayoutState != null) {
            recyclerView_campus.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    private void display() {
        img_empty_kampus = findViewById(R.id.img_no_data_campus);
        recyclerView_campus = findViewById(R.id.list_view_campus);
        Intent i = getIntent();
        String campuss = i.getStringExtra("campus");
        campus = campuss;
        nama_kampus = findViewById(R.id.judul_campus);
        nama_kampus.setText("Sekitar " + campus);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_campus.setLayoutManager(layoutManager);
        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {


                if (KampusList.get(position).getJenis().equals("Kos")) {
                    String intentkost = "KOST";
                    Intent intent = new Intent(ActivityCampus.this, DetailActivityKost.class);
                    intent.putExtra(DetailActivityKost.KOST_KEY, KampusList.get(position));
                    intent.putExtra(DetailActivityKost.FAV_KEY, KampusList.get(position));
                    intent.putExtra("iFav", intentkost);
                    startActivity(intent);
                }
                if (KampusList.get(position).getJenis().equals("Kontrakan")) {
                    String intentkontrakan = "KONTRAKAN";
                    Intent intent = new Intent(ActivityCampus.this, DetailActivityKontrakan.class);
                    intent.putExtra(DetailActivityKontrakan.KONTRAKAN_KEY, KampusList.get(position));
                    intent.putExtra(DetailActivityKost.FAV_KEY, KampusList.get(position));
                    intent.putExtra("iFav", intentkontrakan);
                    startActivity(intent);
                }
            }
        };
        getItemCampus();
    }

    public void getItemCampus() {
        recyclerView_campus.setVisibility(View.GONE);
        Intent i = getIntent();
        String campus = i.getStringExtra("campus");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Item>> call = apiInterface.get_itemcampus(campus);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                KampusList = response.body();
                if (KampusList.isEmpty()) {
                    recyclerView_campus.setVisibility(View.GONE);
                    img_empty_kampus.setVisibility(View.VISIBLE);
                } else {
                    Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                    adapterKampus = new Adapter(KampusList, getApplicationContext(), listener);
                    recyclerView_campus.setAdapter(adapterKampus);
                    recyclerView_campus.setVisibility(View.VISIBLE);
                    img_empty_kampus.setVisibility(View.GONE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapterKampus.showShimmer = false;
                            adapterKampus.notifyDataSetChanged();
                        }
                    }, 5000);

                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan saat memuat data, Coba periksa Koneksi Internet Anda",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(LIST_STATE, new ArrayList<Item>(KampusList.size()));
        savedInstanceState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView_campus.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        KampusList = savedInstanceState.getParcelableArrayList(LIST_STATE);
        savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
        super.onRestoreInstanceState(savedInstanceState);
    }
}


