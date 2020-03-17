package com.mobile.qosin.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Kost implements Parcelable {

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
    @SerializedName("id_content")
    private int id;
    @SerializedName("judul_item")
    private String nama;
    @SerializedName("kampus")
    private String kampus;
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
    @SerializedName("luas_kamar")
    private String ukuran_kamar;
    @SerializedName("kamar_mandi")
    private String kamar_mandi;
    @SerializedName("waktu_kunci")
    private String akses_kunci;
    @SerializedName("jumlah_kamar")
    private String kamar_tersisa;
    @SerializedName("ac")
    private String ac;
    @SerializedName("listrik")
    private String listrik;
    @SerializedName("wifi")
    private String wifi;
    @SerializedName("bawa_hewan")
    private String bawa_hewan;
    @SerializedName("parkiran_motor")
    private String parkiran_motor;
    @SerializedName("parkiran_mobil")
    private String parkiran_mobil;
    @SerializedName("fasilitas_tambahan")
    private String tambahan;
    @SerializedName("minimal_bulan")
    private String bulanan;
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

    public Kost() {

    }

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
        ukuran_kamar = in.readString();
        kamar_mandi = in.readString();
        akses_kunci = in.readString();
        kamar_tersisa = in.readString();
        ac = in.readString();
        listrik = in.readString();
        wifi = in.readString();
        bawa_hewan = in.readString();
        parkiran_motor = in.readString();
        parkiran_mobil = in.readString();
        tambahan = in.readString();
        bulanan = in.readString();
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

    public String getAkses_kunci() {
        return akses_kunci;
    }

    public void setAkses_kunci(String akses_kunci) {
        this.akses_kunci = akses_kunci;
    }

    public String getKamar_tersisa() {
        return kamar_tersisa;
    }

    public void setKamar_tersisa(String kamar_tersisa) {
        this.kamar_tersisa = kamar_tersisa;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getListrik() {
        return listrik;
    }

    public void setListrik(String listrik) {
        this.listrik = listrik;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getBawa_hewan() {
        return bawa_hewan;
    }

    public void setBawa_hewan(String bawa_hewan) {
        this.bawa_hewan = bawa_hewan;
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

    public String getTambahan() {
        return tambahan;
    }

    public void setTambahan(String tambahan) {
        this.tambahan = tambahan;
    }

    public String getBulanan() {
        return bulanan;
    }

    public void setBulanan(String bulanan) {
        this.bulanan = bulanan;
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
        dest.writeString(ukuran_kamar);
        dest.writeString(kamar_mandi);
        dest.writeString(akses_kunci);
        dest.writeString(kamar_tersisa);
        dest.writeString(ac);
        dest.writeString(listrik);
        dest.writeString(wifi);
        dest.writeString(bawa_hewan);
        dest.writeString(parkiran_motor);
        dest.writeString(parkiran_mobil);
        dest.writeString(tambahan);
        dest.writeString(bulanan);
        dest.writeString(tanggal_post);
        dest.writeString(image_thumb);
        dest.writeString(image_depan);
        dest.writeString(image_luar);
        dest.writeString(image_kamar);
    }
}
