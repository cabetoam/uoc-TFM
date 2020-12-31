package com.caam.confirming.ui.Ofertante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TextView;

import com.caam.confirming.R;
import com.caam.confirming.models.Oportunidad;
import com.caam.confirming.ui.Inversor.DetalleOportunidadActivity;
import com.caam.confirming.ui.Inversor.OportunidadesActivity;

import java.io.Serializable;
import java.util.List;

public class DetalleFacturaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_factura);



        List<Oportunidad> lista = (List<Oportunidad>)getIntent().getSerializableExtra("listaObjetos");

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
                Intent intent = new Intent(DetalleFacturaActivity.this, FacturasVendidasActivity.class);
                intent.putExtra("listaObjetos", (Serializable) lista);
                startActivity(intent);
            }
        });


    }
}