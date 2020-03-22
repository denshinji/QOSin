package com.mobile.qosin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobile.qosin.Model.Favorite;
import com.mobile.qosin.R;

import java.util.List;

public class AdapterFav extends RecyclerView.Adapter<AdapterFav.MyViewHolder> {

    public List<Favorite> item;
    private Context context;
    private RecyclerViewClickListener myListeners;

    public AdapterFav(List<Favorite> item, Context context, RecyclerViewClickListener listener) {
        this.item = item;
        this.context = context;
        this.myListeners = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kost, parent, false);
        return new AdapterFav.MyViewHolder(view, myListeners);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFav.MyViewHolder holder, int position) {
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

    @SuppressLint("CheckResult")

    @Override
    public int getItemCount() {
        return item.size();
    }


    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AdapterFav.RecyclerViewClickListener myListenerss;
        private ImageView mPicture;
        private TextView mName, mHarga, mAlamat;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, AdapterFav.RecyclerViewClickListener listener) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.img_item_photo);
            mName = itemView.findViewById(R.id.item_namacontent);
            mHarga = itemView.findViewById(R.id.item_harga);
            mAlamat = itemView.findViewById(R.id.item_rate);
            mRowContainer = itemView.findViewById(R.id.rv_item);
            myListenerss = listener;
            mRowContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item:
                    myListenerss.onRowClick(mRowContainer, getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }

}
