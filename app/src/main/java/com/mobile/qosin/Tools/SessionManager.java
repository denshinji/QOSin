package com.mobile.qosin.Tools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mobile.qosin.Activity.MainActivity;
import com.mobile.qosin.Activity.SplashMenu;

import java.util.HashMap;

public class SessionManager {
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String USERNAME = "USERNAME";
    public static final String NOTELP = "NOTELP";
    public static final String JENIS_AKUN = "JENIS_AKUN";
    public static final String ID = "ID";
    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public SharedPreferences.Editor editor;
    public Context context;
    SharedPreferences sharedPreferences;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String jenis_akun, String name, String email, String username, String notelp, String id) {

        editor.putBoolean(LOGIN, true);
        editor.putString(JENIS_AKUN, jenis_akun);
        editor.putString(NAME, name);
        editor.putString(ID, id);
        editor.putString(EMAIL, email);
        editor.putString(USERNAME, username);
        editor.putString(NOTELP, notelp);
        editor.apply();

    }

    public boolean isLoggin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {

        if (!this.isLoggin()) {
            Intent i = new Intent(context, SplashMenu.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail() {

        HashMap<String, String> user = new HashMap<>();
        user.put(JENIS_AKUN, sharedPreferences.getString(JENIS_AKUN, null));
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(NOTELP, sharedPreferences.getString(NOTELP, null));
        user.put(ID, sharedPreferences.getString(ID, null));
        return user;
    }

    public void logout() {

        editor.clear();
        editor.commit();
        Intent i = new Intent(context, SplashMenu.class);
        context.startActivity(i);

    }


}
