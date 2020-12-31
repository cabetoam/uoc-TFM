package com.caam.confirming.ui.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.interfaces.OfertanteAPI;
import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Login;
import com.caam.confirming.models.Ofertante;
import com.caam.confirming.ui.Ofertante.MenuOfertanteActivity;
import com.caam.confirming.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroOfertanteActivity extends AppCompatActivity {

    private Spinner spinnerIdEmpresa;
    Ofertante ofertante = new Ofertante();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ofertante);

        EditText editIdEmpresa = (EditText)findViewById(R.id.editIdEmpresa);
        EditText editNombreEmpresa = (EditText)findViewById(R.id.editNombreEmpresa);
        EditText editTelefono = (EditText)findViewById(R.id.editTelefonoContacto);

        TextView tituloNombreApellidoOfertante = (TextView)findViewById(R.id.tituloNombreApellidoOfertante);

        spinnerIdEmpresa = (Spinner)findViewById(R.id.spinnerIdEmpresa);
        String [] opcionesIdEmpresa = {"NIT", "Cedula de ciudadania", "Passaporte", "DNI"};
        ArrayAdapter<String> adapterIdEmpresa = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opcionesIdEmpresa);
        spinnerIdEmpresa.setAdapter(adapterIdEmpresa);

        Bundle datos = this.getIntent().getExtras();
        ofertante.setNombre(datos.getString("nombre"));
        ofertante.setApellido(datos.getString("apellido"));
        ofertante.setEmail(datos.getString("email"));
        ofertante.setContrasena(datos.getString("contrasena"));

        tituloNombreApellidoOfertante.setText(datos.getString("nombre") + ": continua con el registro");

        Button finalizarRegistroEmpresa = (Button)findViewById(R.id.finalizarRegistroEmpresa);
        finalizarRegistroEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ofertante.setId(Integer.parseInt(editIdEmpresa.getText().toString()));
                ofertante.setNombre(editNombreEmpresa.getText().toString());
                ofertante.setTipoIdentificacionEmpresa(spinnerIdEmpresa.getSelectedItem().toString());
                ofertante.setIdentificacionEmpresa(editIdEmpresa.getText().toString());
                ofertante.setTelefono(editTelefono.getText().toString());

                insertOfertante(ofertante);
            }
        });

        LinearLayout contaniner = (LinearLayout)findViewById(R.id.container);
        contaniner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }


    private void insertOfertante(Ofertante ofertante) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:6060/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        OfertanteAPI ofertanteAPI = retrofit.create(OfertanteAPI.class);
        Call<InsertResult> call = ofertanteAPI.insertOfertanteService(ofertante);
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                try {
                    if (response.isSuccessful()) {
                        InsertResult responseService = response.body();
                        Log.i("My App ", responseService.getInsertR());
                        System.out.println("responseService : " + responseService.getInsertR());

                        //Toast.makeText(getApplicationContext(), responseService, Toast.LENGTH_LONG).show();

                        if(responseService.getInsertR().equals("Ok")) {

                            Intent intent = new Intent(RegistroOfertanteActivity.this, MenuOfertanteActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(RegistroOfertanteActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                        //System.out.println(u.isUser().);
                    }
                }catch (Exception ex){
                    Toast.makeText( RegistroOfertanteActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText( RegistroOfertanteActivity.this,  "No Response...", Toast.LENGTH_SHORT).show();
            }
        });


        Login login = new Login();
        login.setId(ofertante.getId());
        login.setLogin(ofertante.getEmail());
        login.setContrasena(ofertante.getContrasena());
        login.setTipoUsuario("ofertante");

        Retrofit retrofit2 = new Retrofit.Builder().baseUrl("http://192.168.1.101:5050/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ofertanteAPI = retrofit2.create(OfertanteAPI.class);
        Call<InsertResult> callServiceMQ = ofertanteAPI.insertLoginService(login, ofertante.getEmail());
        callServiceMQ.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> callServiceMQ, Response<InsertResult> response) {
                try {
                    if (response.isSuccessful()) {
                        InsertResult responseMQService = response.body();
                        Log.i("My App ", responseMQService.getInsertR());
                    }
                } catch (Exception ex) {
                    Toast.makeText(RegistroOfertanteActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<InsertResult> callServiceMQ, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText( RegistroOfertanteActivity.this,  "No Response...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}