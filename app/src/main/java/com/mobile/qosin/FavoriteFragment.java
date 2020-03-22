package com.mobile.qosin;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.qosin.Activity.DetailActivityKontrakan;
import com.mobile.qosin.Activity.DetailActivityKost;
import com.mobile.qosin.Adapter.AdapterFav;
import com.mobile.qosin.Db.FavoriteDBHelper;
import com.mobile.qosin.Model.Favorite;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressBar pb;
    private List<Favorite> favorites;
    private AdapterFav.RecyclerViewClickListener listener;
    private FavoriteDBHelper favoriteDBhelper;
    private ImageView img_empty;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.list_view_fav);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favorites = new ArrayList<>();
        favoriteDBhelper = new FavoriteDBHelper(getActivity());
        favoriteDBhelper.getReadableDatabase();
        favorites = favoriteDBhelper.getAllFavorite();
        adapter = new AdapterFav(favorites, getActivity(), listener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        pb = view.findViewById(R.id.pb_frag_fav);
        img_empty = view.findViewById(R.id.img_no_data_fav);

        listener = new AdapterFav.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {

                if (favorites.get(position).getJenis().equals("kost")) {
                    Intent intent = new Intent(getContext(), DetailActivityKost.class);
                    intent.putExtra(DetailActivityKost.KOST_KEY, favorites.get(position));
                    startActivity(intent);
                }
                if (favorites.get(position).getJenis().equals("kontrakan")) {
                    Intent intent = new Intent(getContext(), DetailActivityKontrakan.class);
                    intent.putExtra(DetailActivityKontrakan.KONTRAKAN_KEY, favorites.get(position));
                    startActivity(intent);
                }

            }
        };


        if (favorites.isEmpty()) {
            img_empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            pb.setVisibility(View.GONE);
        }

        return view;
    }




}
