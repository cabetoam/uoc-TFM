package com.caam.confirming.ui.Inversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.caam.confirming.R;

public class MenuInversorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inversor);

        Button buttonOportunidades = (Button)findViewById(R.id.buttonOportunidades);


        buttonOportunidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuInversorActivity.this, BuscarOportunidadActivity.class);
                startActivity(intent);
            }
        });
    }
}