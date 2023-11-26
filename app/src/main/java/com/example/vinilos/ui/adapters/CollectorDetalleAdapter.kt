package com.example.vinilos.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R
import com.example.vinilos.databinding.ArtistasItemBinding
import com.example.vinilos.models.Artista

class CollectorDetalleAdapter : RecyclerView.Adapter<CollectorDetalleAdapter.CollectorDetalleViewHolder>(){

    var artistas :List<Artista> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorDetalleViewHolder {
        val withDataBinding: ArtistasItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorDetalleViewHolder.LAYOUT,
            parent,
            false)
        return CollectorDetalleViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorDetalleViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artista = artistas[position]
        }
        holder.bind(artistas[position])
    }

    override fun getItemCount(): Int {
        return artistas.size
    }


    class CollectorDetalleViewHolder(val viewDataBinding: ArtistasItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artistas_item
        }
        fun bind(artista: Artista) {
            Glide.with(itemView)
                .load(artista.image.toUri().buildUpon().scheme("https").build())
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_broken_image))
                .into(viewDataBinding.artistaCover)
        }
    }
}