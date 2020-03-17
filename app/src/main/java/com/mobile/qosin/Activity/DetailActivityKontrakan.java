package com.mobile.qosin.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.R;

import java.util.ArrayList;

public class DetailActivityKontrakan extends AppCompatActivity {
    public static String KONTRAKAN_KEY = "Kontrakan_key";
    ArrayList<String> foto_kontrakan;
    ViewFlipper v_flipper_kontrakan;
    String namakontrakan;
    String wilayah;
    String kamarjumlah;
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
    ConstraintLayout btn_pesan_kontrakan;
    TextView t_namakontrakan,t_hargakontrakan,t_wilayah,t_kamar_sisa,t_ukuran_kamar,t_kamar_mandi,t_waktu_kunci,t_ac,t_wifi,t_listrik,t_bawa_hewan,t_parkiran_motor,t_parkiran_mobil,t_fasilitas,t_kampus,t_minimal,t_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kontrakan);
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
        btn_pesan_kontrakan = findViewById(R.id.btn_pesan_kontrakan);
        v_flipper_kontrakan = findViewById(R.id.slider_foto_kontrakan);
        t_namakontrakan = findViewById(R.id.nama_kontrakan);
        t_hargakontrakan = findViewById(R.id.harga_kontrakan);
        t_wilayah = findViewById(R.id.detail_alamat_kontrakan);
        t_kamar_sisa = findViewById(R.id.jumlah_kamar_kontrakan);
        t_ukuran_kamar = findViewById(R.id.dt_ukuran_kamar_kontrakan);
        t_kamar_mandi = findViewById(R.id.dt_kamarmandi_kontrakan);
        t_waktu_kunci = findViewById(R.id.dt_jamkunci_kontrakan);
        t_ac = findViewById(R.id.dt_ac_kontrakan);
        t_wifi = findViewById(R.id.dt_wifi_kontrakan);
        t_listrik = findViewById(R.id.dt_listrik_kontrakan);
        t_bawa_hewan = findViewById(R.id.dt_bawahewan_kontrakan);
        t_parkiran_motor = findViewById(R.id.dt_parkiranmotor_kontrakan);
        t_parkiran_mobil = findViewById(R.id.dt_parkiranmobil_kontrakan);
        t_fasilitas = findViewById(R.id.dt_tambahan_kontrakan);
        t_kampus = findViewById(R.id.dt_kampus_kontrakan);
        t_minimal = findViewById(R.id.dt_minimalbulan__kontrakan);
        t_update = findViewById(R.id.dt_timeupdate_kontrakan);
        getKontrakan();
        btn_pesan_kontrakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+6281371855757";
                String message = "Hai min, apakah "+namakontrakan+" masih tersedia ?";
                String url = "https://api.whatsapp.com/send?phone="+number+"&text=" + message;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }

    private void getKontrakan() {
        Intent intent = getIntent();
        Item kontrakan = intent.getParcelableExtra(KONTRAKAN_KEY);
        namakontrakan = kontrakan.getNama();
        t_namakontrakan.setText(namakontrakan);
        wilayah = kontrakan.getAlamat_singkat();
        t_wilayah.setText(wilayah);
        kamarjumlah = kontrakan.getKamar_tersisa();
        t_kamar_sisa.setText(kamarjumlah+" Kamar");
        ukuran_kamar = kontrakan.getUkuran_kamar();
        t_ukuran_kamar.setText(ukuran_kamar);
        kamar_mandi = kontrakan.getBanyak_kamar_mandi();
        t_kamar_mandi.setText(kamar_mandi);
        waktu_kunci = kontrakan.getAkses_kunci();
        t_waktu_kunci.setText(waktu_kunci);
        ac = kontrakan.getAc();
        t_ac.setText(ac);
        listrik = kontrakan.getListrik();
        t_listrik.setText(listrik);
        wifi = kontrakan.getWifi();
        t_wifi.setText(wifi);
        bawa_hewan = kontrakan.getBawa_hewan();
        t_bawa_hewan.setText(bawa_hewan);
        parkiran_motor = kontrakan.getParkiran_motor();
        t_parkiran_motor.setText(parkiran_motor);
        parkiran_mobil = kontrakan.getParkiran_mobil();
        t_parkiran_mobil.setText(parkiran_mobil);
        fasilitas_tambahan = kontrakan.getDeskripsi();
        t_fasilitas.setText(fasilitas_tambahan);
        kampus = kontrakan.getKampus();
        t_kampus.setText(kampus);
        minimal_bayar = kontrakan.getBulanan();
        t_minimal.setText(minimal_bayar);
        update = kontrakan.getTanggal_post();
        t_update.setText(update);
        hargakost = kontrakan.getSetahun();
        t_hargakontrakan.setText("Rp."+hargakost+"/Tahun");
        image_cover = kontrakan.getImage_thumb();
        image_depan = kontrakan.getImage_depan();
        image_kamar_mandi = kontrakan.getImage_kamar();
        image_kamar = kontrakan.getImage_luar();
        foto_kontrakan = new ArrayList<String>();
        foto_kontrakan.add(image_cover);
        foto_kontrakan.add(image_depan);
        foto_kontrakan.add(image_kamar);
        foto_kontrakan.add(image_kamar_mandi);
        flipperImage(foto_kontrakan);
    }

    private void flipperImage(ArrayList<String> foto_kost) {
        for(int i=0;i<foto_kost.size();i++){
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getApplicationContext())
                    .load(foto_kost.get(i).toString())
                    .into(imageView);
            v_flipper_kontrakan.addView(imageView);
            v_flipper_kontrakan.setFlipInterval(8000);
            v_flipper_kontrakan.setAutoStart(true);

            v_flipper_kontrakan.setInAnimation(this,R.anim.slide_right);
            v_flipper_kontrakan.setOutAnimation(this,R.anim.slide_ou_left);
        }
    }


}
