package com.mobile.qosin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.qosin.Activity.ActivityKontrakan;
import com.mobile.qosin.Activity.ActivityKost;
import com.mobile.qosin.R;

import java.util.ArrayList;


/**
 * Created by User on 2/12/2018.
 */

public class RecyclerViewAdapterButton extends RecyclerView.Adapter<RecyclerViewAdapterButton.ViewHolder> {


    private ArrayList<Integer> mImage = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterButton(Context context, ArrayList<Integer> mimage, ArrayList<String> mdesc) {
        mImage = mimage;
        mDesc = mdesc;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_button_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(mImage.get(position))
                .into(holder.image);

        holder.text.setText(mDesc.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDesc.get(position).equals("KOST")) {
                    Intent intent = new Intent(v.getContext(), ActivityKost.class);
                    v.getContext().startActivity(intent);
                } else if (mDesc.get(position).equals("KONTRAKAN")) {
                    Intent intent = new Intent(v.getContext(), ActivityKontrakan.class);
                    v.getContext().startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), "Maaf menu ini belum tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.im_button);
            text = itemView.findViewById(R.id.button_id);
        }
    }
}
