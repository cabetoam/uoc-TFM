package com.caam.confirming.ui.Ofertante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.interfaces.OfertanteAPI;
import com.caam.confirming.interfaces.UserAPI;
import com.caam.confirming.models.Ofertante;
import com.caam.confirming.models.Oportunidad;
import com.caam.confirming.models.UserReturn;
import com.caam.confirming.ui.Inversor.MenuInversorActivity;
import com.caam.confirming.ui.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuOfertanteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ofertante);

        Button btnPublicarFacturas = (Button)findViewById(R.id.btnPublicarFacturas);
        Button btnFacturasVendidas = (Button)findViewById(R.id.btnFacturasVendidas);
        String username = getIntent().getStringExtra("username");

        btnPublicarFacturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findOfertente(username);
            }
        });
    }


    private void findOfertente(String username) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:6060/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        OfertanteAPI ofertanteAPI = retrofit.create(OfertanteAPI.class);
        Call<List<Ofertante>> call = ofertanteAPI.findOfertante(username);
        System.out.println("Datos enviados : " + username);
        call.enqueue(new Callback<List<Ofertante>>() {
            @Override
            public void onResponse(Call<List<Ofertante>> call, Response<List<Ofertante>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Ofertante> responseService = response.body();
                        String nombreOfertante = responseService.get(0).getNombre();
                        int idOfertante = responseService.get(0).getId();
                        System.out.println("idOfertante :" + responseService.get(0).getId());
                        Intent intent = new Intent(MenuOfertanteActivity.this, PublicarOportunidadActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("nombreOfertante", nombreOfertante);
                        intent.putExtra("idOfertante", idOfertante);
                        startActivity(intent);
                    }
                } catch (Exception ex) {
                    Toast.makeText(MenuOfertanteActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ofertante>> call, Throwable t) {
                Toast.makeText(MenuOfertanteActivity.this, "No Response...", Toast.LENGTH_SHORT).show();
                System.out.println(t.toString());
            }
        });
    }
}