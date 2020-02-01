package com.mobile.qosin.KostPria;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.qosin.API.ApiClient;
import com.mobile.qosin.API.ApiInterface;
import com.mobile.qosin.Adapter.Adapter;
import com.mobile.qosin.DetailActivity;
import com.mobile.qosin.Activity.MainActivity;
import com.mobile.qosin.Model.Kost;
import com.mobile.qosin.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentKostPria extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private SearchView searchView;
    private List<Kost> KostList;
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
             adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        recyclerView = view.findViewById(R.id.list_view);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("id", KostList.get(position).getId());
                intent.putExtra("nama", KostList.get(position).getNama());
                startActivity(intent);

            }
        };

        return view;
    }

    public void getKost(){

        Call<List<Kost>> call = apiInterface.getPets();
        call.enqueue(new Callback<List<Kost>>() {
            @Override
            public void onResponse(Call<List<Kost>> call, Response<List<Kost>> response) {
                KostList = response.body();
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                adapter = new Adapter(KostList, getContext(), listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Kost>> call, Throwable t) {
                Toast.makeText(getContext(), "rp :"+
                                t.getMessage().toString(),
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