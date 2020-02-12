package com.mobile.qosin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.mobile.qosin.Adapter.CustomBottomBar;
import com.mobile.qosin.Adapter.ItemAdapter;
import com.mobile.qosin.Dashboard.Dashboard;
import com.mobile.qosin.FavoriteFragment;
import com.mobile.qosin.Model.CustomBottomItem;
import com.mobile.qosin.R;
import com.mobile.qosin.Tools.SessionManager;

public class MainActivity extends AppCompatActivity implements ItemAdapter.ItemSelectorInterface {
    private boolean doubleBackToExitPressedOnce = false;
    private SessionManager sessionManager;
    private Button logout;
    private CustomBottomBar customBottomBar;
    public static final int HOME = 0;
    public static final int FAVORITE = 1;
    public static final int PROFILE = 2;


    @SuppressLint("ResourceType")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        Dashboard dashboard = new Dashboard();
        Toolbar toolbar = findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(null);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, dashboard);
        transaction.addToBackStack(null);
        transaction.commit();
        customBottomBar = new CustomBottomBar(this, findViewById(R.id.customBottomBar), MainActivity.this);
        initItems();
        customBottomBar.changeBackground(getString(R.color.colorItemDefaultBackground));
        customBottomBar.setDefaultBackground(getString(R.color.colorItemDefaultBackground));
        customBottomBar.setDefaultTint(getString(R.color.colorItemDefaultTint));
        customBottomBar.changeDividerColor(getString(R.color.colorDivider));
        customBottomBar.hideDivider();
        customBottomBar.apply(HOME);
    }
    @SuppressLint("ResourceType")
    private void initItems() {
        CustomBottomItem home = new CustomBottomItem(HOME, R.drawable.icon_dashboard_home, getString(R.string.home_title), getString(R.color.colorItemBackground), getString(R.color.colorHijau));
        CustomBottomItem favorite = new CustomBottomItem(FAVORITE, R.drawable.ic_favorite_black_24dp, getString(R.string.favorite_title), getString(R.color.colorItemBackground), getString(R.color.colorHijau));
        CustomBottomItem profile = new CustomBottomItem(PROFILE, R.drawable.profile_icon, getString(R.string.profile_title), getString(R.color.colorItemBackground), getString(R.color.colorHijau));

        customBottomBar.addItem(home);
        customBottomBar.addItem(favorite);
        customBottomBar.addItem(profile);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Cari kost");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar dari Qosin", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }



    @Override
    public void itemSelect(int selectedID) {
        Fragment selectedFragment = null;
        switch (selectedID){
            case HOME:
                selectedFragment = new Dashboard();
                break;
            case FAVORITE:
                selectedFragment = new FavoriteFragment();
                break;
            case PROFILE:
                 selectedFragment = new Account();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, selectedFragment).commit();
        overridePendingTransition(R.anim.slide_right,R.anim.slide_ou_left);
    }
}
