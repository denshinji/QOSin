package com.mobile.qosin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.mobile.qosin.API.ApiClient;
import com.mobile.qosin.API.ApiInterface;
import com.mobile.qosin.Adapter.Adapter;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.R;

import java.sql.ClientInfoStatus;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCampus extends AppCompatActivity {
    private RecyclerView recyclerView_campus;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapterKampus;
    private ProgressBar pb_campus;
    private ImageView img_empty_kampus;
    private List<Item> KampusList;
    String campus;
    Adapter.RecyclerViewClickListener listener;
    TextView nama_kampus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);
        pb_campus = findViewById(R.id.pb_campus);
        img_empty_kampus = findViewById(R.id.img_no_data_campus);
        recyclerView_campus = findViewById(R.id.list_view_campus);
        Intent i = getIntent();
        String campuss = i.getStringExtra("campus");
        campus = campuss;
        nama_kampus = findViewById(R.id.judul_campus);
        nama_kampus.setText("Sekitar "+campus);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_campus.setLayoutManager(layoutManager);
        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {

                    if(KampusList.get(position).getJenis().equals("kost")) {
                        Intent intent = new Intent(ActivityCampus.this, DetailActivityKost.class);
                        intent.putExtra(DetailActivityKost.KOST_KEY, KampusList.get(position));
                        startActivity(intent);
                    }
                    if(KampusList.get(position).getJenis().equals("kontrakan")) {
                        Intent intent = new Intent(ActivityCampus.this, DetailActivityKontrakan.class);
                        intent.putExtra(DetailActivityKontrakan.KONTRAKAN_KEY, KampusList.get(position));
                        startActivity(intent);
                    }
            }
        };

    }

    public void getItemCampus(){
        pb_campus.setVisibility(View.VISIBLE);
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
                    pb_campus.setVisibility(View.GONE);
                    recyclerView_campus.setVisibility(View.GONE);
                    img_empty_kampus.setVisibility(View.VISIBLE);
                    Toast.makeText(ActivityCampus.this, "RV Kosong", Toast.LENGTH_LONG).show();
                } else {
                    Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                    adapterKampus = new Adapter(KampusList, getApplicationContext(), listener);
                    recyclerView_campus.setAdapter(adapterKampus);
                    pb_campus.setVisibility(View.GONE);
                    recyclerView_campus.setVisibility(View.VISIBLE);
                    adapterKampus.notifyDataSetChanged();
                    img_empty_kampus.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan saat memuat data, Coba periksa Koneksi Internet Anda",
                        Toast.LENGTH_SHORT).show();
                pb_campus.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        getItemCampus();

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
