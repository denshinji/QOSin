package com.mobile.qosin.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mobile.qosin.Fragment.FragmentKostPria;
import com.mobile.qosin.Fragment.FragmentKostWanita;
import com.mobile.qosin.R;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ViewPageAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FragmentKostPria();
        } else if (position == 1) {
            return new FragmentKostWanita();
        } else {
            return new FragmentKostPria();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.kost_pria);
            case 1:
                return mContext.getString(R.string.kost_putri);
            default:
                return null;
        }
    }
}
