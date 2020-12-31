package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.interfaces.OportunidadAPI;
import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Oportunidad;
import com.caam.confirming.ui.Ofertante.MenuOfertanteActivity;
import com.caam.confirming.ui.Publicar.TomarFotoActivity;
import com.caam.confirming.ui.login.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuscarOportunidadActivity extends AppCompatActivity {


    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_oportunidad);

        EditText editMonto = (EditText)findViewById(R.id.editMonto);
        Button btnBuscarOportunidad = (Button)findViewById(R.id.btnBuscarOportunidad);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        String [] opcionesMoneda = {"COP", "USD", "EUR"};
        ArrayAdapter<String> adapterMoneda = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opcionesMoneda);
        spinner2.setAdapter(adapterMoneda);

        btnBuscarOportunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String moneda = spinner2.getSelectedItem().toString();
                double monto = Double.parseDouble(editMonto.getText().toString());
                findOportunidad(moneda, monto);


            }
        });


    }


    private void findOportunidad(String moneda, double monto) {
        //System.out.println("streamSize : " + String.valueOf(stream.size()));
       // System.out.println("imageSize in insertArchivo : " + image.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:9090/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        OportunidadAPI oportunidadAPI = retrofit.create(OportunidadAPI.class);

        Call<List<Oportunidad>> call = oportunidadAPI.findOportunidadesService(moneda, monto);
        //Call<InsertResult> call = oportunidadAPI.insertArchivoService2(filePart, "5fda866eec919f180e7a5101");
        call.enqueue(new Callback<List<Oportunidad>>() {
            @Override
            public void onResponse(Call<List<Oportunidad>> call, Response<List<Oportunidad>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Oportunidad> responseService = response.body();
                        Log.i("My App ", responseService.get(0).getId());
                        System.out.println("responseService : " + responseService.get(0).getId());

                        if (responseService.get(0).getId() != null) {
                            Toast.makeText(getApplicationContext(), "Imagen Upload", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(BuscarOportunidadActivity.this, OportunidadesActivity.class);
                            //List<Oportunidad> list = new ArrayList<>();
                            intent.putExtra("listaOportunidades", (Serializable) responseService);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(BuscarOportunidadActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(BuscarOportunidadActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Oportunidad>> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText(BuscarOportunidadActivity.this, "No Response...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}