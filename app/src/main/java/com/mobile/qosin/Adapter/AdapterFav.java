package com.mobile.qosin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.mobile.qosin.Activity.DetailActivityKontrakan;
import com.mobile.qosin.Activity.DetailActivityKost;
import com.mobile.qosin.Model.Favorite;
import com.mobile.qosin.R;

import java.util.List;

public class AdapterFav extends RecyclerView.Adapter<AdapterFav.MyFavHolders> {

    private List<Favorite> fav;
    private Context context;

    public AdapterFav(List<Favorite> fav, Context context) {
        this.fav = fav;
        this.context = context;
    }


    @NonNull
    @Override
    public MyFavHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kost, parent, false);
        return new MyFavHolders(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull MyFavHolders holder, final int position) {
        final String jenisa = fav.get(position).getJenis();
        String gethargakost = fav.get(position).getHarga();
        holder.mName.setText(fav.get(position).getNama());
        holder.mAlamat.setText(fav.get(position).getAlamat_singkat());
        if (jenisa.equals("Kontrakan")) {
            holder.mHarga.setText("Rp." + "" + fav.get(position).getSetahun() + "/Tahun");
        }
        if (jenisa.equals("Kos")) {
            if (gethargakost != null) {
                holder.mHarga.setText("Rp." + "" + fav.get(position).getHarga() + "/Bulan");
            } else {
                holder.mHarga.setText("Rp." + "" + fav.get(position).getSetahun() + "/Tahun");
            }

        }
        Glide.with(context)
                .load(fav.get(position).getImage_thumb())
                .apply(new RequestOptions())
                .into(holder.mPicture);

        holder.mRowContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intentfav = "FAV";
                if (jenisa.equals("Kos")) {
                    Intent intent = new Intent(v.getContext(), DetailActivityKost.class);
                    intent.putExtra(DetailActivityKost.KOST_KEY, fav.get(position));
                    intent.putExtra(DetailActivityKost.FAV_KEY, fav.get(position));
                    intent.putExtra("iFav", intentfav);
                    v.getContext().startActivity(intent);
                }
                if (jenisa.equals("Kontrakan")) {
                    Intent intent = new Intent(v.getContext(), DetailActivityKontrakan.class);
                    intent.putExtra(DetailActivityKontrakan.KONTRAKAN_KEY, fav.get(position));
                    intent.putExtra(DetailActivityKontrakan.FAV_KEY, fav.get(position));
                    intent.putExtra("iFav", intentfav);
                    v.getContext().startActivity(intent);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return fav.size();
    }


    public class MyFavHolders extends RecyclerView.ViewHolder {

        private ImageView mPicture;
        private TextView mName, mHarga, mAlamat;
        private RelativeLayout mRowContainer;

        public MyFavHolders(View itemView) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.img_item_photo);
            mName = itemView.findViewById(R.id.item_namacontent);
            mHarga = itemView.findViewById(R.id.item_harga);
            mAlamat = itemView.findViewById(R.id.item_rate);
            mRowContainer = itemView.findViewById(R.id.rv_item);
        }


    }
}
