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
import java.util.ArrayList;
import java.util.List;

public class FacturasVendidasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas_vendidas);

        ArrayList<String> listaToShow = new ArrayList<String>();
        ListView idListaFacturas = (ListView)findViewById(R.id.idListaFacturas);

        List<Factura> lista = (List<Factura>)getIntent().getSerializableExtra("listaObjetos");
        System.out.println("lista : " + lista.get(0).getId());

        for (int i = 0; i < lista.size(); i++) {
            listaToShow.add("Valor: " + lista.get(i).getValor() + "\n" + "Plazo: " + lista.get(i).getPlazoPago() + "\n");
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
                intent.putExtra("nombre", lista.get(i).getNombre());
                intent.putExtra("moneda", lista.get(i).getMoneda());
                intent.putExtra("valor", lista.get(i).getValor());
                intent.putExtra("fechaCobro", lista.get(i).getFechaCobro());
                intent.putExtra("plazo", lista.get(i).getPlazoPago());
                intent.putExtra("status", lista.get(i).getStatus());
                intent.putExtra("listaObjetos", (Serializable) lista);
                startActivity(intent);

                Toast.makeText(adapterView.getContext(), "Selecciona: " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}