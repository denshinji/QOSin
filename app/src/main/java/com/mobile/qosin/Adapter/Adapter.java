package com.mobile.qosin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobile.qosin.API.CustomFilter;
import com.mobile.qosin.Model.Kost;
import com.mobile.qosin.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements Filterable {

    public List<Kost> kosts, kostsFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter filter;

    public Adapter (List<Kost> kosts, Context context, RecyclerViewClickListener listener) {
        this.kosts = kosts;
        this.kostsFilter = kosts;
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

        holder.mName.setText(kosts.get(position).getNama());
        holder.mHarga.setText("Rp."+""+kosts.get(position).getHarga()+"/bulan");
        holder.mAlamat.setText(kosts.get(position).getAlamat_singkat());
        holder.mDeskripsi.setText(kosts.get(position).getDeskripsi());

        Glide.with(context)
                .load(kosts.get(position).getImage_thumb())
                .apply(new RequestOptions())
                .into(holder.mPicture);

    }

    @Override
    public int getItemCount() {
        return kosts.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilter((ArrayList<Kost>) kostsFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
        private TextView mName, mHarga, mAlamat,mDeskripsi;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.img_item_photo);
            mName = itemView.findViewById(R.id.item_namacontent);
            mDeskripsi = itemView.findViewById(R.id.item_deksripsi);
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
