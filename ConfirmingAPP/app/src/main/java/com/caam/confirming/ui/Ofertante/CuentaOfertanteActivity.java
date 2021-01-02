package com.caam.confirming.ui.Ofertante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caam.confirming.R;
import com.caam.confirming.ui.Inversor.CuentaInversorActivity;
import com.caam.confirming.ui.Inversor.MenuInversorActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class CuentaOfertanteActivity extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_ofertante);

        username = getIntent().getStringExtra("username");

        TextView tvTotalFacturasVendidas = (TextView)findViewById(R.id.tvTotalFacturasVendidas);
        TextView tvTotalFacturasCOP = (TextView)findViewById(R.id.tvTotalFacturasCOP);
        TextView tvTotalRecaudoCOP = (TextView)findViewById(R.id.tvTotalRecaudoCOP);
        TextView tvTotalFacturasUSD = (TextView)findViewById(R.id.tvTotalFacturasUSD);
        TextView tvTotalRecaudoUSD = (TextView)findViewById(R.id.tvTotalRecaudoUSD);
        TextView tvTotalFacturasEUR = (TextView)findViewById(R.id.tvTotalFacturasEUR);
        TextView tvTotalRecaudoEUR = (TextView)findViewById(R.id.tvTotalRecaudoEUR);
        Button btnRegresarMenuOfer = (Button)findViewById(R.id.btnRegresarMenuOfer);

        tvTotalFacturasVendidas.setText(Integer.toString(getIntent().getIntExtra("totalesVendidas", 0)));
        tvTotalFacturasCOP.setText(formatearDouble(getIntent().getDoubleExtra("totalFacturasCOP", 0.0)));
        tvTotalRecaudoCOP.setText(formatearDouble(getIntent().getDoubleExtra("totalRecaudoCOP", 0.0)));
        tvTotalFacturasUSD.setText(formatearDouble(getIntent().getDoubleExtra("totalFacturasUSD", 0.0 )));
        tvTotalRecaudoUSD.setText(formatearDouble(getIntent().getDoubleExtra("totalRecaudoUSD", 0.0)));
        tvTotalFacturasEUR.setText(formatearDouble(getIntent().getDoubleExtra("totalFacturasEUR", 0.0)));
        tvTotalRecaudoEUR.setText(formatearDouble(getIntent().getDoubleExtra("totalRecaudoEUR", 0.0)));

        btnRegresarMenuOfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CuentaOfertanteActivity.this, MenuOfertanteActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }

    private String formatearDouble(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(Locale.US);
        return formato.format(valor);
    }
}