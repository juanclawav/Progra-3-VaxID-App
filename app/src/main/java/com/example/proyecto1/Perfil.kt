package com.example.proyecto1

import android.graphics.Bitmap
import android.net.Uri
import com.google.gson.annotations.Expose

data class Perfil(
    @Expose var fotoPerfil: Uri?,
    @Expose var certificado: Uri?,
    @Expose var nombres: String,
    @Expose var apellidos: String,
    @Expose var hora: String,
    @Expose var qrCode : Bitmap?,
    @Expose var lugar : String?,
    @Expose var depto : String?
)
