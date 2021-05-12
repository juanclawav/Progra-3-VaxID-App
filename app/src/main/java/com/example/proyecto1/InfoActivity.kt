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
import android.widget.ArrayAdapter
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.activity_info.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class InfoActivity : AppCompatActivity() {
    val requestCodeGallery = 420
    val requestCodeCamera = 696

    var qrString = ""

    var bitmap: Bitmap? = null
    var qrgEncoder: QRGEncoder? = null
    var fileUri: Uri? = null

    var certUri: Uri? = null

    var gson: Gson = GsonBuilder().apply { registerTypeAdapter(Uri::class.java, UriAdapter())}.create()

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_info)

            btnBack2.setOnClickListener {
                finish()
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
                startActivityForResult(Intent.createChooser(intent,"Escoge una foto tuya"), requestCodeGallery)
            }
            val Departamentos = arrayOf("CH", "LP", "CB", "OR", "PT", "TJ", "SC", "BE", "PD")
            val ArrayDps =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Departamentos)
            spinnerDepts.adapter = ArrayDps

            val lugaresLP = arrayOf("UMSA", "UPEA", "ProSalud", "EMI")
            val lugaresCB = arrayOf("Sennfeld", "Pacata", "ProSalud", "COMBASE")
        spinnerDepts.setSelection(1)
        val arrayLgs1 =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lugaresLP)
        spinnerLugaresVac.adapter = arrayLgs1
        btnAccept.setOnClickListener {
            if (editTextName.text.toString() == "") {
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
                qrString = editTextLastName.text.toString()+ " + " + editTextName.text.toString() + " + " + fileUri.toString()
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
                val intent = Intent(this, RegisterActivity::class.java)
                val newProfile = Perfil(fileUri,certUri,editTextName.text.toString(),editTextLastName.text.toString(),
                    SimpleDateFormat("yyyy.MM.dd ' - ' HH:mm").format(Date()),bitmap,spinnerLugaresVac.selectedItem.toString(),spinnerDepts.selectedItem.toString()
                )
                TemporalStorage.listaPerfiles.add(newProfile)
                if(TemporalStorage.listaPerfiles[0].nombres.equals(editTextName.text.toString())){
                    Toast.makeText(this, "Guardado Exitosamente!", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent)
            }
        }
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
