package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caam.confirming.R;
import com.caam.confirming.models.Inversion;
import com.caam.confirming.models.Oportunidad;

import java.io.Serializable;
import java.util.List;

public class DetalleInversionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_inversion);

        List<Inversion> listaInversion = (List<Inversion>)getIntent().getSerializableExtra("listaObjetos");

        TextView valueNombreOfertante = (TextView)findViewById(R.id.valueNombreOfertante);
        TextView valueValorFactura = (TextView)findViewById(R.id.valueValorFactura);
        TextView valuePlazo = (TextView)findViewById(R.id.valuePlazo);
        Button btnRegresar = (Button)findViewById(R.id.btnRegresar);

        Double valor = 0.0;
        valor = getIntent().getDoubleExtra("valor", 0.0);

        valueNombreOfertante.setText(getIntent().getStringExtra("nombre"));
        valueValorFactura.setText(valor.toString());
        valuePlazo.setText(getIntent().getStringExtra("plazo"));

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleInversionActivity.this, InversionesActivity.class);
                intent.putExtra("listaObjetos", (Serializable) listaInversion);
                startActivity(intent);
            }
        });
    }
}