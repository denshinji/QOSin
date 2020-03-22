package com.mobile.qosin.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_AC;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_AKSES;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_ALAMAT;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_BANYAK_KAMAR_MANDI;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_BAWA_HEWAN;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_DESKRIPSI;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_GENDER;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_ID;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_KAMAR_MANDI;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_KAMAR_TERSISA;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_KAMPUS;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_LISTRIK;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_NAME;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_P_MOBIL;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_P_MOTOR;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_TGL;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_UKURAN_KAMAR;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_WIFI;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C__BULANAN;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C__COVER;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C__DEPAN;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C__KAMAR;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C__LUAR;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C__SEBULAN;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C__SETAHUN;

public class Favorite implements Parcelable {

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
    @SerializedName("id_content")
    private int id;
    @SerializedName("tipe_content")
    private String jenis;
    @SerializedName("tanggal_post")
    private String tanggal_post;
    @SerializedName("judul_item")
    private String nama;
    @SerializedName("wilayah")
    private String alamat_singkat;
    @SerializedName("kampus")
    private String kampus;
    @SerializedName("tipe")
    private String gender;
    @SerializedName("jumlah_kamar")
    private String kamar_tersisa;
    @SerializedName("luas_kamar")
    private String ukuran_kamar;
    @SerializedName("kamar_mandi")
    private String kamar_mandi;
    @SerializedName("banyak_kamar_mandi")
    private String banyak_kamar_mandi;
    @SerializedName("waktu_kunci")
    private String akses_kunci;
    @SerializedName("wifi")
    private String wifi;
    @SerializedName("listrik")
    private String listrik;
    @SerializedName("parkiran_motor")
    private String parkiran_motor;
    @SerializedName("parkiran_mobil")
    private String parkiran_mobil;
    @SerializedName("fasilitas tambahan")
    private String deskripsi;
    @SerializedName("bawa_hewan")
    private String bawa_hewan;
    @SerializedName("ac")
    private String ac;
    @SerializedName("bulanan")
    private String harga;
    @SerializedName("sat_tahun")
    private String setahun;
    @SerializedName("minimal_bulan")
    private String bulanan;
    @SerializedName("foto_cover")
    private String image_thumb;
    @SerializedName("foto_luar")
    private String image_depan;
    @SerializedName("foto_kamar")
    private String image_luar;
    @SerializedName("foto_kamar_mandi")
    private String image_kamar;

    public Favorite() {
    }

    public Favorite(int id, String jenis, String tanggal_post, String nama, String alamat_singkat,
                    String kampus, String gender, String kamar_tersisa, String ukuran_kamar, String kamar_mandi,
                    String banyak_kamar_mandi, String akses_kunci, String wifi, String listrik, String parkiran_motor,
                    String parkiran_mobil, String deskripsi, String bawa_hewan, String ac, String harga, String setahun,
                    String bulanan, String image_thumb, String image_depan, String image_luar, String image_kamar) {
        this.id = id;
        this.jenis = jenis;
        this.tanggal_post = tanggal_post;
        this.nama = nama;
        this.alamat_singkat = alamat_singkat;
        this.kampus = kampus;
        this.gender = gender;
        this.kamar_tersisa = kamar_tersisa;
        this.ukuran_kamar = ukuran_kamar;
        this.kamar_mandi = kamar_mandi;
        this.banyak_kamar_mandi = banyak_kamar_mandi;
        this.akses_kunci = akses_kunci;
        this.wifi = wifi;
        this.listrik = listrik;
        this.parkiran_motor = parkiran_motor;
        this.parkiran_mobil = parkiran_mobil;
        this.deskripsi = deskripsi;
        this.bawa_hewan = bawa_hewan;
        this.ac = ac;
        this.harga = harga;
        this.setahun = setahun;
        this.bulanan = bulanan;
        this.image_thumb = image_thumb;
        this.image_depan = image_depan;
        this.image_luar = image_luar;
        this.image_kamar = image_kamar;
    }

    protected Favorite(Parcel in) {
        id = in.readInt();
        jenis = in.readString();
        tanggal_post = in.readString();
        nama = in.readString();
        alamat_singkat = in.readString();
        kampus = in.readString();
        gender = in.readString();
        kamar_tersisa = in.readString();
        ukuran_kamar = in.readString();
        kamar_mandi = in.readString();
        banyak_kamar_mandi = in.readString();
        akses_kunci = in.readString();
        wifi = in.readString();
        listrik = in.readString();
        parkiran_motor = in.readString();
        parkiran_mobil = in.readString();
        deskripsi = in.readString();
        bawa_hewan = in.readString();
        ac = in.readString();
        harga = in.readString();
        setahun = in.readString();
        bulanan = in.readString();
        image_thumb = in.readString();
        image_depan = in.readString();
        image_luar = in.readString();
        image_kamar = in.readString();
    }

