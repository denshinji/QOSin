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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.mobile.qosin.API.CustomFilter;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements Filterable {

    public List<Item> item, ItemFilter;
    CustomFilter filter;
    private Context context;
    private RecyclerViewClickListener mListener;
    public boolean showShimmer = true;
    int shimmer_number = 5;

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
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer();
        } else {
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);
            String jenis = item.get(position).getJenis();
            String gethargakost = item.get(position).getHarga();
            holder.mName.setBackground(null);
            holder.mName.setText(item.get(position).getNama());
            holder.mAlamat.setBackground(null);
            holder.mAlamat.setText(item.get(position).getAlamat_singkat());
            holder.mTracking.setBackground(null);
            holder.mTracking.setBackgroundResource(R.drawable.ic_location_on_black_24dp);
            if (jenis.equals("Kontrakan")) {
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorHijau));
                holder.mHarga.setText("Rp." + "" + item.get(position).getSetahun() + "/Tahun");
            } else if (jenis.equals("Kos")) {
                if (gethargakost != null) {
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorHijau));
                    holder.mHarga.setText("Rp." + "" + item.get(position).getHarga() + "/Bulan");
                } else {
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorHijau));
                    holder.mHarga.setText("Rp." + "" + item.get(position).getSetahun() + "/Tahun");
                }

            }
            holder.mPicture.setBackground(null);
            Glide.with(context)
                    .load(item.get(position).getImage_thumb())
                    .apply(new RequestOptions())
                    .into(holder.mPicture);

        }
    }


    @Override
    public int getItemCount() {
        return showShimmer ? shimmer_number : item.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter((ArrayList<Item>) ItemFilter, this);

        }
        return filter;
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private ImageView mPicture, mTracking;
        private TextView mName, mHarga, mAlamat;
        private RelativeLayout mRowContainer;
        private ShimmerFrameLayout shimmerFrameLayout;
        private CardView cardView;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mTracking = itemView.findViewById(R.id.item_mark);
            cardView = itemView.findViewById(R.id.cv_harga);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer_layout);
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

}
