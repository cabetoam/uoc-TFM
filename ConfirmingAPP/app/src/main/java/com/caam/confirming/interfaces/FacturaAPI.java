package com.caam.confirming.interfaces;

import com.caam.confirming.models.Factura;
import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Ofertante;
import com.caam.confirming.models.Oportunidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FacturaAPI {

    @POST("findFactura")
    public Call<Factura> findOportunidadesService(@Query("id") String id);

    @POST("addFactura")
    public Call<InsertResult> insertFacturaService(@Body Factura factura);

    @POST("findFacturasVendidas")
    public Call<List<Factura>> findFacturasVendidasService(@Query("username") String username);
}
