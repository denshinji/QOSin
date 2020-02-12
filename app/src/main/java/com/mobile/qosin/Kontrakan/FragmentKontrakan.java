package com.mobile.qosin.Kontrakan;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.mobile.qosin.API.ApiClient;
import com.mobile.qosin.API.ApiInterface;
import com.mobile.qosin.Activity.DetailActivity;
import com.mobile.qosin.Activity.MainActivity;
import com.mobile.qosin.Adapter.Adapter;
import com.mobile.qosin.Model.Kost;
import com.mobile.qosin.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKontrakan extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private ProgressBar pb;
    private SearchView searchView;
    private List<Kost> KostList;
    ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    public FragmentKontrakan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_kontrakan, container, false);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        searchView = view.findViewById(R.id.searchview_kontrakan);
        pb = view.findViewById(R.id.pb_frag_kontrakan);
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
        recyclerView = view.findViewById(R.id.list_view_kontrakan);

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

    public void getKontrakan(){
        pb.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        Call<List<Kost>> call = apiInterface.getPets();
        call.enqueue(new Callback<List<Kost>>() {
            @Override
            public void onResponse(Call<List<Kost>> call, Response<List<Kost>> response) {
                KostList = response.body();
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                adapter = new Adapter(KostList, getContext(), listener);
                recyclerView.setAdapter(adapter);
                pb.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Kost>> call, Throwable t) {
                Toast.makeText(getContext(), "Terjadi kesalahan saat memuat data, Coba periksa Koneksi Internet Anda",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        getKontrakan();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}