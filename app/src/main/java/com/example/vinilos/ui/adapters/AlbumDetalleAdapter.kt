package com.example.vinilos.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R
import com.example.vinilos.databinding.DetalleAlbumItemBinding
import com.example.vinilos.models.Album

class AlbumDetalleAdapter : RecyclerView.Adapter<AlbumDetalleAdapter.AlbumDetalleViewHolder>(){

    var album :Album? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetalleViewHolder {
        val withDataBinding: DetalleAlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumDetalleViewHolder.LAYOUT,
            parent,
            false)
        return AlbumDetalleViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumDetalleViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = album
        }
        album?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return 1
    }


    class AlbumDetalleViewHolder(val viewDataBinding: DetalleAlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detalle_album_item
        }

        fun bind(album: Album) {
            Glide.with(itemView)
                .load(album.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)

                        .error(R.drawable.ic_broken_image))
                .into(viewDataBinding.albumCover)
        }

    }


}