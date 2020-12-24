package com.caam.confirming.interfaces;

import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Ofertante;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ArchivoAPI {

    @POST("addArchivo")
    public Call<InsertResult> insertArchivoService(@Body byte[] stream);

    @Multipart
    @POST("addArchivo2")
    public Call<InsertResult> insertArchivoService2(@Part MultipartBody.Part filePart, @Query("id") String idFactura);
}
