package com.mobile.qosin.Kost.KostPria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentKostPria extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private ProgressBar pb;
    private SearchView searchView;
    private ImageView img_empty;
    private List<Item> ItemList;
    ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    public FragmentKostPria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_kost, container, false);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        searchView = view.findViewById(R.id.searchview);
        pb = view.findViewById(R.id.pb_frag_kost);
        img_empty = view.findViewById(R.id.img_no_data_kostp);
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
        recyclerView = view.findViewById(R.id.list_view);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {

                Intent intent = new Intent(getContext(), DetailActivityKost.class);
                intent.putExtra(DetailActivityKost.KOST_KEY, ItemList.get(position));
                startActivity(intent);

            }
        };


        return view;
    }

    public void getKost(){
        pb.setVisibility(View.VISIBLE);
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
                pb.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getContext(), "Terjadi kesalahan saat memuat data, Coba periksa Koneksi Internet Anda",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getKost();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}