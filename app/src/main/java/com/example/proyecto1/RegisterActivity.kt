package com.example.proyecto1

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.activity_register.*
import java.io.File


class RegisterActivity : AppCompatActivity() {
    val requestCodeCamera = 696

    var fileUri: Uri? = null
    var qrString = ""

    var bitmap: Bitmap? = null
    var qrgEncoder: QRGEncoder? = null

    var gson: Gson = GsonBuilder().apply { registerTypeAdapter(Uri::class.java, UriAdpter())}.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnInfo.setOnClickListener{
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
        btnCerti.setOnClickListener{
            val file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "imageCamera" + System.currentTimeMillis() + ".jpg"
            )
            fileUri = FileProvider.getUriForFile(this, "com.example.proyecto1", file)

            val intent = Intent()
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            startActivityForResult(intent, requestCodeCamera)
        }

        btnCrear.setOnClickListener {
            val intent = Intent(this, WaitActivity::class.java)
            if (!checkBoxLastName.isChecked||!checkBoxName.isChecked||!checkBoxName2.isChecked) {

                Toast.makeText(this, "Falta Informacion", Toast.LENGTH_SHORT).show();
            } else {

                // below line is for getting
                // the windowmanager service.
                var manager : WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager;

                // initializing a variable for default display.
                val display = manager.defaultDisplay

                // creating a variable for point which
                // is to be displayed in QR Code.
                var point : Point = Point()
                display.getSize(point);

                // getting width and
                // height of a point
                var width = point.x;
                var height = point.y;

                // generating dimension from width and height.
                var dimen = maxOf(height, width)
                dimen = dimen * 3 / 4;

                // setting this dimensions inside our qr code
                // encoder to generate our qr code.
                qrString = qrString + editTextLastName.text + " + " + editTextName.text
                qrgEncoder = QRGEncoder(qrString, null, QRGContents.Type.TEXT, dimen);
                try {
                    // getting our qrcode in the form of bitmap.
                    bitmap = qrgEncoder!!.encodeAsBitmap();
                    // the bitmap is set inside our image
                    // view using .setimagebitmap method.
                } catch (e: WriterException) {
                    // this method is called for
                    // exception handling.
                    Log.e("Tag", e.toString());
                }
                intent.putExtra("codigoQR", Gson().toJson(bitmap))
                intent.putExtra("apellidos", Gson().toJson(editTextLastName.text))
                intent.putExtra("nombres", Gson().toJson(editTextName.text))
                intent.putExtra("fotoPerfil", Gson().toJson(fileUri))
                startActivity(intent)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCodeCamera) {
            Toast.makeText(
                this,
                "Certificado guardado exitosamente!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}