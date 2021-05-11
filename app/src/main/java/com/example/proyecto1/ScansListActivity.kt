package com.example.proyecto1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_scans_list.*

class ScansListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scans_list)
        val adapter = ProfileListRecyclerViewAdapter(TemporalStorage.listaPerfiles)

        recyclerViewPerfiles.adapter = adapter
        recyclerViewPerfiles.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        btnBack3.setOnClickListener {
           finish()
        }
    }
}