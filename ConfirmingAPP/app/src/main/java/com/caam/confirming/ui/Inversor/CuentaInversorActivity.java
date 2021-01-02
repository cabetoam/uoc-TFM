package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caam.confirming.R;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class CuentaInversorActivity extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_inversor);

        username = getIntent().getStringExtra("username");

        TextView tvTotalFacturasCompradas = (TextView)findViewById(R.id.tvTotalFacturasCompradas);
        TextView tvTotalInvertidoCOP = (TextView)findViewById(R.id.tvTotalInvertidoCOP);
        TextView tvTotalGananciaCOP = (TextView)findViewById(R.id.tvTotalGananciaCOP);
        TextView tvTotalInvertidoUSD = (TextView)findViewById(R.id.tvTotalInvertidoUSD);
        TextView tvTotalGananciaUSD = (TextView)findViewById(R.id.tvTotalGananciaUSD);
        TextView tvTotalInvertidoEUR = (TextView)findViewById(R.id.tvTotalInvertidoEUR);
        TextView tvTotalGananciaEUR = (TextView)findViewById(R.id.tvTotalGananciaEUR);
        Button btnRegresarMenuInv = (Button)findViewById(R.id.btnRegresarMenuInv);

        tvTotalFacturasCompradas.setText(Integer.toString(getIntent().getIntExtra("totalesCompradas", 0)));
        tvTotalInvertidoCOP.setText(formatearDouble(getIntent().getDoubleExtra("totalInversionCOP", 0.0)));
        tvTotalGananciaCOP.setText(formatearDouble(getIntent().getDoubleExtra("totalGananciaCOP", 0.0)));
        tvTotalInvertidoUSD.setText(formatearDouble(getIntent().getDoubleExtra("totalInversionUSD", 0.0 )));
        tvTotalGananciaUSD.setText(formatearDouble(getIntent().getDoubleExtra("totalGananciaUSD", 0.0)));
        tvTotalInvertidoEUR.setText(formatearDouble(getIntent().getDoubleExtra("totalInversionEUR", 0.0)));
        tvTotalGananciaEUR.setText(formatearDouble(getIntent().getDoubleExtra("totalGananciaEUR", 0.0)));

        btnRegresarMenuInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CuentaInversorActivity.this, MenuInversorActivity.class);
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