package com.example.vinilos

import ImageLoadTask
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class AdaptadorAlbum(var lista: ArrayList<Album>, var context: Context): RecyclerView.Adapter<AdaptadorAlbum.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var textViewNombre : TextView = itemView.findViewById(R.id.album_nombre)
        var textViewDescripcion : TextView = itemView.findViewById(R.id.album_descripcion)
        var imageView : ImageView = itemView.findViewById(R.id.album_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.album_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewNombre.text = lista.get(position).name
        holder.textViewDescripcion.text = lista.get(position).description
        ImageLoadTask(lista.get(position).cover, holder.imageView).execute()

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "album ${lista.get(position).name}", Toast.LENGTH_LONG).show()
        }
    }

}