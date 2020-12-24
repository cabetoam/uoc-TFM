package com.caam.confirming.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.caam.confirming.PublicarArchivoActivity;
import com.caam.confirming.R;
import com.caam.confirming.ui.Inversor.BuscarOportunidadActivity;
import com.caam.confirming.ui.Inversor.MenuInversorActivity;
import com.caam.confirming.ui.Ofertante.MenuOfertanteActivity;
import com.caam.confirming.ui.Publicar.TomarFotoActivity;
import com.caam.confirming.ui.Registro.RegistroActivity;
import com.caam.confirming.ui.Registro.RegistroInversorActivity;
import com.caam.confirming.interfaces.UserAPI;
import com.caam.confirming.models.UserReturn;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.Base64.getEncoder;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private TextView mensajeError;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final Button buttonFoto = findViewById(R.id.buttonFoto);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Button register = findViewById(R.id.register);
        mensajeError = (TextView)findViewById(R.id.mensajeError);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());

                find(usernameEditText.getText().toString(), passwordEditText.getText().toString() );

                //Se colocan en este metodo para pruebas del diseño de las intertfaces
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        buttonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this, TomarFotoActivity.class);
                Intent intent = new Intent(LoginActivity.this, BuscarOportunidadActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        //String welcome = "Welcome ! " + model.getDisplayName();
        // TODO : initiate successful logged in experience
        //Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        //Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }


    private void find(String username, String contrasena) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.3:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call <UserReturn> call = userAPI.find(username, contrasena);
        call.enqueue(new Callback<UserReturn>() {
            @Override
            public void onResponse(Call<UserReturn> call, Response<UserReturn> response) {
                try {
                    if (response.isSuccessful()) {
                        UserReturn userReturn = response.body();
                        Log.i("My App", userReturn.getTipoUsuario());
                        System.out.println(userReturn.getTipoUsuario());
                        String isUser = userReturn.getIsUser();

                        System.out.println((userReturn.getIsUser()));

                        Toast.makeText(getApplicationContext(), userReturn.getTipoUsuario(), Toast.LENGTH_LONG).show();

                        if(isUser.equals("true")) {
                            if(userReturn.getTipoUsuario().equals("inversor")) {
                                Intent intent = new Intent(LoginActivity.this, MenuInversorActivity.class);
                                startActivity(intent);
                            }
                            else if(userReturn.getTipoUsuario().equals("ofertante")){
                                Intent intent = new Intent(LoginActivity.this, MenuOfertanteActivity.class);
                                startActivity(intent);
                            }
                        }
                        else {
                            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                        //System.out.println(u.isUser().);
                    }
                }catch (Exception ex){
                    Toast.makeText( LoginActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserReturn> call, Throwable t) {
                Toast.makeText( LoginActivity.this,  "No Response...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}