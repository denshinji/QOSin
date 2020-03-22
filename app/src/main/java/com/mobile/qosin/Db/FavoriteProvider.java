package com.mobile.qosin.Db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import static com.mobile.qosin.Db.FavoriteContract.AUTHORITY;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.CONTENT_URI;
import static com.mobile.qosin.Db.FavoriteContract.Favorites.TABLE_NAME;

public class FavoriteProvider extends ContentProvider {


    private static final int FAVORITE = 1;
    private static final int FAVORITE_ID = 2;
    private static final UriMatcher sUrimatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUrimatcher.addURI(AUTHORITY, TABLE_NAME, FAVORITE);
        sUrimatcher.addURI(AUTHORITY, TABLE_NAME + "/#", FAVORITE_ID);
    }

    SQLiteDatabase sq;
    private FavoriteDBHelper favoriteDBhelper;


    public FavoriteProvider() {
    }

    @Override
    public boolean onCreate() {
        favoriteDBhelper = FavoriteDBHelper.getInstance(getContext());
        favoriteDBhelper.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        favoriteDBhelper.open();
        Cursor cursors;
        switch (sUrimatcher.match(uri)) {
            case FAVORITE:
                cursors = favoriteDBhelper.queryAll();
                break;
            case FAVORITE_ID:
                cursors = favoriteDBhelper.queryById(uri.getLastPathSegment());
                break;
            default:
                cursors = null;
                break;
        }
        return cursors;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        favoriteDBhelper.getWritableDatabase();
        long added;
        if (sUrimatcher.match(uri) == FAVORITE) {
            added = favoriteDBhelper.insertprovider(values);
        } else {
            added = 0;
        }
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, null);
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        favoriteDBhelper.getWritableDatabase();
        int delete;
        switch (sUrimatcher.match(uri)) {
            case FAVORITE:
                delete = favoriteDBhelper.deleteprovider(uri.getLastPathSegment());
                break;
            default:
                delete = 0;
                break;
        }
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, null);
        return delete;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;

    }
}
