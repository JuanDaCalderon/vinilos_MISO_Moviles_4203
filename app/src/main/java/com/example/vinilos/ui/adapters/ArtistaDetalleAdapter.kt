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
import com.example.vinilos.databinding.DetalleArtistaItemBinding
import com.example.vinilos.models.Artista

class ArtistaDetalleAdapter : RecyclerView.Adapter<ArtistaDetalleAdapter.ArtistaDetalleViewHolder>(){

    var artista :Artista? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaDetalleViewHolder {
        val withDataBinding: DetalleArtistaItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistaDetalleViewHolder.LAYOUT,
            parent,
            false)
        return ArtistaDetalleViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistaDetalleViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artista = artista
        }
        artista?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return 1
    }


    class ArtistaDetalleViewHolder(val viewDataBinding: DetalleArtistaItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detalle_artista_item
        }

        fun bind(artista: Artista) {
            Glide.with(itemView)
                .load(artista.image.toUri().buildUpon().scheme("https").build())
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_broken_image))
                .into(viewDataBinding.artistaImage)
        }

    }


}