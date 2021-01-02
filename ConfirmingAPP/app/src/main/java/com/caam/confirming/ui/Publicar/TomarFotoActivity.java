package com.caam.confirming.ui.Publicar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;

import com.caam.confirming.R;
import com.caam.confirming.interfaces.ArchivoAPI;
import com.caam.confirming.interfaces.OfertanteAPI;
import com.caam.confirming.models.InsertResult;
import com.caam.confirming.models.Ofertante;
import com.caam.confirming.ui.Ofertante.MenuOfertanteActivity;
import com.caam.confirming.ui.Registro.RegistroOfertanteActivity;
import com.caam.confirming.ui.login.LoginActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TomarFotoActivity extends AppCompatActivity {

    private String FILE_NAME = "photo-";
    private int REQUEST_CODE = 42;
    private File photoFile;
    Button btnPublicar;
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    byte[] image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_foto);

        Button btnTakePicture2 = (Button)findViewById(R.id.btnTakePicture2);
        btnPublicar = (Button)findViewById(R.id.btnPublicar);
        btnPublicar.setEnabled(false);

        btnTakePicture2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    photoFile = getphotoFile(FILE_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile)

                Uri fileProvider = FileProvider.getUriForFile(getApplicationContext(), "com.caam.confirming.ui.Publicar", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_CODE);
                } else {
                    System.out.println("null");
                    //Toast.makeText( "this", "Unable to open camera", Toast.LENGTH_SHORT).show()
                }
            }
        });

        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertArchivo(stream);
            }
        });
    }

    public File getphotoFile(String filename) throws IOException {
        File storageDitrectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(filename, ".jpg", storageDitrectory);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
                    //val takenImage = data?.extras?.get("data") as Bitmap
            Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
            imageView.setImageBitmap(takenImage);
            System.out.println("imageBitmap : " + String.valueOf(takenImage.getByteCount()));
            System.out.println("imageHeight : " + String.valueOf(takenImage.getHeight()));
            System.out.println("imageHeight : " + String.valueOf(takenImage.getWidth()));
            takenImage.compress(Bitmap.CompressFormat.PNG, 90, stream);
            image = stream.toByteArray();
            String imagenstr = new String(image);
            System.out.println("imageSize : " + image.toString());
            System.out.println("imageSize : " + String.valueOf(stream.size()));
            //System.out.println("imagenstr : " + imagenstr);
            btnPublicar.setEnabled(true);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void insertArchivo(ByteArrayOutputStream stream) {
        System.out.println("streamSize : " + String.valueOf(stream.size()));
        System.out.println("imageSize in insertArchivo : " + image.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.101:9090/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ArchivoAPI archivoAPI = retrofit.create(ArchivoAPI.class);

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", photoFile.getName(), RequestBody.create(MediaType.parse("image/*"), photoFile));
        //Call<InsertResult> call = archivoAPI.insertArchivoService2(photoFile);
        Call<InsertResult> call = archivoAPI.insertArchivoService2(filePart, "5fda866eec919f180e7a5101");
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                try {
                    if (response.isSuccessful()) {
                        InsertResult responseService = response.body();
                        Log.i("My App ", responseService.getInsertR());
                        System.out.println("responseService : " + responseService.getInsertR());

                        if (responseService.getInsertR().equals("Ok")) {
                            Toast.makeText(getApplicationContext(), "Imagen Upload", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(TomarFotoActivity.this, MenuOfertanteActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(TomarFotoActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(TomarFotoActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                System.out.println("responseService error....");
                Toast.makeText(TomarFotoActivity.this, "No Response...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}