    public Favorite(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(C_ID));
        this.jenis = getColumnString(cursor, C_NAME);
        this.tanggal_post = getColumnString(cursor, C_TGL);
        this.nama = getColumnString(cursor, C_NAME);
        this.alamat_singkat = getColumnString(cursor, C_ALAMAT);
        this.kampus = getColumnString(cursor, C_KAMPUS);
        this.gender = getColumnString(cursor, C_GENDER);
        this.kamar_tersisa = getColumnString(cursor, C_KAMAR_TERSISA);
        this.ukuran_kamar = getColumnString(cursor, C_UKURAN_KAMAR);
        this.kamar_mandi = getColumnString(cursor, C_KAMAR_MANDI);
        this.banyak_kamar_mandi = getColumnString(cursor, C_BANYAK_KAMAR_MANDI);
        this.akses_kunci = getColumnString(cursor, C_AKSES);
        this.wifi = getColumnString(cursor, C_WIFI);
        this.listrik = getColumnString(cursor, C_LISTRIK);
        this.parkiran_motor = getColumnString(cursor, C_P_MOTOR);
        this.parkiran_mobil = getColumnString(cursor, C_P_MOBIL);
        this.deskripsi = getColumnString(cursor, C_DESKRIPSI);
        this.bawa_hewan = getColumnString(cursor, C_BAWA_HEWAN);
        this.ac = getColumnString(cursor, C_AC);
        this.harga = getColumnString(cursor, C__SEBULAN);
        this.setahun = getColumnString(cursor, C__SETAHUN);
        this.bulanan = getColumnString(cursor, C__BULANAN);
        this.image_thumb = getColumnString(cursor, C__COVER);
        this.image_depan = getColumnString(cursor, C__DEPAN);
        this.image_luar = getColumnString(cursor, C__LUAR);
        this.image_kamar = getColumnString(cursor, C__KAMAR);
    }

    private static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static Favorite fromContentValues(ContentValues values) {
        final Favorite itemFavorite = new Favorite();
        if (values.containsKey(C_ID)) {
            itemFavorite.id = values.getAsInteger(C_ID);
        }

        return itemFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTanggal_post() {
        return tanggal_post;
    }

    public void setTanggal_post(String tanggal_post) {
        this.tanggal_post = tanggal_post;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat_singkat() {
        return alamat_singkat;
    }

    public void setAlamat_singkat(String alamat_singkat) {
        this.alamat_singkat = alamat_singkat;
    }

    public String getKampus() {
        return kampus;
    }

    public void setKampus(String kampus) {
        this.kampus = kampus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKamar_tersisa() {
        return kamar_tersisa;
    }

    public void setKamar_tersisa(String kamar_tersisa) {
        this.kamar_tersisa = kamar_tersisa;
    }

    public String getUkuran_kamar() {
        return ukuran_kamar;
    }

    public void setUkuran_kamar(String ukuran_kamar) {
        this.ukuran_kamar = ukuran_kamar;
    }

    public String getKamar_mandi() {
        return kamar_mandi;
    }

    public void setKamar_mandi(String kamar_mandi) {
        this.kamar_mandi = kamar_mandi;
    }

    public String getBanyak_kamar_mandi() {
        return banyak_kamar_mandi;
    }

    public void setBanyak_kamar_mandi(String banyak_kamar_mandi) {
        this.banyak_kamar_mandi = banyak_kamar_mandi;
    }

    public String getAkses_kunci() {
        return akses_kunci;
    }

    public void setAkses_kunci(String akses_kunci) {
        this.akses_kunci = akses_kunci;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getListrik() {
        return listrik;
    }

    public void setListrik(String listrik) {
        this.listrik = listrik;
    }

    public String getParkiran_motor() {
        return parkiran_motor;
    }

    public void setParkiran_motor(String parkiran_motor) {
        this.parkiran_motor = parkiran_motor;
    }

    public String getParkiran_mobil() {
        return parkiran_mobil;
    }

    public void setParkiran_mobil(String parkiran_mobil) {
        this.parkiran_mobil = parkiran_mobil;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBawa_hewan() {
        return bawa_hewan;
    }

    public void setBawa_hewan(String bawa_hewan) {
        this.bawa_hewan = bawa_hewan;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getSetahun() {
        return setahun;
    }

    public void setSetahun(String setahun) {
        this.setahun = setahun;
    }

    public String getBulanan() {
        return bulanan;
    }

    public void setBulanan(String bulanan) {
        this.bulanan = bulanan;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public String getImage_depan() {
        return image_depan;
    }

    public void setImage_depan(String image_depan) {
        this.image_depan = image_depan;
    }

    public String getImage_luar() {
        return image_luar;
    }

    public void setImage_luar(String image_luar) {
        this.image_luar = image_luar;
    }

    public String getImage_kamar() {
        return image_kamar;
    }

    public void setImage_kamar(String image_kamar) {
        this.image_kamar = image_kamar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(jenis);
        dest.writeString(tanggal_post);
        dest.writeString(nama);
        dest.writeString(alamat_singkat);
        dest.writeString(kampus);
        dest.writeString(gender);
        dest.writeString(kamar_tersisa);
        dest.writeString(ukuran_kamar);
        dest.writeString(kamar_mandi);
        dest.writeString(banyak_kamar_mandi);
        dest.writeString(akses_kunci);
        dest.writeString(wifi);
        dest.writeString(listrik);
        dest.writeString(parkiran_motor);
        dest.writeString(parkiran_mobil);
        dest.writeString(deskripsi);
        dest.writeString(bawa_hewan);
        dest.writeString(ac);
        dest.writeString(harga);
        dest.writeString(setahun);
        dest.writeString(bulanan);
        dest.writeString(image_thumb);
        dest.writeString(image_depan);
        dest.writeString(image_luar);
        dest.writeString(image_kamar);
    }
}
