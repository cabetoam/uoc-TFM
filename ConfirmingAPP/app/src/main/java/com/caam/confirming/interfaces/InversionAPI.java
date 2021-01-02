package com.caam.confirming.interfaces;

import com.caam.confirming.models.Factura;
import com.caam.confirming.models.IdFactura;
import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Inversion;
import com.caam.confirming.models.Login;
import com.caam.confirming.models.Oportunidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InversionAPI {

    @POST("addInversion")
    public Call<InsertResult> insertInversionService(@Body Inversion inversion);

    @POST("inversionUpdate")
   //public Call<InsertResult> updateFacturaStatus(@Query("id") String id);
    public Call<InsertResult> updateFacturaStatus(@Body IdFactura idFactura);

    @POST("findInversiones")
    public Call<List<Inversion>> findInversionesService(@Query("username") String username);

}
