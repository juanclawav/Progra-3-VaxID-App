package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scan_menu.*

class ScanMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_menu)
        btnBack2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val Departamentos = arrayOf("CH", "LP", "CB", "OR", "PT", "TJ", "SC", "BE", "PD")
        val ArrayDps =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Departamentos)
        spinnerDepts.adapter = ArrayDps

        val lugaresLP = arrayOf("UMSA", "UNIVALLE", "ProSalud")
        val lugaresCB = arrayOf("Sennfeld", "Pacata", "ProSalud", "COMBASE")

                val arrayLgs =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lugaresLP)
                spinnerLugaresVac.adapter = arrayLgs
    }
}