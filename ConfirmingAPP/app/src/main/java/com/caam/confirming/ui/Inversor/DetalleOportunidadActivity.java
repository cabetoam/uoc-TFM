package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.interfaces.InversionAPI;
import com.caam.confirming.interfaces.OportunidadAPI;
import com.caam.confirming.models.IdFactura;
import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Inversion;
import com.caam.confirming.models.Oportunidad;
import com.caam.confirming.ui.Ofertante.PublicarOportunidadActivity;
import com.caam.confirming.ui.Registro.RegistroInversorActivity;
import com.caam.confirming.ui.login.LoginActivity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleOportunidadActivity extends AppCompatActivity {

    String usernameSesion;
    Double valorVenta;
    Double ganancia;
    TextView mensajeConfirmarCompra;
    Button btnFinalizarCompra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_oportunidad);

        usernameSesion = getIntent().getStringExtra("usernameSesion");

        List<Oportunidad> lista = (List<Oportunidad>)getIntent().getSerializableExtra("listaOportunidades");
        TextView valueNombreOfertante = (TextView)findViewById(R.id.valueNombreOfertante);
        TextView valueMoneda = (TextView)findViewById(R.id.valueMoneda);
        TextView valueValorFactura = (TextView)findViewById(R.id.valueValorFactura);
        TextView valueFechaCobro = (TextView)findViewById(R.id.valueFechaCobro);
        TextView valuePlazo = (TextView)findViewById(R.id.valuePlazo);
        TextView valueValorVenta = (TextView)findViewById(R.id.valueValorVenta);
        TextView valueGanancia = (TextView)findViewById(R.id.valueGanancia);
        TextView valueDetalles = (TextView)findViewById(R.id.valueDetalles);

        Button btnRegresar = (Button)findViewById(R.id.btnRegresar);
        Button btnComprar = (Button)findViewById(R.id.btnComprar);
        btnFinalizarCompra = (Button)findViewById(R.id.btnFinalizarCompra);
        mensajeConfirmarCompra = (TextView)findViewById(R.id.mensajeConfirmarCompra);
        mensajeConfirmarCompra.setEnabled(false);
        btnFinalizarCompra.setEnabled(false);

        valueNombreOfertante.setText(getIntent().getStringExtra("nombre"));
        valueMoneda.setText(getIntent().getStringExtra("moneda"));
        Double valor = getIntent().getDoubleExtra("valor", 0.0);
        valueValorFactura.setText(formatearDouble(valor));
        valueFechaCobro.setText(getIntent().getStringExtra("fechaCobro"));
        valuePlazo.setText(getIntent().getStringExtra("plazo") + " dias");
        valorVenta = getIntent().getDoubleExtra("valorVenta", 0.0);
        valueValorVenta.setText(formatearDouble(valorVenta));
        ganancia = valor - valorVenta;
        //inversion.setGanancia(ganancia);
        valueGanancia.setText(formatearDouble(ganancia));
        valueDetalles.setText(getIntent().getStringExtra("detalle"));

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleOportunidadActivity.this, OportunidadesActivity.class);
                intent.putExtra("listaOportunidades", (Serializable) lista);
                startActivity(intent);
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensajeConfirmarCompra.setEnabled(true);
                mensajeConfirmarCompra.setText("Ha seleccionado la opción comprar. Si desea finalizar el proceso de compra de esta factura, seleccione Finalizar Factura.");
                btnFinalizarCompra.setEnabled(true);
            }
        });

        btnFinalizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inversion inversion = new Inversion();
                inversion.setId("");
                inversion.setNombreOfertante(getIntent().getStringExtra("nombre"));
                inversion.setMoneda(getIntent().getStringExtra("moneda"));
                inversion.setValorCompra(valorVenta);
                inversion.setGanancia(ganancia);
                inversion.setPlazoPago(getIntent().getStringExtra("plazo"));
                Date fecha = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formatDate = dateFormat.format(fecha.getTime());
                inversion.setFechaCompra(formatDate);
                inversion.setUsernameComprador(usernameSesion);
                inversion.setIdFactura(getIntent().getStringExtra("id"));

                insertInversion(inversion);
            }
        });

    }

    private String formatearDouble(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(Locale.US);
        return formato.format(valor);
    }

    private void insertInversion(Inversion inversion) {
        //System.out.println("streamSize : " + String.valueOf(stream.size()));
        // System.out.println("imageSize in insertArchivo : " + image.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:9090/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        InversionAPI inversionAPI = retrofit.create(InversionAPI.class);

        Call<InsertResult> call = inversionAPI.insertInversionService(inversion);
        //Call<InsertResult> call = oportunidadAPI.insertArchivoService2(filePart, "5fda866eec919f180e7a5101");
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                try {
                    if (response.isSuccessful()) {
                        InsertResult responseService = response.body();
                        Log.i("My App ", responseService.getInsertR());
                        System.out.println("responseService : " + responseService.getInsertR());
                        //Toast.makeText(getApplicationContext(), responseService, Toast.LENGTH_LONG).show();
                        if (responseService.getInsertR().equals("Ok")) {
                            Toast.makeText(DetalleOportunidadActivity.this, "Oportunidad comprada...", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(DetalleOportunidadActivity.this, MenuInversorActivity.class);
                            intent.putExtra("username", usernameSesion);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(DetalleOportunidadActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(DetalleOportunidadActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText(DetalleOportunidadActivity.this, "No Response...", Toast.LENGTH_SHORT).show();
            }
        });

        System.out.println("data To MQ " + getIntent().getStringExtra("id"));
        String idFacturaVendida = getIntent().getStringExtra("id");
        IdFactura idFactura = new IdFactura();
        idFactura.setIdFactura(idFacturaVendida);

        Call<InsertResult> callServiceMQ = inversionAPI.updateFacturaStatus(idFactura);
        callServiceMQ.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> callServiceMQ, Response<InsertResult> response) {
                try {
                    if (response.isSuccessful()) {
                        InsertResult responseMQService = response.body();
                        Log.i("My App to MQ", responseMQService.getInsertR());
                    }
                } catch (Exception ex) {
                    Toast.makeText(DetalleOportunidadActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<InsertResult> callServiceMQ, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText( DetalleOportunidadActivity.this,  "No Response...", Toast.LENGTH_SHORT).show();
            }
        });
    }



}