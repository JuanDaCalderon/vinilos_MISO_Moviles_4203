package com.example.vinilos.ui.adaptadores

import ImageLoadTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.vinilos.R
import com.example.vinilos.modelos.Album


class AdaptadorDetalleAlbum : RecyclerView.Adapter<AdaptadorDetalleAlbum.ViewHolder>() {

    var album : Album? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(
            R.layout.album_detalle,
            parent,
            false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewNombre.text = album?.name
        holder.textViewDescripcion.text = album?.description
        holder.textViewRelease.text = album?.releaseDate
        holder.textViewGenero.text = album?.genre
        holder.textViewRecordLabel.text = album?.recordLabel

        //album?.cover?.let { ImageLoadTask(it, holder.imageView).execute() }
    }

    override fun getItemCount(): Int {
        return 1
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var textViewNombre : TextView = itemView.findViewById(R.id.nombre_album)
        var textViewDescripcion : TextView = itemView.findViewById(R.id.descripcion_album)
        var imageView : ImageView = itemView.findViewById(R.id.portada_album)
        var textViewRelease : TextView = itemView.findViewById(R.id.release_album)
        var textViewGenero : TextView = itemView.findViewById(R.id.genero_album)
        var textViewRecordLabel : TextView = itemView.findViewById(R.id.recordLabel_album)
    }
}