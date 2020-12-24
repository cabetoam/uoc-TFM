package com.caam.confirming

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.caam.confirming.ui.Publicar.TomarFotoActivity
import java.io.ByteArrayOutputStream
import java.io.File


private const val FILE_NAME = "photo.jpg"
private const val REQUEST_CODE = 42
private lateinit var photoFile: File


class PublicarArchivoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publicar_archivo)

        val btnTakePicture = findViewById<Button>(R.id.btnTakePicture)
        val buttonPublicar = findViewById<Button>(R.id.buttonPublicar)

        btnTakePicture.setOnClickListener {
            val intent = Intent(this, TomarFotoActivity::class.java)
            startActivity(intent)
           /* val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getphotoFile(FILE_NAME)
            //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile)

            val fileProvider = FileProvider.getUriForFile(this, "com.caam.confirming", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            } else {
                System.out.println("null")
                //Toast.makeText( "this", "Unable to open camera", Toast.LENGTH_SHORT).show()
            }*/
        }


    }

    private fun getphotoFile(filename: String): File {
        val storageDitrectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(filename, ".jpg", storageDitrectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageView = findViewById<ImageView>(R.id.imageView)
            //val takenImage = data?.extras?.get("data") as Bitmap
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
                imageView.setImageBitmap(takenImage)
            println("imageBitmap : ${takenImage.byteCount}")
            println("imageHeight : ${takenImage.height}")
            println("imageHeight : ${takenImage.width}")

            val stream = ByteArrayOutputStream();
            takenImage.compress(Bitmap.CompressFormat.PNG, 90, stream);
            val image = stream.toByteArray();
            println("imageSize : ${image.size}")

            val intent = Intent(this, TomarFotoActivity::class.java)
            startActivity(intent)
            /*val bundle: Bundle = Bundle()
            val nombre = "hola"
            bundle.putString("nombre", nombre)
            var intent: Intent = Intent(this, TomarFotoActivity::class.java).apply {
                intent.putExtra("dato", "bundle")
               // intent.putExtra("foto", image)
            }
            startActivity(intent)*/



        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}