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
import java.util.ArrayList;
import java.util.List;

public class InversionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inversiones);

        ArrayList<String> listaToShow = new ArrayList<String>();
        ListView idListaInversiones = (ListView)findViewById(R.id.idListaInversiones);

        List<Inversion> listaInversiones = (List<Inversion>)getIntent().getSerializableExtra("listaObjetos");
        System.out.println("lista inversiones : " + listaInversiones.get(0).getId());

        for (int i = 0; i < listaInversiones.size(); i++) {
            listaToShow.add("Valor: " + listaInversiones.get(i).getValor() + "\n" + "Plazo: " + listaInversiones.get(i).getPlazoPago() + "\n");
        }

        ArrayAdapter adapter = new ArrayAdapter(this,  android.R.layout.simple_selectable_list_item, listaToShow);
        idListaInversiones.setAdapter(adapter);

        idListaInversiones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("id select : " + i);
                System.out.println(listaInversiones.get(i).getEmail());
                System.out.println(listaInversiones.get(i).getOfertante());
                System.out.println(listaInversiones.get(i).getMoneda());
                System.out.println(listaInversiones.get(i).getValor());
                System.out.println(listaInversiones.get(i).getFechaCompra());
                System.out.println(listaInversiones.get(i).getPlazoPago());

                Intent intent = new Intent(InversionesActivity.this, DetalleInversionActivity.class);
                intent.putExtra("email", listaInversiones.get(i).getEmail());
                intent.putExtra("ofertante", listaInversiones.get(i).getOfertante());
                intent.putExtra("moneda", listaInversiones.get(i).getMoneda());
                intent.putExtra("valor", listaInversiones.get(i).getValor());
                intent.putExtra("fechaCompra", listaInversiones.get(i).getFechaCompra());
                intent.putExtra("plazo", listaInversiones.get(i).getPlazoPago());

                intent.putExtra("listaObjetos", (Serializable) listaInversiones);
                startActivity(intent);

                Toast.makeText(adapterView.getContext(), "Selecciona: " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}