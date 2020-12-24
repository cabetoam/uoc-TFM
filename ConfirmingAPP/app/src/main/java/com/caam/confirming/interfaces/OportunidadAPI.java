package com.caam.confirming.interfaces;

import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Oportunidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OportunidadAPI {

    @POST("findOportunidades")
    public Call<List<Oportunidad>> findOportunidadesService(@Query("moneda") String moneda, @Query("monto") double monto);
}
