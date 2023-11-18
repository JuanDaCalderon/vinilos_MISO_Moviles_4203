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
import com.example.vinilos.databinding.CollectorItemBinding
import com.example.vinilos.models.Collector

class CollectorDetalleAdapter : RecyclerView.Adapter<CollectorDetalleAdapter.CollectorDetalleViewHolder>(){

    var collector :Collector? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorDetalleViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorDetalleViewHolder.LAYOUT,
            parent,
            false)
        return CollectorDetalleViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorDetalleViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collector
        }
        collector?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return 1
    }


    class CollectorDetalleViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }

        fun bind(collector: Collector) {
            Glide.with(itemView)
                .load(collector.image.toUri().buildUpon().scheme("https").build())
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_broken_image))
                .into(viewDataBinding.collectorImage)
        }

    }


}