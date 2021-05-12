package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val requestCodeH = 420
    var historialScans = HistorialScans(listOf())
    var waiting = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        waiting = intent.getBooleanExtra("esperando", false)
        btnCrearQR.setOnClickListener{
            if(!TemporalStorage.listaPerfiles.isEmpty()){
                val intent = Intent(this, SuccessActivity::class.java)
                startActivity(intent)
            }
            else if(!waiting){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)}
            else{
                val intent = Intent(this, WaitActivity::class.java)
                startActivity(intent)
            }
        }
        btnScanQR.setOnClickListener{
            val intent = Intent(this, ScanMenuActivity::class.java)
            intent.putExtra("historial", Gson().toJson(historialScans))
            startActivityForResult(intent, requestCodeH)
        }
        btnVerQR.setOnClickListener {
            if(!TemporalStorage.listaPerfiles.isEmpty()) {
                val intent = Intent(this, ViewProfileActivity::class.java)
                startActivity(intent)
                waiting = false
            }
            else {
                Toast.makeText(this, "No hay un perfil aun", Toast.LENGTH_SHORT).show()
            }
        }
        btnSalir.setOnClickListener{
            System.exit(0)
        }
    }
}