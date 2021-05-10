package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_scan_menu.*


class ScanMenuActivity : AppCompatActivity() {
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

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (intentResult != null) {
            if (intentResult.contents == null) {
                Toast.makeText(baseContext, "Invalido", Toast.LENGTH_SHORT).show()
            } else {
                toolbar5.setTitle("Scan Exitoso!")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}