package com.example.proyecto1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_profile.*

class ViewProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)
        btnBack6.setOnClickListener{
            finish()
        }
        imageViewPerfil.setImageURI(TemporalStorage.listaPerfiles[0].fotoPerfil)
        imageViewQR.setImageBitmap(TemporalStorage.listaPerfiles[0].qrCode)
        textViewNmb.setText(TemporalStorage.listaPerfiles[0].nombres+" "+TemporalStorage.listaPerfiles[0].apellidos)
    }
}