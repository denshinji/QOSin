package com.mobile.qosin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.mobile.qosin.Model.Kost;
import com.mobile.qosin.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    public static String KOST_KEY = "Kost_key";
    ArrayList<String> foto_kost;
    ViewFlipper v_flipper;
    String namakost;
    String hargakost;
    String image_cover;
    String image_depan;
    String image_kamar;
    String image_kamar_mandi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kost);
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle(null);
        v_flipper = findViewById(R.id.slider_foto_kost);
        TextView t_namakost = findViewById(R.id.nama_kost);
        TextView t_hargakost = findViewById(R.id.harga_kost);
        Intent intent = getIntent();
        Kost kost = intent.getParcelableExtra(KOST_KEY);
        namakost = kost.getNama();
        t_namakost.setText(namakost);
        hargakost = kost.getHarga();
        t_hargakost.setText("Rp."+hargakost+"/bulan");
        image_cover = kost.getImage_thumb();
        image_depan = kost.getImage_depan();
        image_kamar_mandi = kost.getImage_kamar();
        image_kamar = kost.getImage_luar();
        foto_kost = new ArrayList<String>();
        foto_kost.add(image_cover);
        foto_kost.add(image_depan);
        foto_kost.add(image_kamar);
        foto_kost.add(image_kamar_mandi);
        flipperImage(foto_kost);
    }

    private void flipperImage(ArrayList<String> foto_kost) {
        for(int i=0;i<foto_kost.size();i++){
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getApplicationContext())
                    .load(foto_kost.get(i).toString())
                    .into(imageView);
            v_flipper.addView(imageView);
            v_flipper.setFlipInterval(8000);
            v_flipper.setAutoStart(true);

            v_flipper.setInAnimation(this,R.anim.slide_right);
            v_flipper.setOutAnimation(this,R.anim.slide_ou_left);
        }
    }


}
