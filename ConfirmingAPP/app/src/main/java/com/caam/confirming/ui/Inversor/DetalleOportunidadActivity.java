package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caam.confirming.R;
import com.caam.confirming.models.Oportunidad;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DetalleOportunidadActivity extends AppCompatActivity {

    TextView mensajeConfirmarCompra;
    Button btnFinalizarCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_oportunidad);

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
        Double valorVenta = getIntent().getDoubleExtra("valorVenta", 0.0);
        valueValorVenta.setText(formatearDouble(valorVenta));
        Double ganancia = valor - valorVenta;
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
                mensajeConfirmarCompra.setText("Ha seleccionado la opción comprar. Si desea fianlizar el proceso de compra de esta factura, seleccione Finalizar Factura.");
                btnFinalizarCompra.setEnabled(true);
            }
        });

    }

    private String formatearDouble(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(Locale.US);
        return formato.format(valor);
    }
}