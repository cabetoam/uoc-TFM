package com.caam.confirming.interfaces;

import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Inversor;
import com.caam.confirming.models.Login;
import com.caam.confirming.models.Ofertante;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OfertanteAPI {

    @POST("addOfertante")
    public Call<InsertResult> insertOfertanteService(@Body Ofertante ofertante);

    @POST("loginInsert/{email}")
    public Call<InsertResult> insertLoginService(@Body Login login, @Query("email") String email);
}
