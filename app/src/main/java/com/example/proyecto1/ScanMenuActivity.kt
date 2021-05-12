package com.example.proyecto1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_scan_menu.*
import java.text.SimpleDateFormat
import java.util.*


class ScanMenuActivity : AppCompatActivity() {
    var profileString =""
    val gson = GsonBuilder().registerTypeAdapter(Uri::class.java, UriAdapter()).create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_menu)

        val listaScans = Gson().fromJson(intent.getStringExtra("historial"), HistorialScans::class.java)

        btnScan.setOnClickListener{
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setPrompt("Scan QR Code")
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.initiateScan()
        }
        btnHistory.setOnClickListener {
            val intent = Intent(this, ScansListActivity::class.java)
            intent.putExtra("historial", Gson().toJson(listaScans))
            startActivity(intent)
        }
        btnBack4.setOnClickListener {
            finish()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (intentResult != null) {
            if (intentResult.contents == null) {
                Toast.makeText(baseContext, "Invalido", Toast.LENGTH_SHORT).show()
            } else {
                val qrString = intentResult.contents
                val qrDataArray = qrString.split(" + ")
                val newProfile1 = Perfil(Uri.parse(qrDataArray[2]), null, qrDataArray[1],qrDataArray[0],  SimpleDateFormat("yyyy.MM.dd ' - ' HH:mm").format(
                    Date()),null,null,null)
                TemporalStorage.listaPerfiles.add(newProfile1)
                Toast.makeText(baseContext, "Escaneo exitoso!", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}