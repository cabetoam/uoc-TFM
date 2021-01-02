package com.caam.confirming.ui.Ofertante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TextView;

import com.caam.confirming.R;
import com.caam.confirming.models.Factura;
import com.caam.confirming.models.Oportunidad;
import com.caam.confirming.ui.Inversor.DetalleOportunidadActivity;
import com.caam.confirming.ui.Inversor.OportunidadesActivity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DetalleFacturaActivity extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_factura);

        username = getIntent().getStringExtra("username");

        List<Factura> lista = (List<Factura>)getIntent().getSerializableExtra("listaFacturasVendidas");

        TextView valueNombreOfertante = (TextView)findViewById(R.id.valueNombreOfertante);
        TextView valueMoneda = (TextView)findViewById(R.id.valueMoneda);
        TextView valueValorFactura = (TextView)findViewById(R.id.valueValorFactura);
        TextView valueFechaCobro = (TextView)findViewById(R.id.valueFechaCobro);
        TextView valuePlazo = (TextView)findViewById(R.id.valuePlazo);
        TextView valueValorVenta = (TextView)findViewById(R.id.valueValorVenta);
        TextView valueDetalles = (TextView)findViewById(R.id.valueDetalles);
        Button btnRegresar = (Button)findViewById(R.id.btnRegresar);

        valueNombreOfertante.setText(getIntent().getStringExtra("nombre"));
        valueMoneda.setText(getIntent().getStringExtra("moneda"));
        Double valor = getIntent().getDoubleExtra("valor", 0.0);
        valueValorFactura.setText(formatearDouble(valor));
        Double valorVenta = getIntent().getDoubleExtra("valorVenta", 0.0);
        valueValorVenta.setText(formatearDouble(valorVenta));
        valueFechaCobro.setText(getIntent().getStringExtra("fechaCobro"));
        valuePlazo.setText(getIntent().getStringExtra("plazo"));
        valueDetalles.setText(getIntent().getStringExtra("detalle"));

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleFacturaActivity.this, FacturasVendidasActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("listaFacturasVendidas", (Serializable) lista);
                startActivity(intent);
            }
        });
    }

    private String formatearDouble(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(Locale.US);
        return formato.format(valor);
    }
}