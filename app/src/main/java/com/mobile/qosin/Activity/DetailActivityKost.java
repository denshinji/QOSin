package com.mobile.qosin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.Model.Kost;
import com.mobile.qosin.R;

import java.util.ArrayList;

public class DetailActivityKost extends AppCompatActivity {
    public static String KOST_KEY = "Kost_key";
    ArrayList<String> foto_kost;
    ViewFlipper v_flipper;
    String namakost;
    String wilayah;
    String kamarsisa;
    String ukuran_kamar;
    String kamar_mandi;
    String waktu_kunci;
    String ac;
    String wifi;
    String listrik;
    String bawa_hewan;
    String parkiran_motor;
    String parkiran_mobil;
    String fasilitas_tambahan;
    String kampus;
    String minimal_bayar;
    String update;
    String hargakost;
    String image_cover;
    String image_depan;
    String image_kamar;
    String image_kamar_mandi;
    ConstraintLayout btn_pesan;
    TextView t_namakost,t_hargakost,t_wilayah,t_kamar_sisa,t_ukuran_kamar,t_kamar_mandi,t_waktu_kunci,t_ac,t_wifi,t_listrik,t_bawa_hewan,t_parkiran_motor,t_parkiran_mobil,t_fasilitas,t_kampus,t_minimal,t_update;
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
        btn_pesan = findViewById(R.id.btn_pesan);
        v_flipper = findViewById(R.id.slider_foto_kost);
        t_namakost = findViewById(R.id.nama_kost);
        t_hargakost = findViewById(R.id.harga_kost);
        t_wilayah = findViewById(R.id.detail_alamat_kost);
        t_kamar_sisa = findViewById(R.id.dt_sisa_kamar_kost);
        t_ukuran_kamar = findViewById(R.id.dt_ukuran_kamar_kost);
        t_kamar_mandi = findViewById(R.id.dt_kamarmandi_kost);
        t_waktu_kunci = findViewById(R.id.dt_jamkunci_kost);
        t_ac = findViewById(R.id.dt_ac_kost);
        t_wifi = findViewById(R.id.dt_wifi_kost);
        t_listrik = findViewById(R.id.dt_listrik_kost);
        t_bawa_hewan = findViewById(R.id.dt_bawahewan_kost);
        t_parkiran_motor = findViewById(R.id.dt_parkiranmotor_kost);
        t_parkiran_mobil = findViewById(R.id.dt_parkiranmobil_kost);
        t_fasilitas = findViewById(R.id.dt_tambahan);
        t_kampus = findViewById(R.id.dt_kampus_kost);
        t_minimal = findViewById(R.id.dt_minimalbulan_kost);
        t_update = findViewById(R.id.dt_timeupdate_kost);
        getKost();
        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+6281371855757";
                String message = "Hai min, apakah "+namakost+" masih ada kamar tersedia ?";
                String url = "https://api.whatsapp.com/send?phone="+number+"&text=" + message;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }

    private void getKost() {
        Intent intent = getIntent();
        Item kost = intent.getParcelableExtra(KOST_KEY);
        namakost = kost.getNama();
        t_namakost.setText(namakost);
        wilayah = kost.getAlamat_singkat();
        t_wilayah.setText(wilayah);
        kamarsisa = kost.getKamar_tersisa();
        t_kamar_sisa.setText(kamarsisa +" Kamar Tersisa");
        ukuran_kamar = kost.getUkuran_kamar();
        t_ukuran_kamar.setText(": "+kost.getUkuran_kamar());
        kamar_mandi = kost.getKamar_mandi();
        t_kamar_mandi.setText(": "+kamar_mandi);
        waktu_kunci = kost.getAkses_kunci();
        t_waktu_kunci.setText(": "+waktu_kunci);
        ac = kost.getAc();
        t_ac.setText(": "+ac);
        listrik = kost.getListrik();
        t_listrik.setText(": "+listrik);
        wifi = kost.getWifi();
        t_wifi.setText(": "+wifi);
        bawa_hewan = kost.getBawa_hewan();
        t_bawa_hewan.setText(": "+bawa_hewan);
        parkiran_motor = kost.getParkiran_motor();
        t_parkiran_motor.setText(": "+parkiran_motor);
        parkiran_mobil = kost.getParkiran_mobil();
        t_parkiran_mobil.setText(": "+parkiran_mobil);
        fasilitas_tambahan = kost.getDeskripsi();
        if(TextUtils.isEmpty(fasilitas_tambahan)){
            t_fasilitas.setText(": -");
        } else {
            t_fasilitas.setText(": " + fasilitas_tambahan);
        }
        kampus = kost.getKampus();
        t_kampus.setText(kampus);
        minimal_bayar = kost.getBulanan();
        t_minimal.setText(minimal_bayar);
        update = kost.getTanggal_post();
        t_update.setText(update);
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
