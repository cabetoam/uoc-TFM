package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.interfaces.InversionAPI;
import com.caam.confirming.models.Inversion;
import com.caam.confirming.ui.login.LoginActivity;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuInversorActivity extends AppCompatActivity {
    String username;
    String monedaTotales;
    int totalFacturasCompradas;
    double totalInversionCOP, totalGananciaCOP, totalInversionUSD, totalGananciaUSD, totalInversionEUR, totalGananciaEUR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inversor);

        Button buttonOportunidades = (Button)findViewById(R.id.buttonOportunidades);
        Button btnMisInversiones = (Button)findViewById(R.id.btnMisInversiones);
        Button btnMiCuenta = (Button)findViewById(R.id.btnMiCuentaOfer);

        username = getIntent().getStringExtra("username");

        buttonOportunidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuInversorActivity.this, BuscarOportunidadActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        btnMisInversiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findInversiones(username);
            }
        });

        btnMiCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findInversionesTotales(username);
            }
        });

    }

    private void findInversiones(String username) {
        //System.out.println("streamSize : " + String.valueOf(stream.size()));
        // System.out.println("imageSize in insertArchivo : " + image.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:9090/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        InversionAPI inversionAPI = retrofit.create(InversionAPI.class);

        Call<List<Inversion>> call = inversionAPI.findInversionesService(username);
        call.enqueue(new Callback<List<Inversion>>() {
            @Override
            public void onResponse(Call<List<Inversion>> call, Response<List<Inversion>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Inversion> responseService = response.body();
                        Log.i("My App ", responseService.get(0).getId());
                        System.out.println("responseService : " + responseService.get(0).getId());

                        if (responseService.get(0).getId() != null) {
                            Toast.makeText(getApplicationContext(), "Imagen Upload", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MenuInversorActivity.this, InversionesActivity.class);
                            intent.putExtra("username", username);
                            intent.putExtra("listaInversiones", (Serializable) responseService);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(MenuInversorActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(MenuInversorActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println("Exception : " + ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Inversion>> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText(MenuInversorActivity.this, "No Response...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findInversionesTotales(String username) {
        //System.out.println("streamSize : " + String.valueOf(stream.size()));
        // System.out.println("imageSize in insertArchivo : " + image.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:9090/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        InversionAPI inversionAPI = retrofit.create(InversionAPI.class);

        Call<List<Inversion>> call = inversionAPI.findInversionesService(username);
        call.enqueue(new Callback<List<Inversion>>() {
            @Override
            public void onResponse(Call<List<Inversion>> call, Response<List<Inversion>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Inversion> responseService = response.body();
                        Log.i("My App ", responseService.get(0).getId());
                        System.out.println("responseService : " + responseService.get(0).getId());

                        if (responseService.get(0).getId() != null) {

                            //Totales
                            totalFacturasCompradas = 0;
                            totalInversionCOP = 0.0;
                            totalGananciaCOP = 0.0;
                            totalInversionUSD = 0.0;
                            totalGananciaUSD = 0.0;
                            totalInversionEUR = 0.0;
                            totalGananciaEUR = 0.0;
                            for (int i=0; i < responseService.size(); i++) {
                                totalFacturasCompradas++;
                                monedaTotales = responseService.get(i).getMoneda();
                                switch(monedaTotales) {
                                    case "COP":
                                        totalInversionCOP = totalInversionCOP + responseService.get(i).getValorCompra();
                                        totalGananciaCOP = totalGananciaCOP + responseService.get(i).getGanancia();
                                        break;

                                    case "USD":
                                        totalInversionUSD = totalInversionUSD + responseService.get(i).getValorCompra();
                                        totalGananciaUSD = totalGananciaUSD + responseService.get(i).getGanancia();
                                        break;

                                    case "EUR":
                                        totalInversionEUR = totalInversionEUR + responseService.get(i).getValorCompra();
                                        totalGananciaEUR = totalGananciaEUR + responseService.get(i).getGanancia();
                                        break;
                                }
                            }

                            Toast.makeText(getApplicationContext(), "Imagen Upload", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MenuInversorActivity.this, CuentaInversorActivity.class);
                            intent.putExtra("username", username);
                            intent.putExtra("totalesCompradas", totalFacturasCompradas);
                            intent.putExtra("totalInversionCOP", totalInversionCOP);
                            intent.putExtra("totalGananciaCOP", totalGananciaCOP);
                            intent.putExtra("totalInversionUSD", totalInversionUSD);
                            intent.putExtra("totalGananciaUSD", totalGananciaUSD);
                            intent.putExtra("totalInversionEUR", totalInversionEUR);
                            intent.putExtra("totalGananciaEUR", totalGananciaEUR);

                            //intent.putExtra("listaInversiones", (Serializable) responseService);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(MenuInversorActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(MenuInversorActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println("Exception : " + ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Inversion>> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText(MenuInversorActivity.this, "No Response...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}