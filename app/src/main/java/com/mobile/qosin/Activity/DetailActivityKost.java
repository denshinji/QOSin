package com.mobile.qosin.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.mobile.qosin.Adapter.SliderFotoDetail;
import com.mobile.qosin.Db.FavoriteContract;
import com.mobile.qosin.Db.FavoriteDBHelper;
import com.mobile.qosin.Model.Favorite;
import com.mobile.qosin.Model.Item;
import com.mobile.qosin.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class DetailActivityKost extends AppCompatActivity {
    public static String KOST_KEY = "Kost_key";
    public static String FAV_KEY = "Fav_key";
    ArrayList<String> foto_kost = new ArrayList<>();
    SliderView slk;
    SQLiteDatabase mdb;
    private String namakost, wilayah, kamarsisa, ukuran_kamar, jenis, kamar_mandi, waktu_kunci, ac, wifi, listrik, bawa_hewan, parkiran_motor,
            parkiran_mobil, fasilitas_tambahan, kampus, minimal_bayar, update, hargakost, image_cover, image_depan, image_kamar,
            image_kamar_mandi, gender, banyak_kamar_mandi, setahun, hargatahunan;
    private int id;
    private AppCompatActivity activity = DetailActivityKost.this;
    private ConstraintLayout btn_pesan;
    private Item item;
    private Favorite fav;
    private FavoriteDBHelper favoriteDBHelper;
    private TextView t_namakost, t_hargakost, t_wilayah, t_kamar_sisa, t_ukuran_kamar, t_kamar_mandi, t_waktu_kunci, t_ac, t_wifi, t_listrik, t_bawa_hewan, t_parkiran_motor, t_parkiran_mobil, t_fasilitas, t_kampus, t_minimal, t_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kost);
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        slk = findViewById(R.id.slider_foto_kost);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle(null);
        btn_pesan = findViewById(R.id.btn_pesan);
        FavoriteDBHelper favoritehelper = new FavoriteDBHelper(this);
        mdb = favoritehelper.getWritableDatabase();
        getKost();
        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+6281371855757";
                String message = "Hai min, apakah " + namakost + " masih ada kamar tersedia ?";
                String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + message;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        getSliderFotoDetail();
        getFavButton();


    }

    private void getKost() {
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
        Intent intent = getIntent();
        String pn = intent.getStringExtra("iFav");
        if (pn.equals("FAV")) {
            Favorite kost = intent.getParcelableExtra(FAV_KEY);
            jenis = kost.getJenis();
            setahun = kost.getSetahun();
            banyak_kamar_mandi = kost.getBanyak_kamar_mandi();
            namakost = kost.getNama();
            t_namakost.setText(namakost);
            wilayah = kost.getAlamat_singkat();
            t_wilayah.setText(wilayah);
            kamarsisa = kost.getKamar_tersisa();
            t_kamar_sisa.setText(kamarsisa + " Kamar Tersisa");
            ukuran_kamar = kost.getUkuran_kamar();
            t_ukuran_kamar.setText(": " + kost.getUkuran_kamar());
            kamar_mandi = kost.getKamar_mandi();
            t_kamar_mandi.setText(": " + kamar_mandi);
            waktu_kunci = kost.getAkses_kunci();
            t_waktu_kunci.setText(": " + waktu_kunci);
            ac = kost.getAc();
            t_ac.setText(": " + ac);
            id = kost.getId();
            listrik = kost.getListrik();
            t_listrik.setText(": " + listrik);
            wifi = kost.getWifi();
            t_wifi.setText(": " + wifi);
            bawa_hewan = kost.getBawa_hewan();
            t_bawa_hewan.setText(": " + bawa_hewan);
            parkiran_motor = kost.getParkiran_motor();
            t_parkiran_motor.setText(": " + parkiran_motor);
            parkiran_mobil = kost.getParkiran_mobil();
            t_parkiran_mobil.setText(": " + parkiran_mobil);
            fasilitas_tambahan = kost.getDeskripsi();
            if (TextUtils.isEmpty(fasilitas_tambahan)) {
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
            gender = kost.getGender();
            hargakost = kost.getHarga();
            if (hargakost == null) {
                hargakost = kost.getSetahun();
            }
            t_hargakost.setText("Rp." + hargakost + "/bulan");
            image_cover = kost.getImage_thumb();
            image_depan = kost.getImage_depan();
            image_kamar_mandi = kost.getImage_kamar();
            image_kamar = kost.getImage_luar();
            foto_kost.add(image_cover);
            foto_kost.add(image_depan);
            foto_kost.add(image_kamar);
            foto_kost.add(image_kamar_mandi);
        }
        if (pn.equals("KOST")) {
            Item kost = intent.getParcelableExtra(KOST_KEY);
            jenis = kost.getJenis();
            setahun = kost.getSetahun();
            banyak_kamar_mandi = kost.getBanyak_kamar_mandi();
            namakost = kost.getNama();
            t_namakost.setText(namakost);
            wilayah = kost.getAlamat_singkat();
            t_wilayah.setText(wilayah);
            kamarsisa = kost.getKamar_tersisa();
            t_kamar_sisa.setText(kamarsisa + " Kamar Tersisa");
            ukuran_kamar = kost.getUkuran_kamar();
            t_ukuran_kamar.setText(": " + kost.getUkuran_kamar());
            kamar_mandi = kost.getKamar_mandi();
            t_kamar_mandi.setText(": " + kamar_mandi);
            waktu_kunci = kost.getAkses_kunci();
            t_waktu_kunci.setText(": " + waktu_kunci);
            ac = kost.getAc();
            t_ac.setText(": " + ac);
            id = kost.getId();
            listrik = kost.getListrik();
            t_listrik.setText(": " + listrik);
            wifi = kost.getWifi();
            t_wifi.setText(": " + wifi);
            bawa_hewan = kost.getBawa_hewan();
            t_bawa_hewan.setText(": " + bawa_hewan);
            parkiran_motor = kost.getParkiran_motor();
            t_parkiran_motor.setText(": " + parkiran_motor);
            parkiran_mobil = kost.getParkiran_mobil();
            t_parkiran_mobil.setText(": " + parkiran_mobil);
            fasilitas_tambahan = kost.getDeskripsi();
            if (TextUtils.isEmpty(fasilitas_tambahan)) {
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
            gender = kost.getGender();
            hargakost = kost.getHarga();
            if (hargakost == null) {
                hargakost = kost.getSetahun();
            }
            t_hargakost.setText("Rp." + hargakost + "/bulan");
            image_cover = kost.getImage_thumb();
            image_depan = kost.getImage_depan();
            image_kamar_mandi = kost.getImage_kamar();
            image_kamar = kost.getImage_luar();
            foto_kost.add(image_cover);
            foto_kost.add(image_depan);
            foto_kost.add(image_kamar);
            foto_kost.add(image_kamar_mandi);

        }

    }

    private void getSliderFotoDetail() {
        slk.setSliderAdapter(new SliderFotoDetail(getApplicationContext(), foto_kost));
        slk.startAutoCycle();
        slk.setScrollTimeInSec(6);
        slk.setIndicatorAnimation(IndicatorAnimations.WORM);
        slk.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    private void getFavButton() {
        MaterialFavoriteButton favoriteButton = findViewById(R.id.favorite_button);
        if (Exists(namakost)) {
            favoriteButton.setFavorite(true);
            favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite == true) {
                        saveFavorite();
                        Snackbar.make(buttonView, "Berhasil memasukan ke favorite list", Snackbar.LENGTH_SHORT).show();
                    } else {
                        favoriteDBHelper = new FavoriteDBHelper(getApplicationContext());
                        favoriteDBHelper.deleteFavorite(namakost);
                        Snackbar.make(buttonView, "Item di hapus dari list favorite", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite == true) {
                        saveFavorite();
                        Snackbar.make(buttonView, "Berhasil memasukan ke favorite list", Snackbar.LENGTH_SHORT).show();
                    } else {
                        favoriteDBHelper = new FavoriteDBHelper(getApplicationContext());
                        favoriteDBHelper.deleteFavorite(namakost);
                        Snackbar.make(buttonView, "Item di hapus dari list favorite", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void saveFavorite() {
        favoriteDBHelper = new FavoriteDBHelper(activity);
        Favorite favorite = new Favorite();
        item = new Item();
        favorite.setId(id);
        favorite.setJenis(jenis);
        favorite.setTanggal_post(update);
        favorite.setNama(namakost);
        favorite.setAlamat_singkat(wilayah);
        favorite.setKampus(kampus);
        favorite.setGender(gender);
        favorite.setKamar_tersisa(kamarsisa);
        favorite.setUkuran_kamar(ukuran_kamar);
        favorite.setKamar_mandi(kamar_mandi);
        favorite.setBanyak_kamar_mandi(banyak_kamar_mandi);
        favorite.setAkses_kunci(waktu_kunci);
        favorite.setWifi(wifi);
        favorite.setListrik(listrik);
        favorite.setParkiran_motor(parkiran_motor);
        favorite.setParkiran_mobil(parkiran_mobil);
        favorite.setDeskripsi(fasilitas_tambahan);
        favorite.setBawa_hewan(bawa_hewan);
        favorite.setAc(ac);
        favorite.setHarga(hargakost);
        favorite.setSetahun(setahun);
        favorite.setBulanan(minimal_bayar);
        favorite.setImage_thumb(image_cover);
        favorite.setImage_depan(image_depan);
        favorite.setImage_luar(image_kamar_mandi);
        favorite.setKamar_mandi(image_kamar);
        favoriteDBHelper.addFavorite(favorite);
    }

    public boolean Exists(String searchItem) {

        String[] projection = {
                FavoriteContract.Favorites._ID,
                FavoriteContract.Favorites.C_ID,
                FavoriteContract.Favorites.C_JENIS,
                FavoriteContract.Favorites.C_TGL,
                FavoriteContract.Favorites.C_NAME,
                FavoriteContract.Favorites.C_ALAMAT,
                FavoriteContract.Favorites.C_KAMPUS,
                FavoriteContract.Favorites.C_GENDER,
                FavoriteContract.Favorites.C_KAMAR_TERSISA,
                FavoriteContract.Favorites.C_UKURAN_KAMAR,
                FavoriteContract.Favorites.C_KAMAR_MANDI,
                FavoriteContract.Favorites.C_BANYAK_KAMAR_MANDI,
                FavoriteContract.Favorites.C_AKSES,
                FavoriteContract.Favorites.C_WIFI,
                FavoriteContract.Favorites.C_LISTRIK,
                FavoriteContract.Favorites.C_P_MOTOR,
                FavoriteContract.Favorites.C_P_MOBIL,
                FavoriteContract.Favorites.C_DESKRIPSI,
                FavoriteContract.Favorites.C_BAWA_HEWAN,
                FavoriteContract.Favorites.C_AC,
                FavoriteContract.Favorites.C__SEBULAN,
                FavoriteContract.Favorites.C__SETAHUN,
                FavoriteContract.Favorites.C__BULANAN,
                FavoriteContract.Favorites.C__COVER,
                FavoriteContract.Favorites.C__DEPAN,
                FavoriteContract.Favorites.C__LUAR,
                FavoriteContract.Favorites.C__KAMAR

        };
        String selection = FavoriteContract.Favorites.C_NAME + " =?";
        String[] selectionArgs = {searchItem};
        String limit = "1";
        Cursor cursor = mdb.query(FavoriteContract.Favorites.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }


}
