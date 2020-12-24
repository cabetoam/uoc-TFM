package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.models.Oportunidad;

import java.util.ArrayList;
import java.util.List;

public class OportunidadesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oportunidades);
        ArrayList<String> listaToShow = new ArrayList<String>();
        ListView idLista = (ListView)findViewById(R.id.idLista);

        List<Oportunidad> lista = (List<Oportunidad>)getIntent().getSerializableExtra("listaObjetos");
        System.out.println("lista : " + lista.get(0).getId());

        for (int i = 0; i < lista.size(); i++) {
            listaToShow.add("Valor: " + lista.get(i).getValor() + "\n" + "Plazo: " + lista.get(i).getPlazoPago() + "\n");
        }

        ArrayAdapter adapter = new ArrayAdapter(this,  android.R.layout.simple_selectable_list_item, listaToShow);
        idLista.setAdapter(adapter);

        idLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapterView.getContext(), "Selecciona: " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });










    }
}