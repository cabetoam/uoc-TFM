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
import com.caam.confirming.interfaces.FacturaAPI;
import com.caam.confirming.interfaces.OfertanteAPI;
import com.caam.confirming.interfaces.OportunidadAPI;
import com.caam.confirming.interfaces.UserAPI;
import com.caam.confirming.models.Factura;
import com.caam.confirming.models.Ofertante;
import com.caam.confirming.models.Oportunidad;
import com.caam.confirming.models.UserReturn;
import com.caam.confirming.ui.Inversor.BuscarOportunidadActivity;
import com.caam.confirming.ui.Inversor.MenuInversorActivity;
import com.caam.confirming.ui.Inversor.OportunidadesActivity;
import com.caam.confirming.ui.login.LoginActivity;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuOfertanteActivity extends AppCompatActivity {
    String username;
    String monedaTotales;
    int totalFacturasVendidas;
    double totalFacturasCOP, totalRecaudoCOP, totalFacturasUSD, totalRecaudoUSD, totalFacturasEUR, totalRecaudoEUR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ofertante);

        username = getIntent().getStringExtra("username");

        Button btnPublicarFacturas = (Button)findViewById(R.id.btnPublicarFacturas);
        Button btnFacturasVendidas = (Button)findViewById(R.id.btnFacturasVendidas);
        Button btnMiCuentaOfer = (Button)findViewById(R.id.btnMiCuentaOfer);



        btnPublicarFacturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findOfertente(username);
            }
        });

        btnFacturasVendidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findFacturasVendidas(username);
            }
        });

        btnMiCuentaOfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findTotalesVendidas(username);
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


    private void findFacturasVendidas(String username) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:9090/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        FacturaAPI facturaAPI = retrofit.create(FacturaAPI.class);

        Call<List<Factura>> call = facturaAPI.findFacturasVendidasService(username);
        call.enqueue(new Callback<List<Factura>>() {
            @Override
            public void onResponse(Call<List<Factura>> call, Response<List<Factura>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Factura> responseService = response.body();
                        Log.i("My App ", responseService.get(0).getId());
                        System.out.println("responseService : " + responseService.get(0).getId());

                        if (responseService.get(0).getId() != null) {
                            Toast.makeText(getApplicationContext(), "Imagen Upload", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(MenuOfertanteActivity.this, FacturasVendidasActivity.class);
                            intent.putExtra("username", username);
                            intent.putExtra("listaFacturasVendidas", (Serializable) responseService);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(MenuOfertanteActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(MenuOfertanteActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Factura>> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText(MenuOfertanteActivity.this, "No Response...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findTotalesVendidas(String username) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:9090/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        FacturaAPI facturaAPI = retrofit.create(FacturaAPI.class);

        Call<List<Factura>> call = facturaAPI.findFacturasVendidasService(username);
        call.enqueue(new Callback<List<Factura>>() {
            @Override
            public void onResponse(Call<List<Factura>> call, Response<List<Factura>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Factura> responseService = response.body();
                        Log.i("My App ", responseService.get(0).getId());
                        System.out.println("responseService : " + responseService.get(0).getId());

                        if (responseService.get(0).getId() != null) {
                            totalFacturasVendidas = 0;
                            totalFacturasCOP = 0.0;
                            totalRecaudoCOP = 0.0;
                            totalFacturasUSD = 0.0;
                            totalRecaudoUSD = 0.0;
                            totalFacturasEUR = 0.0;
                            totalRecaudoEUR = 0.0;
                            for (int i=0; i < responseService.size(); i++) {
                                totalFacturasVendidas++;
                                monedaTotales = responseService.get(i).getMoneda();
                                switch(monedaTotales) {
                                    case "COP":
                                        totalFacturasCOP = totalFacturasCOP + responseService.get(i).getValor();
                                        totalRecaudoCOP = totalRecaudoCOP + responseService.get(i).getValorVenta();
                                        break;

                                    case "USD":
                                        totalFacturasUSD = totalFacturasUSD + responseService.get(i).getValor();
                                        totalRecaudoUSD = totalRecaudoUSD + responseService.get(i).getValorVenta();
                                        break;

                                    case "EUR":
                                        totalFacturasEUR = totalFacturasEUR + responseService.get(i).getValor();
                                        totalRecaudoEUR = totalRecaudoEUR + responseService.get(i).getValorVenta();
                                        break;
                                }
                            }

                            Toast.makeText(getApplicationContext(), "Imagen Upload", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(MenuOfertanteActivity.this, CuentaOfertanteActivity.class);
                            intent.putExtra("username", username);
                            intent.putExtra("totalesVendidas", totalFacturasVendidas);
                            intent.putExtra("totalFacturasCOP", totalFacturasCOP);
                            intent.putExtra("totalRecaudoCOP", totalRecaudoCOP);
                            intent.putExtra("totalFacturasUSD", totalFacturasUSD);
                            intent.putExtra("totalRecaudoUSD", totalRecaudoUSD);
                            intent.putExtra("totalFacturasEUR", totalFacturasEUR);
                            intent.putExtra("totalRecaudoEUR", totalRecaudoEUR);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(MenuOfertanteActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(MenuOfertanteActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Factura>> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText(MenuOfertanteActivity.this, "No Response...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}