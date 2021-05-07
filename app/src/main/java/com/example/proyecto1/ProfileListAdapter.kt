package com.example.proyecto1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfileListRecyclerViewAdapter (val listaPerfiles: MutableList<Perfil>): RecyclerView.Adapter<ViewHolder>() {

    var clickListener: ((perfil: Perfil) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_item_perfil, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaPerfiles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listaPerfiles.get(position))
        holder.itemView.setOnClickListener {
            clickListener?.invoke(listaPerfiles.get(position))
        }
    }
    fun setOnProfileClickListener(profileClickListener: (profile: Perfil) -> Unit ){
        clickListener = profileClickListener
    }
    fun addProfile(profile: Perfil){
        listaPerfiles.add(profile)
        notifyDataSetChanged()
    }
}
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(profile: Perfil) {
            itemView.findViewById<TextView>(R.id.textViewNombresPerfil).text = profile.nombres + profile.apellidos
            itemView.findViewById<ImageView>(R.id.imageViewFotoPerfil).setImageURI(profile.fotoPerfil)
        }
}