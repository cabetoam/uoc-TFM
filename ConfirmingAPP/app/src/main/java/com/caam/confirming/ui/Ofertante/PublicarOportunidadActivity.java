package com.caam.confirming.ui.Ofertante;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.interfaces.FacturaAPI;
import com.caam.confirming.interfaces.OfertanteAPI;
import com.caam.confirming.models.Factura;
import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Ofertante;
import com.caam.confirming.ui.Publicar.TomarFotoActivity;
import com.caam.confirming.ui.Registro.RegistroOfertanteActivity;
import com.caam.confirming.ui.login.LoginActivity;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PublicarOportunidadActivity extends AppCompatActivity {

    private int day, month, year;
    Spinner spinner1;
    Factura factura = new Factura();
    Button btnSubirArchivo;
    Button btnPublicarOferta;
    int idOfertante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_oportunidad);

        btnPublicarOferta = (Button)findViewById(R.id.btnPublicarOferta);
        btnSubirArchivo = (Button)findViewById(R.id.btnSubirArchivo);
        btnSubirArchivo.setEnabled(false);

        TextView valorNombreOfertante = (TextView)findViewById(R.id.valorNombreOfertante);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        String [] opciones = {"COP", "USD", "EUR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);
        TextView editMonto = (TextView)findViewById(R.id.editMonto);
        TextView editFechaCobro = (TextView)findViewById(R.id.editFechaCobro);
        TextView editPlazo = (TextView)findViewById(R.id.editPlazo);
        TextView editValorVenta = (TextView)findViewById(R.id.editValorVenta);
        TextView editDetalles = (TextView)findViewById(R.id.editDetalles);

        valorNombreOfertante.setText(getIntent().getStringExtra("nombreOfertante"));

        System.out.println("idOfertante :" + getIntent().getIntExtra("idOfertante", 0));
        idOfertante = getIntent().getIntExtra("idOfertante", 0);

        Button btnSelectFecha = (Button)findViewById(R.id.btnSelectFecha);
        btnSelectFecha.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View view) {
            if (view == btnSelectFecha) {
                final Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(PublicarOportunidadActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editFechaCobro.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        }
    });


    btnPublicarOferta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            factura.setId("id");
            factura.setNombre(getIntent().getStringExtra("nombreOfertante"));
            factura.setMoneda(spinner1.getSelectedItem().toString());
            factura.setValor(Double.parseDouble(editMonto.getText().toString()));
            factura.setValorVenta(Double.parseDouble(editValorVenta.getText().toString()));
            factura.setFechaCobro(editFechaCobro.getText().toString());
            factura.setPlazoPago(editPlazo.getText().toString());
            factura.setDetalle(editDetalles.getText().toString());
            factura.setStatus("disponible");
            factura.setUsername(getIntent().getStringExtra("username"));
            factura.setIdOfertante(idOfertante);

            insertFactura(factura);
        }
    });

        btnSubirArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PublicarOportunidadActivity.this, TomarFotoActivity.class);
                startActivity(intent);
            }
        });

    }


    private void insertFactura(Factura factura) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:9090/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        FacturaAPI facturaAPI = retrofit.create(FacturaAPI.class);
        Call<InsertResult> call = facturaAPI.insertFacturaService(factura);
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
                            Toast.makeText(PublicarOportunidadActivity.this, "Factura creada...", Toast.LENGTH_LONG).show();
                            btnPublicarOferta.setEnabled(false);
                            btnSubirArchivo.setEnabled(true);
                        } else {
                            Intent intent = new Intent(PublicarOportunidadActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                        //System.out.println(u.isUser().);
                    }
                } catch (Exception ex) {
                    Toast.makeText(PublicarOportunidadActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText(PublicarOportunidadActivity.this, "No Response...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}