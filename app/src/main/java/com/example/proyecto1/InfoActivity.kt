package com.example.proyecto1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_info.*
import java.io.File

class InfoActivity : AppCompatActivity() {
    val requestCodeGallery = 420
    val requestCodeCamera = 696

    var fileUri: Uri? = null

    var gson: Gson = GsonBuilder().apply { registerTypeAdapter(Uri::class.java, UriAdpter())}.create()

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_info)

            btnBack2.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            btnCam.setOnClickListener {
                val file = File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), "imageCamera" + System.currentTimeMillis() + ".jpg")
                fileUri = FileProvider.getUriForFile(this, "com.example.proyecto1", file)

                val intent = Intent()
                intent.action = MediaStore.ACTION_IMAGE_CAPTURE
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
                startActivityForResult(intent, requestCodeCamera)
            }
            btnUpload.setOnClickListener {
                val intent = Intent()
                intent.action=Intent.ACTION_PICK
                intent.type = "image/*"
                startActivityForResult(Intent.createChooser(intent,"Escoge una foto tuya"), requestCodeCamera)
            }
            val Departamentos = arrayOf("CH", "LP", "CB", "OR", "PT", "TJ", "SC", "BE", "PD")
            val ArrayDps =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Departamentos)
            spinnerDepts.adapter = ArrayDps

            val lugaresLP = arrayOf("UMSA", "UPEA", "ProSalud", "EMI")
            val lugaresCB = arrayOf("Sennfeld", "Pacata", "ProSalud", "COMBASE")

            val arrayLgs =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lugaresLP)
            spinnerLugaresVac.adapter = arrayLgs

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == requestCodeGallery) {
                fileUri = data?.data
                imageViewProfilePic.setImageURI(fileUri)
            }else if(requestCode == requestCodeCamera) {
                imageViewProfilePic.setImageURI(fileUri)
            }
        }
    }
