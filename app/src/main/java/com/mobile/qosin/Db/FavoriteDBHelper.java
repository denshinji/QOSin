package com.mobile.qosin.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobile.qosin.Model.Favorite;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_ID;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.C_NAME;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.TABLE_NAME;

public class FavoriteDBHelper extends SQLiteOpenHelper {
    public static final String LOGTAG = "FAVORITE";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "favoritedb";
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static FavoriteDBHelper INSTANCE;
    SQLiteDatabase db;


    public FavoriteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    public static FavoriteDBHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteDBHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        Log.i(LOGTAG, "Database Close");
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                FavoriteContract.Favorites._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                FavoriteContract.Favorites.C_ID + " INTEGER," +
                FavoriteContract.Favorites.C_NAME + " TEXT," +
                FavoriteContract.Favorites.C_JENIS + " TEXT," +
                FavoriteContract.Favorites.C_TGL + " TEXT," +
                FavoriteContract.Favorites.C_KAMPUS + " TEXT," +
                FavoriteContract.Favorites.C_ALAMAT + " TEXT," +
                FavoriteContract.Favorites.C_GENDER + " TEXT," +
                FavoriteContract.Favorites.C_KAMAR_TERSISA + " TEXT," +
                FavoriteContract.Favorites.C_UKURAN_KAMAR + " TEXT," +
                FavoriteContract.Favorites.C_KAMAR_MANDI + " TEXT," +
                FavoriteContract.Favorites.C_BANYAK_KAMAR_MANDI + " TEXT," +
                FavoriteContract.Favorites.C_AKSES + " TEXT," +
                FavoriteContract.Favorites.C_WIFI + " TEXT," +
                FavoriteContract.Favorites.C_LISTRIK + " TEXT," +
                FavoriteContract.Favorites.C_P_MOTOR + " TEXT," +
                FavoriteContract.Favorites.C_P_MOBIL + " TEXT," +
                FavoriteContract.Favorites.C_DESKRIPSI + " TEXT," +
                FavoriteContract.Favorites.C_BAWA_HEWAN + " TEXT," +
                FavoriteContract.Favorites.C_AC + " TEXT," +
                FavoriteContract.Favorites.C__SEBULAN + " TEXT," +
                FavoriteContract.Favorites.C__SETAHUN + " TEXT," +
                FavoriteContract.Favorites.C__BULANAN + " TEXT," +
                FavoriteContract.Favorites.C__COVER + " TEXT," +
                FavoriteContract.Favorites.C__DEPAN + " TEXT," +
                FavoriteContract.Favorites.C__LUAR + " TEXT," +
                FavoriteContract.Favorites.C__KAMAR + " TEXT" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addFavorite(Favorite favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FavoriteContract.Favorites.C_ID, favorite.getId());
        values.put(FavoriteContract.Favorites.C_JENIS, favorite.getJenis());
        values.put(FavoriteContract.Favorites.C_TGL, favorite.getTanggal_post());
        values.put(FavoriteContract.Favorites.C_NAME, favorite.getNama());
        values.put(FavoriteContract.Favorites.C_ALAMAT, favorite.getAlamat_singkat());
        values.put(FavoriteContract.Favorites.C_KAMPUS, favorite.getKampus());
        values.put(FavoriteContract.Favorites.C_GENDER, favorite.getGender());
        values.put(FavoriteContract.Favorites.C_KAMAR_TERSISA, favorite.getKamar_tersisa());
        values.put(FavoriteContract.Favorites.C_UKURAN_KAMAR, favorite.getUkuran_kamar());
        values.put(FavoriteContract.Favorites.C_KAMAR_MANDI, favorite.getKamar_mandi());
        values.put(FavoriteContract.Favorites.C_BANYAK_KAMAR_MANDI, favorite.getBanyak_kamar_mandi());
        values.put(FavoriteContract.Favorites.C_AKSES, favorite.getAkses_kunci());
        values.put(FavoriteContract.Favorites.C_WIFI, favorite.getWifi());
        values.put(FavoriteContract.Favorites.C_LISTRIK, favorite.getListrik());
        values.put(FavoriteContract.Favorites.C_P_MOTOR, favorite.getParkiran_motor());
        values.put(FavoriteContract.Favorites.C_P_MOBIL, favorite.getParkiran_mobil());
        values.put(FavoriteContract.Favorites.C_DESKRIPSI, favorite.getDeskripsi());
        values.put(FavoriteContract.Favorites.C_BAWA_HEWAN, favorite.getBawa_hewan());
        values.put(FavoriteContract.Favorites.C_AC, favorite.getAc());
        values.put(FavoriteContract.Favorites.C__SEBULAN, favorite.getHarga());
        values.put(FavoriteContract.Favorites.C__SETAHUN, favorite.getSetahun());
        values.put(FavoriteContract.Favorites.C__BULANAN, favorite.getBulanan());
        values.put(FavoriteContract.Favorites.C__COVER, favorite.getImage_thumb());
        values.put(FavoriteContract.Favorites.C__DEPAN, favorite.getImage_depan());
        values.put(FavoriteContract.Favorites.C__LUAR, favorite.getImage_luar());
        values.put(FavoriteContract.Favorites.C__KAMAR, favorite.getImage_kamar());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteFavorite(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_NAME + " WHERE " + C_NAME + "= '" + id + "'");
        db.close();

    }


    public List<Favorite> getAllFavorite() {
        String[] columns = {
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
        String sortOrder =
                FavoriteContract.Favorites._ID + " ASC";
        List<Favorite> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Favorite favorite = new Favorite();
                favorite.setId(cursor.getInt(cursor.getColumnIndex(FavoriteContract.Favorites.C_ID)));
                favorite.setJenis(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_JENIS)));
                favorite.setTanggal_post(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_TGL)));
                favorite.setNama(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_NAME)));
                favorite.setAlamat_singkat(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_ALAMAT)));
                favorite.setKampus(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_KAMPUS)));
                favorite.setGender(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_GENDER)));
                favorite.setKamar_tersisa(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_KAMAR_TERSISA)));
                favorite.setUkuran_kamar(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_UKURAN_KAMAR)));
                favorite.setKamar_mandi(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_KAMAR_MANDI)));
                favorite.setBanyak_kamar_mandi(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_BANYAK_KAMAR_MANDI)));
                favorite.setAkses_kunci(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_AKSES)));
                favorite.setWifi(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_WIFI)));
                favorite.setListrik(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_LISTRIK)));
                favorite.setParkiran_motor(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_P_MOTOR)));
                favorite.setParkiran_mobil(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_P_MOBIL)));
                favorite.setDeskripsi(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_DESKRIPSI)));
                favorite.setBawa_hewan(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_BAWA_HEWAN)));
                favorite.setAc(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C_AC)));
                favorite.setHarga(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C__SEBULAN)));
                favorite.setSetahun(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C__SETAHUN)));
                favorite.setBulanan(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C__BULANAN)));
                favorite.setImage_thumb(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C__COVER)));
                favorite.setImage_depan(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C__DEPAN)));
                favorite.setImage_luar(cursor.getString(cursor.getColumnIndex(FavoriteContract.Favorites.C__LUAR)));

                favoriteList.add(favorite);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return favoriteList;
    }


    public Cursor queryAll() {
        return db.query(DATABASE_TABLE
                , null
                , null
                , null,
                null,
                null
                , C_ID + " ASC");
    }

    public Cursor queryById(String id) {
        return db.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public long insertprovider(ContentValues contentValues) {
        return db.insert(DATABASE_TABLE, null, contentValues);
    }

    public int deleteprovider(String title) {
        return db.delete(DATABASE_TABLE, C_ID + "= ?", new String[]{title});
    }
}