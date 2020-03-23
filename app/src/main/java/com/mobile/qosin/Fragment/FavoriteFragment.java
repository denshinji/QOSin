package com.mobile.qosin.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.qosin.Adapter.AdapterFav;
import com.mobile.qosin.Db.FavoriteDBHelper;
import com.mobile.qosin.Model.Favorite;
import com.mobile.qosin.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressBar pb;
    private List<Favorite> favorites = new ArrayList<>();
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
        pb = view.findViewById(R.id.pb_frag_fav);
        img_empty = view.findViewById(R.id.img_no_data_fav);
        initRecylerview();
        initCheckItem();

        return view;
    }

    private void initCheckItem() {
        if (favorites != null) {
            pb.setVisibility(View.GONE);
            img_empty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            pb.setVisibility(View.GONE);
            img_empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void initRecylerview() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favoriteDBhelper = new FavoriteDBHelper(getActivity());
        favoriteDBhelper.open();
        favorites = favoriteDBhelper.getAllFavorite();
        adapter = new AdapterFav(favorites, getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
