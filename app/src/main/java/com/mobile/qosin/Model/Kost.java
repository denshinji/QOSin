package com.mobile.qosin.Model;

import com.google.gson.annotations.SerializedName;

public class Kost {

    @SerializedName("id")
    private int id;
    @SerializedName("nama")
    private String nama;

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

    @SerializedName("alamat_lengkap")
    private String alamat_engkap;
    @SerializedName("alamat_singkat")
    private String alamat_singkat;
    @SerializedName("jenis")
    private String jenis;
    @SerializedName("harga")
    private int harga;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("gender")
    private String gender;
    @SerializedName("view")
    private int view;
    @SerializedName("tanggal_post")
    private String tanggal_post;
    @SerializedName("image_thumb")
    private String image_thumb;
    @SerializedName("image_depan")
    private String image_depan;
    @SerializedName("image_luar")
    private String image_luar;
    @SerializedName("image_kamar")
    private String image_kamar;
    @SerializedName("image_wc")
    private String image_wc;

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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
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

    public String getImage_wc() {
        return image_wc;
    }

    public void setImage_wc(String image_wc) {
        this.image_wc = image_wc;
    }
}
