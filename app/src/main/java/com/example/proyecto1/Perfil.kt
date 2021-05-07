package com.example.proyecto1

import android.net.Uri
import com.google.gson.annotations.Expose

data class Perfil(
    @Expose val fotoPerfil: Uri?,
    @Expose val nombres: String,
    @Expose val apellidos: String,
    @Expose var hora: String,
    @Expose var cantidad: Int = 1
)
