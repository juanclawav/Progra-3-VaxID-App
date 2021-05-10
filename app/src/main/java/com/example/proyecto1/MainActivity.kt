package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val requestCodeH = 420
    var historialScans = HistorialScans(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCrearQR.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        btnScanQR.setOnClickListener{
            val intent = Intent(this, ScanMenuActivity::class.java)
            intent.putExtra("historial", Gson().toJson(historialScans))
            startActivityForResult(intent, requestCodeH)
        }
        btnSalir.setOnClickListener{
            finish()
        }
    }
}