package com.example.proyecto1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_register.*
import java.io.File


class RegisterActivity : AppCompatActivity() {
    val requestCodeCamera = 696

    var fileUri: Uri? = null
    var gson: Gson = GsonBuilder().apply { registerTypeAdapter(Uri::class.java, UriAdapter())}.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btnBack.setOnClickListener {
            finish()
        }
        btnInfo.setOnClickListener{
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("UriCertificado",fileUri)
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
            startActivity(intent)
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