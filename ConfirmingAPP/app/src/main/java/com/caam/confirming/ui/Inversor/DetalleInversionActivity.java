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
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DetalleInversionActivity extends AppCompatActivity {
    String username;
    Double ganancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_inversion);

        username = getIntent().getStringExtra("username");

        List<Inversion> listaInversion = (List<Inversion>)getIntent().getSerializableExtra("listaInversiones");

        TextView valueNombreOfertante = (TextView)findViewById(R.id.valueNombreOfertante);
        TextView valueMoneda = (TextView)findViewById(R.id.valueMoneda);
        TextView valueValorInvertido = (TextView)findViewById(R.id.valueValorInvertido);
        TextView valueGanancia = (TextView)findViewById(R.id.valueGanancia);
        TextView valueFechaCompra = (TextView)findViewById(R.id.valueFechaCompra);
        TextView valuePlazo = (TextView)findViewById(R.id.valuePlazo);
        Button btnRegresar = (Button)findViewById(R.id.btnRegresar);

        valueNombreOfertante.setText(getIntent().getStringExtra("ofertante"));
        valueMoneda.setText(getIntent().getStringExtra("moneda"));
        Double valor = getIntent().getDoubleExtra("valorInvertido", 0.0);
        valueValorInvertido.setText(formatearDouble(valor));
        ganancia = getIntent().getDoubleExtra("ganancia", 0.0);
        valueGanancia.setText(formatearDouble(ganancia));
        valueFechaCompra.setText(getIntent().getStringExtra("fechaCompra"));
        valuePlazo.setText(getIntent().getStringExtra("plazo") + " dias");

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleInversionActivity.this, InversionesActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("listaInversiones", (Serializable) listaInversion);
                startActivity(intent);
            }
        });
    }

    private String formatearDouble(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(Locale.US);
        return formato.format(valor);
    }
}