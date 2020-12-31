package com.caam.confirming.ui.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    int radioId;

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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                radioInversor = (RadioButton)findViewById(R.id.radioInversor);
                //radioOfertante = (RadioButton)findViewById(R.id.radioOfertante);


                radioId = radioGroup.getCheckedRadioButtonId();

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioInversor:
                        radioId = 0;
                        break;
                    case R.id.radioOfertante:
                        radioId = 1;
                        break;
                }
            }
        });

        LinearLayout contaniner = (LinearLayout)findViewById(R.id.container);
        contaniner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        Button buttonSiguiente = findViewById(R.id.buttonSiguiente);

        System.out.println("radioId : " + radioId);
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
                parametros.putString("email", email);
                parametros.putString("contrasena", contrasena);

                if(email.contains("@") && email.contains(".")) {
                    if (!contrasena.equals(contrasenaConf)) {
                        Toast.makeText(getApplicationContext(), "Contraseñas no son iguales...", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent intent;
                        switch (radioId)
                        {
                            case 0:
                                tipoUsuario = "inversor";
                                intent = new Intent(RegistroActivity.this, RegistroInversorActivity.class);
                                intent.putExtras(parametros);
                                startActivity(intent);
                                break;

                            case 1:
                                tipoUsuario = "ofertante";
                                intent = new Intent(RegistroActivity.this, RegistroOfertanteActivity.class);
                                intent.putExtras(parametros);
                                startActivity(intent);
                                break;
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Corre electonico no valido...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}