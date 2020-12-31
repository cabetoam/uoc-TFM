package com.caam.confirming.interfaces;

import com.caam.confirming.models.UserReturn;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserAPI {

    //@GET("validateUser2?")
    //@GET("validateUser2?log=carlos@gmail.com&contrasena=carloncho")
    @GET("validateUser?")
    public Call<UserReturn> find(@Query("log") String login, @Query("contrasena") String contrasena);
}
