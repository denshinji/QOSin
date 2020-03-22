package com.mobile.qosin.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
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

public class DetailActivityKontrakan extends AppCompatActivity {
    public static String KONTRAKAN_KEY = "Kontrakan_key";
    ArrayList<String> foto_kontrakan = new ArrayList<>();
    SQLiteDatabase mkdb;
    private String namakontrakan, wilayah, kamarjumlah, ukuran_kamar, jenis, kamar_mandi, waktu_kunci, ac, wifi, listrik, bawa_hewan, parkiran_motor,
            parkiran_mobil, fasilitas_tambahan, kampus, minimal_bayar, update, hargakost, image_cover, image_depan, image_kamar,
            image_kamar_mandi, gender, banyak_kamar_mandi, setahun;
    private int id;
    private AppCompatActivity activity = DetailActivityKontrakan.this;
    private FavoriteDBHelper favoriteDBHelper;
    private Item item;
    SliderView slkn;
    ConstraintLayout btn_pesan_kontrakan;
    TextView t_namakontrakan, t_hargakontrakan, t_wilayah, t_kamar_sisa, t_ukuran_kamar, t_kamar_mandi, t_waktu_kunci, t_ac, t_wifi, t_listrik, t_bawa_hewan, t_parkiran_motor, t_parkiran_mobil, t_fasilitas, t_kampus, t_minimal, t_update;

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
        slkn = findViewById(R.id.slider_foto_kontrakan);
        FavoriteDBHelper favoritehelper = new FavoriteDBHelper(this);
        mkdb = favoritehelper.getWritableDatabase();
        getKontrakan();
        getDetailFotoKontrakan();
        btn_pesan_kontrakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+6281371855757";
                String message = "Hai min, apakah " + namakontrakan + " masih tersedia ?";
                String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + message;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        getFavButton();
    }


    private void getKontrakan() {
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
        Intent intent = getIntent();
        Item kontrakan = intent.getParcelableExtra(KONTRAKAN_KEY);
        namakontrakan = kontrakan.getNama();
        t_namakontrakan.setText(namakontrakan);
        wilayah = kontrakan.getAlamat_singkat();
        t_wilayah.setText(wilayah);
        kamarjumlah = kontrakan.getKamar_tersisa();
        t_kamar_sisa.setText(kamarjumlah + " Kamar");
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
        t_hargakontrakan.setText("Rp." + hargakost + "/Tahun");
        image_cover = kontrakan.getImage_thumb();
        image_depan = kontrakan.getImage_depan();
        image_kamar_mandi = kontrakan.getImage_kamar();
        image_kamar = kontrakan.getImage_luar();
        foto_kontrakan = new ArrayList<String>();
        foto_kontrakan.add(image_cover);
        foto_kontrakan.add(image_depan);
        foto_kontrakan.add(image_kamar);
        foto_kontrakan.add(image_kamar_mandi);
    }

    private void getDetailFotoKontrakan() {
        slkn.setSliderAdapter(new SliderFotoDetail(getApplicationContext(), foto_kontrakan));
        slkn.startAutoCycle();
        slkn.setScrollTimeInSec(6);
        slkn.setIndicatorAnimation(IndicatorAnimations.WORM);
        slkn.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    private void getFavButton() {
        MaterialFavoriteButton favoriteButton = findViewById(R.id.favorite_button_kontrakan);
        if (Exists(namakontrakan)) {
            favoriteButton.setFavorite(true);
            favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite == true) {
                        saveFavorite();
                        Snackbar.make(buttonView, "Berhasil memasukan ke favorite list", Snackbar.LENGTH_SHORT).show();
                    } else {
                        favoriteDBHelper = new FavoriteDBHelper(getApplicationContext());
                        favoriteDBHelper.deleteFavorite(namakontrakan);
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
                        favoriteDBHelper.deleteFavorite(namakontrakan);
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
        favorite.setNama(namakontrakan);
        favorite.setAlamat_singkat(wilayah);
        favorite.setKampus(kampus);
        favorite.setGender(gender);
        favorite.setKamar_tersisa(kamarjumlah);
        favorite.setBanyak_kamar_mandi(banyak_kamar_mandi);
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
        Cursor cursor = mkdb.query(FavoriteContract.Favorites.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }


}
