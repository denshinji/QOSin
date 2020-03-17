package com.mobile.qosin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mobile.qosin.R;
import com.mobile.qosin.Tools.DetailFoto;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;


public class SliderFotoDetail extends SliderViewAdapter<SliderFotoDetail.SliderAdapterVH> {

    private ArrayList<String> foto = new ArrayList<>();
    private Context context;

    public SliderFotoDetail(Context context, ArrayList<String> foto) {
        this.context = context;
        this.foto = foto;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foto_detail_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        Glide.with(viewHolder.itemView)
                .load(foto.get(position))
                .into(viewHolder.imageViewBackground);

        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailFoto.class);
                intent.putStringArrayListExtra("foto", foto);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 4;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.image_slide_foto_detail);
            this.itemView = itemView;
        }
    }
}
