package com.mobile.qosin.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Kontrakan implements Parcelable {

    public static final Creator<Kontrakan> CREATOR = new Creator<Kontrakan>() {
        @Override
        public Kontrakan createFromParcel(Parcel in) {
            return new Kontrakan(in);
        }

        @Override
        public Kontrakan[] newArray(int size) {
            return new Kontrakan[size];
        }
    };
    @SerializedName("id_content")
    private int id;
    @SerializedName("judul_item")
    private String nama;
    @SerializedName("kampus")
    private String kampus;
    @SerializedName("wilayah")
    private String alamat_singkat;
    @SerializedName("tipe_content")
    private String jenis;
    @SerializedName("jumlah_kamar")
    private String jumlah_kamar;
    @SerializedName("luas_kamar")
    private String luas_kamar;
    @SerializedName("banyak_kamar_mandi")
    private String banyak_kamar_mandi;
    @SerializedName("listrik")
    private String listrik;
    @SerializedName("parkiran_motor")
    private String parkiran_motor;
    @SerializedName("parkiran_mobil")
    private String parkiran_mobil;
    @SerializedName("sat_tahun")
    private String harga;
    @SerializedName("fasilitas tambahan")
    private String deskripsi;
    @SerializedName("view")
    private int view;
    @SerializedName("tanggal_post")
    private String tanggal_post;
    @SerializedName("foto_cover")
    private String image_thumb;
    @SerializedName("foto_luar")
    private String image_depan;
    @SerializedName("foto_kamar")
    private String image_luar;
    @SerializedName("foto_kamar_mandi")
    private String image_kamar;

    public Kontrakan() {

    }

    protected Kontrakan(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        kampus = in.readString();
        alamat_singkat = in.readString();
        jenis = in.readString();
        jumlah_kamar = in.readString();
        luas_kamar = in.readString();
        banyak_kamar_mandi = in.readString();
        listrik = in.readString();
        parkiran_motor = in.readString();
        parkiran_mobil = in.readString();
        harga = in.readString();
        deskripsi = in.readString();
        view = in.readInt();
        tanggal_post = in.readString();
        image_thumb = in.readString();
        image_depan = in.readString();
        image_luar = in.readString();
        image_kamar = in.readString();
    }

    public String getKampus() {
        return kampus;
    }

    public void setKampus(String kampus) {
        this.kampus = kampus;
    }

    public String getAlamat_singkat() {
        return alamat_singkat;
    }

    public void setAlamat_singkat(String alamat_singkat) {
        this.alamat_singkat = alamat_singkat;
    }

    public String getJumlah_kamar() {
        return jumlah_kamar;
    }

    public void setJumlah_kamar(String jumlah_kamar) {
        this.jumlah_kamar = jumlah_kamar;
    }

    public String getLuas_kamar() {
        return luas_kamar;
    }

    public void setLuas_kamar(String luas_kamar) {
        this.luas_kamar = luas_kamar;
    }

    public String getBanyak_kamar_mandi() {
        return banyak_kamar_mandi;
    }

    public void setBanyak_kamar_mandi(String banyak_kamar_mandi) {
        this.banyak_kamar_mandi = banyak_kamar_mandi;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getTanggal_post() {
        return tanggal_post;
    }

    public void setTanggal_post(String tanggal_post) {
        this.tanggal_post = tanggal_post;
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
        dest.writeString(nama);
        dest.writeString(kampus);
        dest.writeString(alamat_singkat);
        dest.writeString(jenis);
        dest.writeString(jumlah_kamar);
        dest.writeString(luas_kamar);
        dest.writeString(banyak_kamar_mandi);
        dest.writeString(listrik);
        dest.writeString(parkiran_motor);
        dest.writeString(parkiran_mobil);
        dest.writeString(harga);
        dest.writeString(deskripsi);
        dest.writeInt(view);
        dest.writeString(tanggal_post);
        dest.writeString(image_thumb);
        dest.writeString(image_depan);
        dest.writeString(image_luar);
        dest.writeString(image_kamar);
    }
}
