package com.caam.confirming.interfaces;

import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Inversor;
import com.caam.confirming.models.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InversorAPI {

    @POST("addInversor")
    public Call<InsertResult> insertInversorService(@Body Inversor inversor);

    @POST("loginInsert/{email}")
    public Call<InsertResult> insertLoginService(@Body Login login, @Query("email") String email);

    //@GET("validateUser2?log=carlos@gmail.com&contrasena=carloncho")
    /*public Call<String> insertInversorService(@Field("id") int id,
                                              @Field("nombre") String nombre,
                                              @Field("apellido") String apellido,
                                              @Field("email") String email,
                                              @Field("contraseña") String contrasena,
                                              @Field("tipoIdentificacion") String tipoIdentificacion,
                                              @Field("identificacion") String identificacion,
                                              @Field("moneda") String moneda,
                                              @Field("monto") String monto,
                                              @Field("telefono") String telefono);*/
}
