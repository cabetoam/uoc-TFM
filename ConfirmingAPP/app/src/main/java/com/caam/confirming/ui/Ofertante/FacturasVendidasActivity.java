package com.caam.confirming.ui.Ofertante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.models.Factura;
import com.caam.confirming.models.Oportunidad;
import com.caam.confirming.ui.Inversor.DetalleOportunidadActivity;
import com.caam.confirming.ui.Inversor.OportunidadesActivity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FacturasVendidasActivity extends AppCompatActivity {
    String username;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas_vendidas);

        username = getIntent().getStringExtra("username");

        ArrayList<String> listaToShow = new ArrayList<String>();
        ListView idListaFacturas = (ListView)findViewById(R.id.idListaFacturas);

        List<Factura> lista = (List<Factura>)getIntent().getSerializableExtra("listaFacturasVendidas");
        System.out.println("lista : " + lista.get(0).getId());

        for (int i = 0; i < lista.size(); i++) {
            listaToShow.add("Valor Factura: " + formatearDouble(lista.get(i).getValor()) + " " + lista.get(i).getMoneda() + "\n" +
                    "Vendida en: " + formatearDouble(lista.get(i).getValorVenta()) + " " + lista.get(i).getMoneda() +  "\n");
        }

        ArrayAdapter adapter = new ArrayAdapter(this,  android.R.layout.simple_selectable_list_item, listaToShow);
        idListaFacturas.setAdapter(adapter);

        idListaFacturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("id select : " + i);
                System.out.println(lista.get(i).getNombre());
                System.out.println(lista.get(i).getMoneda());
                System.out.println(lista.get(i).getValor());
                System.out.println(lista.get(i).getFechaCobro());
                System.out.println(lista.get(i).getPlazoPago());
                System.out.println(lista.get(i).getStatus());

                Intent intent = new Intent(FacturasVendidasActivity.this, DetalleFacturaActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("nombre", lista.get(i).getNombre());
                intent.putExtra("moneda", lista.get(i).getMoneda());
                intent.putExtra("valor", lista.get(i).getValor());
                intent.putExtra("valorVenta", lista.get(i).getValorVenta());
                intent.putExtra("fechaCobro", lista.get(i).getFechaCobro());
                intent.putExtra("plazo", lista.get(i).getPlazoPago());
                intent.putExtra("detalle", lista.get(i).getDetalle());
                intent.putExtra("status", lista.get(i).getStatus());
                intent.putExtra("listaFacturasVendidas", (Serializable) lista);
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