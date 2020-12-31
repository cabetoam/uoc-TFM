package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.interfaces.OportunidadAPI;
import com.caam.confirming.models.Oportunidad;
import com.caam.confirming.ui.login.LoginActivity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OportunidadesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oportunidades);

        ArrayList<String> listaToShow = new ArrayList<String>();
        ListView idListaInversiones = (ListView)findViewById(R.id.idListaInversiones);

        List<Oportunidad> lista = (List<Oportunidad>)getIntent().getSerializableExtra("listaOportunidades");
        //System.out.println("lista : " + lista.get(0).getId());

        double valorToShow = 0.0;
        for (int i = 0; i < lista.size(); i++) {
            listaToShow.add("Valor: " + formatearDouble(lista.get(i).getValor()) + "\n" + "Plazo: " + lista.get(i).getPlazoPago() + "\n");
        }

        ArrayAdapter adapter = new ArrayAdapter(this,  android.R.layout.simple_selectable_list_item, listaToShow);
        idListaInversiones.setAdapter(adapter);
        idListaInversiones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("id select : " + i);
                System.out.println(lista.get(i).getNombre());
                System.out.println(lista.get(i).getFechaCobro());
                System.out.println(lista.get(i).getMoneda());
                System.out.println(lista.get(i).getValor());
                System.out.println(lista.get(i).getPlazoPago());

                Intent intent = new Intent(OportunidadesActivity.this, DetalleOportunidadActivity.class);
                intent.putExtra("id", lista.get(i).getId());
                intent.putExtra("nombre", lista.get(i).getNombre());
                intent.putExtra("moneda", lista.get(i).getMoneda());
                intent.putExtra("valor", lista.get(i).getValor());
                intent.putExtra("valorVenta", lista.get(i).getValorVenta());
                intent.putExtra("fechaCobro", lista.get(i).getFechaCobro());
                intent.putExtra("plazo", lista.get(i).getPlazoPago());
                intent.putExtra("detalle", lista.get(i).getDetalle());
                intent.putExtra("status", lista.get(i).getStatus());
                intent.putExtra("username", lista.get(i).getUsername());
                intent.putExtra("idOfertante", lista.get(i).getIdOfertante());

                intent.putExtra("listaOportunidades", (Serializable) lista);
                startActivity(intent);

                Toast.makeText(adapterView.getContext(), "Selecciona: " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String formatearDouble(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(Locale.US);
        return formato.format(valor);
    }

}