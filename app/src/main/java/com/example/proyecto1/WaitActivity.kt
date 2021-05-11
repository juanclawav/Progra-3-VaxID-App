package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_wait.*

class WaitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isWaiting = true
        setContentView(R.layout.activity_wait)
        btnBack5.setOnClickListener {
          finish()
        }
        btnAccept2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("esperando", Gson().toJson(isWaiting))
            startActivity(intent)
        }
    }
}