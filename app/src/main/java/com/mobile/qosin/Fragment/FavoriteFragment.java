package com.mobile.qosin.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.qosin.Adapter.AdapterFav;
import com.mobile.qosin.Db.FavoriteDBHelper;
import com.mobile.qosin.Model.Favorite;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private static final String BUNDLE_RECYCLER_LAYOUT = "recycler_layout";
    private List<Favorite> favorites = new ArrayList<>();
    private FavoriteDBHelper favoriteDBhelper;
    private ImageView img_empty;
    private static String LIST_STATE = "list_state";
    private AdapterFav adapters;
    private Parcelable savedRecyclerLayoutState;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        if (savedInstanceState != null) {
            restoreLayoutManagerPosition();
            favorites = savedInstanceState.getParcelableArrayList(LIST_STATE);
            savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            restoreLayoutManagerPosition();
            displayRestore(view);
        } else {
            display(view);
        }

        return view;
    }

    private void restoreLayoutManagerPosition() {
        if (savedRecyclerLayoutState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    private void displayRestore(View view) {
        recyclerView = view.findViewById(R.id.list_view);
        adapters = new AdapterFav(favorites, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapters);
        restoreLayoutManagerPosition();
        adapters.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putParcelableArrayList(LIST_STATE, new ArrayList<Item>(favorites.size()));
        outstate.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
    }

    private void display(View view) {
        recyclerView = view.findViewById(R.id.list_view_fav);
        img_empty = view.findViewById(R.id.img_no_data_fav);
        initRecylerview();
        if (favorites.isEmpty()) {
            img_empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            img_empty.setVisibility(View.GONE);
        }
    }


    private void initRecylerview() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favoriteDBhelper = new FavoriteDBHelper(getActivity());
        favoriteDBhelper.open();
        favorites = favoriteDBhelper.getAllFavorite();
        adapters = new AdapterFav(favorites, getContext());
        recyclerView.setAdapter(adapters);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapters.showShimmers = false;
                adapters.notifyDataSetChanged();
                img_empty.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }, 5000);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecylerview();
    }
}


