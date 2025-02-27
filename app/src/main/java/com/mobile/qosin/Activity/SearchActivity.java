package com.mobile.qosin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
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

public class SearchActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapterAll;
    private SearchView searchView;
    private ImageView img_empty_all;
    private List<Item> AllItem;
    private static final String BUNDLE_RECYCLER_LAYOUT = "recycler_layout";
    private static String LIST_STATE = "list_state";
    private Parcelable savedRecyclerLayoutState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (savedInstanceState != null) {
            restoreLayoutManagerPosition();
            AllItem = savedInstanceState.getParcelableArrayList(LIST_STATE);
            savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            restoreLayoutManagerPosition();
            displayRestore();
        } else {
            display();
        }


    }

    private void displayRestore() {
        recyclerView = findViewById(R.id.list_view_all);
        adapterAll = new Adapter(AllItem, getApplicationContext(), listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapterAll);
        restoreLayoutManagerPosition();
        adapterAll.notifyDataSetChanged();
    }

    private void display() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        searchView = findViewById(R.id.searchview_all);
        img_empty_all = findViewById(R.id.img_no_data_all);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterAll.getFilter().filter(query);
                if (adapterAll.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    img_empty_all.setVisibility(View.VISIBLE);
                } else if (query.isEmpty()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    img_empty_all.setVisibility(View.GONE);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterAll.getFilter().filter(newText);
                if (newText.isEmpty()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    img_empty_all.setVisibility(View.GONE);
                } else if (adapterAll.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    img_empty_all.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        recyclerView = findViewById(R.id.list_view_all);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {


                if (AllItem.get(position).getJenis().equals("Kos")) {
                    String intentkost = "KOST";
                    Intent intent = new Intent(SearchActivity.this, DetailActivityKost.class);
                    intent.putExtra(DetailActivityKost.KOST_KEY, AllItem.get(position));
                    intent.putExtra(DetailActivityKost.FAV_KEY, AllItem.get(position));
                    intent.putExtra("iFav", intentkost);
                    startActivity(intent);
                }
                if (AllItem.get(position).getJenis().equals("Kontrakan")) {
                    String intentkontrakan = "KONTRAKAN";
                    Intent intent = new Intent(SearchActivity.this, DetailActivityKontrakan.class);
                    intent.putExtra(DetailActivityKontrakan.KONTRAKAN_KEY, AllItem.get(position));
                    intent.putExtra(DetailActivityKost.FAV_KEY, AllItem.get(position));
                    intent.putExtra("iFav", intentkontrakan);
                    startActivity(intent);
                }

            }
        };
        getAll();

    }

    private void restoreLayoutManagerPosition() {
        if (savedRecyclerLayoutState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    public void getAll() {
        recyclerView.setVisibility(View.GONE);
        Call<List<Item>> call = apiInterface.get_all();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                AllItem = response.body();
                if (AllItem.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    img_empty_all.setVisibility(View.VISIBLE);
                }
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                adapterAll = new Adapter(AllItem, getApplicationContext(), listener);
                recyclerView.setAdapter(adapterAll);
                recyclerView.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapterAll.showShimmer = false;
                        adapterAll.notifyDataSetChanged();
                    }
                }, 5000);
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
        savedInstanceState.putParcelableArrayList(LIST_STATE, new ArrayList<Item>(AllItem.size()));
        savedInstanceState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        AllItem = savedInstanceState.getParcelableArrayList(LIST_STATE);
        savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
        super.onRestoreInstanceState(savedInstanceState);
    }

}
