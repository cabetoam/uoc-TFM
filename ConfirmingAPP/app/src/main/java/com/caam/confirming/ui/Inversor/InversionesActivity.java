package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.models.Inversion;
import com.caam.confirming.models.Oportunidad;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InversionesActivity extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inversiones);

        username = getIntent().getStringExtra("username");

        ArrayList<String> listaToShow = new ArrayList<String>();
        ListView idListaInversiones = (ListView)findViewById(R.id.idListaInversiones);

        List<Inversion> listaInversiones = (List<Inversion>)getIntent().getSerializableExtra("listaInversiones");
        System.out.println("lista inversiones : " + listaInversiones.get(0).getId());

        for (int i = 0; i < listaInversiones.size(); i++) {
            listaToShow.add("Valor Invertido: " + formatearDouble(listaInversiones.get(i).getValorCompra()) + " " + listaInversiones.get(i).getMoneda() +  "\n" +
                    "Ganancia: " + formatearDouble(listaInversiones.get(i).getGanancia()) + " " + listaInversiones.get(i).getMoneda() + "\n");
        }

        ArrayAdapter adapter = new ArrayAdapter(this,  android.R.layout.simple_selectable_list_item, listaToShow);
        idListaInversiones.setAdapter(adapter);

        idListaInversiones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("id select : " + i);
                System.out.println(listaInversiones.get(i).getUsernameComprador());
                System.out.println(listaInversiones.get(i).getNombreOfertante());
                System.out.println(listaInversiones.get(i).getMoneda());
                System.out.println(listaInversiones.get(i).getValorCompra());
                System.out.println(listaInversiones.get(i).getFechaCompra());
                System.out.println(listaInversiones.get(i).getPlazoPago());

                Intent intent = new Intent(InversionesActivity.this, DetalleInversionActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("email", listaInversiones.get(i).getUsernameComprador());
                intent.putExtra("ofertante", listaInversiones.get(i).getNombreOfertante());
                intent.putExtra("moneda", listaInversiones.get(i).getMoneda());
                intent.putExtra("valorInvertido", listaInversiones.get(i).getValorCompra());
                intent.putExtra("ganancia", listaInversiones.get(i).getGanancia());
                intent.putExtra("fechaCompra", listaInversiones.get(i).getFechaCompra());
                intent.putExtra("plazo", listaInversiones.get(i).getPlazoPago());

                intent.putExtra("listaInversiones", (Serializable) listaInversiones);
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