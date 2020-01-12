package com.mobile.qosin.API;

import com.mobile.qosin.Model.Kost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by haerul on 15/03/18.
 */

public interface ApiInterface {

    @POST("get_kost_pria.php")
    Call<List<Kost>> getPets();

}
