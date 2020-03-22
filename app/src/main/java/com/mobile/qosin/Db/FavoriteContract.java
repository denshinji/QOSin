package com.mobile.qosin.Db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class FavoriteContract {
    public static final String AUTHORITY = "com.example.filmin";
    private static final String SCHEME = "content";

    private FavoriteContract() {
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static final class Favorites implements BaseColumns {

        public static final String TABLE_NAME = "favorited";
        public static final String C_ID = "id";
        public static final String C_JENIS = "jenis";
        public static final String C_TGL = "tgl";
        public static final String C_NAME = "name";
        public static final String C_ALAMAT = "alamat";
        public static final String C_KAMPUS = "kampus";
        public static final String C_GENDER = "gender";
        public static final String C_KAMAR_TERSISA = "kamar_sisa";
        public static final String C_UKURAN_KAMAR = "ukuran_kamar";
        public static final String C_KAMAR_MANDI = "kamar_mandi";
        public static final String C_BANYAK_KAMAR_MANDI = "b_kamar_mandi";
        public static final String C_AKSES = "akses";
        public static final String C_WIFI = "wifi";
        public static final String C_LISTRIK = "listrik";
        public static final String C_P_MOTOR = "p_motor";
        public static final String C_P_MOBIL = "p_mobil";
        public static final String C_DESKRIPSI = "deskripsi";
        public static final String C_BAWA_HEWAN = "bawa_hewan";
        public static final String C_AC = "ac";
        public static final String C__SEBULAN = "harga";
        public static final String C__SETAHUN = "setahun";
        public static final String C__BULANAN = "bulanan";
        public static final String C__COVER = "cover";
        public static final String C__DEPAN = "depan";
        public static final String C__LUAR = "luar";
        public static final String C__KAMAR = "kamar";


        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

    }

}