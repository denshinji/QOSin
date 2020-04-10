package com.mobile.qosin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.qosin.API.ApiClient;
import com.mobile.qosin.API.ApiInterface;
import com.mobile.qosin.Activity.DetailActivityKost;
import com.mobile.qosin.Activity.MainActivity;
import com.mobile.qosin.Adapter.Adapter;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentKostPria extends Fragment {

    ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private SearchView searchView;
    private ImageView img_empty;
    private List<Item> ItemList;
    private static final String BUNDLE_RECYCLER_LAYOUT = "recycler_layout";
    private static String LIST_STATE = "list_state";
    private Parcelable savedRecyclerLayoutState;

    public FragmentKostPria() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kost, container, false);
        if (savedInstanceState != null) {
            restoreLayoutManagerPosition();
            ItemList = savedInstanceState.getParcelableArrayList(LIST_STATE);
            savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            restoreLayoutManagerPosition();
            displayRestore(view);
        } else {
            display(view);
        }

        return view;
    }

    private void display(View view) {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        img_empty = view.findViewById(R.id.img_no_data_kostp);
        recyclerView = view.findViewById(R.id.list_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {
                String intentkost = "KOST";
                Intent intent = new Intent(getContext(), DetailActivityKost.class);
                intent.putExtra(DetailActivityKost.KOST_KEY, ItemList.get(position));
                intent.putExtra("iFav", intentkost);
                startActivity(intent);

            }
        };
        initSearch(view);
        getKost();
    }

    private void displayRestore(View view) {
        recyclerView = view.findViewById(R.id.list_view);
        adapter = new Adapter(ItemList, getContext(), listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        restoreLayoutManagerPosition();
        adapter.notifyDataSetChanged();
    }

    private void restoreLayoutManagerPosition() {
        if (savedRecyclerLayoutState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    private void initSearch(View view) {
        searchView = view.findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                if (adapter.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    img_empty.setVisibility(View.VISIBLE);
                } else if (query.isEmpty()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    img_empty.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                if (newText.isEmpty()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    img_empty.setVisibility(View.GONE);
                } else if (adapter.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    img_empty.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
    }

    public void getKost() {
        recyclerView.setVisibility(View.GONE);


        Call<List<Item>> call = apiInterface.getPets();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                ItemList = response.body();
                if (ItemList.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    img_empty.setVisibility(View.VISIBLE);
                }
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                adapter = new Adapter(ItemList, getContext(), listener);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.showShimmer = false;
                        adapter.notifyDataSetChanged();
                    }
                }, 5000);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getContext(), "Terjadi kesalahan saat memuat data, Coba periksa Koneksi Internet Anda",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putParcelableArrayList(LIST_STATE, new ArrayList<Item>(ItemList.size()));
        outstate.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
    }


}