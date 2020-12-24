package com.caam.confirming.ui.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.ui.Inversor.MenuInversorActivity;
import com.caam.confirming.ui.login.LoginActivity;

public class RegistroActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioInversor, radioOfertante;
    String nombre, apellido, email, contrasena, contrasenaConf, tipoUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final EditText editNombre = findViewById(R.id.editNombre);
        final EditText editApellido = findViewById(R.id.editApellido);
        final EditText editRegEmailAddress = findViewById(R.id.editRegEmailAddress);
        final EditText editContrasena = findViewById(R.id.editContrasena);
        final EditText editContrasenaConf = findViewById(R.id.editContrasenaConf);

        radioGroup = findViewById(R.id.radioGroup);
        radioInversor = (RadioButton)findViewById(R.id.radioInversor);
        radioOfertante = (RadioButton)findViewById(R.id.radioOfertante);

        Button buttonSiguiente = findViewById(R.id.buttonSiguiente);
        buttonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = editNombre.getText().toString();
                apellido = editApellido.getText().toString();
                String email = editRegEmailAddress.getText().toString();
                contrasena = editContrasena.getText().toString();
                contrasenaConf = editContrasenaConf.getText().toString();
                tipoUsuario = "";


                System.out.println("nombre : " + nombre);
                System.out.println("apellido : " + apellido);
                System.out.println("email : " + email);
                System.out.println("contrasena : " + contrasena);
                System.out.println("contrasenaConf : " + contrasenaConf);
                System.out.println("tipoUsuario : " + tipoUsuario);

                Bundle parametros = new Bundle();
                parametros.putString("nombre", nombre);
                parametros.putString("apellido", apellido);
                parametros.putString("email", nombre);
                parametros.putString("contrasena", apellido);

                int radioId = -1;
                radioId = radioGroup.getCheckedRadioButtonId();

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioInversor:
                        radioId = 0;
                        break;
                    case R.id.radioOfertante:
                        radioId = 1;
                        break;
                }
                System.out.println("radioId : " + radioId);

                if(radioId == 0) {
                    tipoUsuario = "inversor";

                    Intent intent = new Intent(RegistroActivity.this, RegistroInversorActivity.class);
                    intent.putExtras(parametros);
                    startActivity(intent);
                }
                else {
                    tipoUsuario = "ofertante";
                    Intent intent = new Intent(RegistroActivity.this, RegistroOfertanteActivity.class);
                    intent.putExtras(parametros);
                    startActivity(intent);
                }



                // radioButton = findViewById(radioId);
            }
        });

    }


}