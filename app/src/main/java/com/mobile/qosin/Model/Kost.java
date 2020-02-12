package com.mobile.qosin.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Kost implements Parcelable {

    public Kost (){
        
    }

    @SerializedName("id_content")
    private int id;
    @SerializedName("judul_item")
    private String nama;

    protected Kost(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        kampus = in.readString();
        alamat_engkap = in.readString();
        alamat_singkat = in.readString();
        jenis = in.readString();
        harga = in.readString();
        deskripsi = in.readString();
        gender = in.readString();
        view = in.readInt();
        tanggal_post = in.readString();
        image_thumb = in.readString();
        image_depan = in.readString();
        image_luar = in.readString();
        image_kamar = in.readString();
    }

    public static final Creator<Kost> CREATOR = new Creator<Kost>() {
        @Override
        public Kost createFromParcel(Parcel in) {
            return new Kost(in);
        }

        @Override
        public Kost[] newArray(int size) {
            return new Kost[size];
        }
    };

    public String getKampus() {
        return kampus;
    }

    public void setKampus(String kampus) {
        this.kampus = kampus;
    }

    @SerializedName("kampus")
    private String kampus;

    public String getAlamat_engkap() {
        return alamat_engkap;
    }

    public void setAlamat_engkap(String alamat_engkap) {
        this.alamat_engkap = alamat_engkap;
    }

    public String getAlamat_singkat() {
        return alamat_singkat;
    }

    public void setAlamat_singkat(String alamat_singkat) {
        this.alamat_singkat = alamat_singkat;
    }

    @SerializedName("alamat")
    private String alamat_engkap;
    @SerializedName("wilayah")
    private String alamat_singkat;
    @SerializedName("tipe_content")
    private String jenis;
    @SerializedName("bulanan")
    private String harga;
    @SerializedName("fasilitas tambahan")
    private String deskripsi;
    @SerializedName("tipe")
    private String gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        dest.writeString(alamat_engkap);
        dest.writeString(alamat_singkat);
        dest.writeString(jenis);
        dest.writeString(harga);
        dest.writeString(deskripsi);
        dest.writeString(gender);
        dest.writeInt(view);
        dest.writeString(tanggal_post);
        dest.writeString(image_thumb);
        dest.writeString(image_depan);
        dest.writeString(image_luar);
        dest.writeString(image_kamar);
    }
}
