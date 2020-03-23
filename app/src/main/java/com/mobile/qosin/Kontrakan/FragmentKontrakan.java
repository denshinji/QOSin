package com.mobile.qosin.Kontrakan;


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
import com.mobile.qosin.Activity.DetailActivityKontrakan;
import com.mobile.qosin.Activity.MainActivity;
import com.mobile.qosin.Adapter.Adapter;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKontrakan extends Fragment {

    ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapterKontrakan;
    private ProgressBar pb;
    private SearchView searchView;
    private ImageView img_empty_kontrakan;
    private List<Item> KontrakanList;

    public FragmentKontrakan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kontrakan, container, false);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        searchView = view.findViewById(R.id.searchview_kontrakan);
        pb = view.findViewById(R.id.pb_frag_kontrakan);
        img_empty_kontrakan = view.findViewById(R.id.img_no_data_kontrakan);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterKontrakan.getFilter().filter(query);
                if (adapterKontrakan.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    img_empty_kontrakan.setVisibility(View.VISIBLE);
                } else if (query.isEmpty()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    img_empty_kontrakan.setVisibility(View.GONE);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterKontrakan.getFilter().filter(newText);
                if (newText.isEmpty()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    img_empty_kontrakan.setVisibility(View.GONE);
                } else if (adapterKontrakan.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    img_empty_kontrakan.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        recyclerView = view.findViewById(R.id.list_view_kontrakan);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {
                String intentkontrakan = "KONTRAKAN";
                Intent intent = new Intent(getContext(), DetailActivityKontrakan.class);
                intent.putExtra(DetailActivityKontrakan.KONTRAKAN_KEY, KontrakanList.get(position));
                intent.putExtra("iFav", intentkontrakan);
                startActivity(intent);

            }
        };

        return view;
    }

    public void getKontrakan() {
        pb.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        Call<List<Item>> call = apiInterface.get_kontrakan();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                KontrakanList = response.body();
                if (KontrakanList.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    img_empty_kontrakan.setVisibility(View.VISIBLE);
                }
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                adapterKontrakan = new Adapter(KontrakanList, getContext(), listener);
                recyclerView.setAdapter(adapterKontrakan);
                pb.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapterKontrakan.notifyDataSetChanged();
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

        getKontrakan();

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}