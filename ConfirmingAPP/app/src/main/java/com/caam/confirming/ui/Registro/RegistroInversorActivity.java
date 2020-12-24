package com.caam.confirming.ui.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.interfaces.UserAPI;
import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Inversor;
import com.caam.confirming.interfaces.InversorAPI;
import com.caam.confirming.models.Login;
import com.caam.confirming.models.UserReturn;
import com.caam.confirming.ui.Inversor.MenuInversorActivity;
import com.caam.confirming.ui.Ofertante.MenuOfertanteActivity;
import com.caam.confirming.ui.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class RegistroInversorActivity extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    Inversor inversor = new Inversor();
    /*private Button buttonSiguiente;
    private EditText editIdentificacion;
    private EditText editMonto;
    private EditText editTelefono;*/

    //String nombre, apellido, email, contrasena, contrasenaConf, tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_inversor);

        EditText editIdentificacion = (EditText)findViewById(R.id.editIdentificacion);
        EditText editMonto = (EditText)findViewById(R.id.editMonto);
        EditText editTelefono = (EditText)findViewById(R.id.editTelefono);


        spinner1 = (Spinner)findViewById(R.id.spinner);
        String [] opciones = {"Cedula de ciudadania", "Passaporte", "DNI"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner)findViewById(R.id.spinner2);
        String [] opcionesMoneda = {"COP", "USD", "EUR"};
        ArrayAdapter<String> adapterMoneda = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opcionesMoneda);
        spinner2.setAdapter(adapterMoneda);

        Bundle datos = this.getIntent().getExtras();
        inversor.setNombre(datos.getString("nombre"));
        inversor.setApellido(datos.getString("apellido"));
        inversor.setEmail(datos.getString("email"));
        inversor.setContrasena(datos.getString("contrasena"));

        Button buttonFinalizarRegInversor = (Button)findViewById(R.id.buttonFinalizarRegInversor);
        buttonFinalizarRegInversor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inversor.setId(Integer.parseInt(editIdentificacion.getText().toString()));
                inversor.setTipoIdentificacion(spinner1.getSelectedItem().toString());
                inversor.setIdentificacion(editIdentificacion.getText().toString());
                inversor.setMoneda(spinner2.getSelectedItem().toString());
                inversor.setMonto(editMonto.getText().toString());
                inversor.setTelefono(editTelefono.getText().toString());
                insertInversor(inversor);
            }
        });


      /*  System.out.println("nombre : " + nombre);
        System.out.println("apellido : " + apellido);
        System.out.println("email : " + email);
        System.out.println("contrasena : " + contrasena);
        System.out.println("tipoUsuario : " + tipoUsuario);*/

    }


    private void insertInversor(Inversor inversor) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.7:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        InversorAPI inversorAPI = retrofit.create(InversorAPI.class);
        //Call<String> call = inversorAPI.insertInversorService(inversor.getId(), inversor.getNombre(), inversor.getApellido(), inversor.getEmail(), inversor.getContrasena(),
        //                                inversor.getTipoIdentificacion(), inversor.getIdentificacion(),  inversor.getMoneda(), inversor.getMonto(), inversor.getTelefono());

        Call<InsertResult> call = inversorAPI.insertInversorService(inversor);
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

                            Intent intent = new Intent(RegistroInversorActivity.this, MenuInversorActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(RegistroInversorActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                        //System.out.println(u.isUser().);
                    }
                }catch (Exception ex){
                    Toast.makeText( RegistroInversorActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText( RegistroInversorActivity.this,  "No Response...", Toast.LENGTH_SHORT).show();
            }
        });


        Login login = new Login();
        login.setId(inversor.getId());
        login.setLogin(inversor.getEmail());
        login.setContrasena(inversor.getContrasena());
        login.setTipoUsuario("inversor");

        Call<InsertResult> callServiceMQ = inversorAPI.insertLoginService(login, inversor.getEmail());
        callServiceMQ.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> callServiceMQ, Response<InsertResult> response) {
                try {
                    if (response.isSuccessful()) {
                        InsertResult responseMQService = response.body();
                        Log.i("My App ", responseMQService.getInsertR());
                    }
                } catch (Exception ex) {
                    Toast.makeText(RegistroInversorActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<InsertResult> callServiceMQ, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText( RegistroInversorActivity.this,  "No Response...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
