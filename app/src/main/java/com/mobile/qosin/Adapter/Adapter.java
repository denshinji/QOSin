package com.mobile.qosin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobile.qosin.API.CustomFilter;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements Filterable {

    public List<Item> item, ItemFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter filter;

    public Adapter(List<Item> item, Context context, RecyclerViewClickListener listener) {
        this.item = item;
        this.ItemFilter = item;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kost, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String jenis = item.get(position).getJenis();
        holder.mName.setText(item.get(position).getNama());
        holder.mAlamat.setText(item.get(position).getAlamat_singkat());
        if (jenis.equals("kontrakan")) {
            holder.mHarga.setText("Rp." + "" + item.get(position).getSetahun() + "/Tahun");
        } else if (jenis.equals("kost")) {
            holder.mHarga.setText("Rp." + "" + item.get(position).getHarga() + "/Bulan");
        }
        Glide.with(context)
                .load(item.get(position).getImage_thumb())
                .apply(new RequestOptions())
                .into(holder.mPicture);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter = new CustomFilter((ArrayList<Item>) ItemFilter, this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private ImageView mPicture;
        private TextView mName, mHarga, mAlamat;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.img_item_photo);
            mName = itemView.findViewById(R.id.item_namacontent);
            mHarga = itemView.findViewById(R.id.item_harga);
            mAlamat = itemView.findViewById(R.id.item_rate);
            mRowContainer = itemView.findViewById(R.id.rv_item);
            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

}
