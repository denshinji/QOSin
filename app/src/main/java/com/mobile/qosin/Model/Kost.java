package com.mobile.qosin.Model;

import com.google.gson.annotations.SerializedName;

public class Kost {

    @SerializedName("id_content")
    private int id;
    @SerializedName("judul_item")
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

}
