package com.mobile.qosin.Model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Item implements Parcelable{

    public Item() {

    }

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

    protected Item(Parcel in) {
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

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

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

    @SerializedName("foto_luar")
    private String image_depan;
    @SerializedName("foto_kamar")
    private String image_luar;
    @SerializedName("foto_kamar_mandi")
    private String image_kamar;


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
