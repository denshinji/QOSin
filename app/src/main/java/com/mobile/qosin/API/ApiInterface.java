package com.mobile.qosin.API;

import com.mobile.qosin.Model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by haerul on 15/03/18.
 */

public interface ApiInterface {

    @POST("data_pria.php")
    Call<List<Item>> getPets();

    @POST("data_perempuan.php")
    Call<List<Item>> get_kost_wanita();

    @POST("data_kontrakan.php")
    Call<List<Item>> get_kontrakan();

    @POST("data.php")
    Call<List<Item>> get_all();

    @FormUrlEncoded
    @POST("data_campus.php")
    Call<List<Item>> get_itemcampus(@Field("campus") String campus);


}
