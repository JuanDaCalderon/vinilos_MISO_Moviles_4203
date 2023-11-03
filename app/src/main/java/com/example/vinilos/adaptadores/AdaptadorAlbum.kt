package com.example.vinilos.adaptadores

import ImageLoadTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.example.vinilos.R
import com.example.vinilos.modelos.Album


class AdaptadorAlbum : RecyclerView.Adapter<AdaptadorAlbum.ViewHolder>() {

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(
            R.layout.album_card,
            parent,
            false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewNombre.text = albums[position].name
        holder.textViewDescripcion.text = albums[position].description
        ImageLoadTask(albums[position].cover, holder.imageView).execute()

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "album ${albums[position].name}", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var textViewNombre : TextView = itemView.findViewById(R.id.album_nombre)
        var textViewDescripcion : TextView = itemView.findViewById(R.id.album_descripcion)
        var imageView : ImageView = itemView.findViewById(R.id.album_image)
    }
}