package com.mobile.qosin.API;

import android.widget.Filter;

import com.mobile.qosin.Adapter.Adapter;
import com.mobile.qosin.Model.Item;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    Adapter adapters;
    ArrayList<Item> filterList;

    public CustomFilter(ArrayList<Item> filterList, Adapter adapter)
    {
        this.adapters=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Item> filteredKost = new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredKost.add(filterList.get(i));
                }
            }

            results.count=filteredKost.size();
            results.values=filteredKost;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapters.item = (ArrayList<Item>) results.values;

        //REFRESH
        adapters.notifyDataSetChanged();

    }
}