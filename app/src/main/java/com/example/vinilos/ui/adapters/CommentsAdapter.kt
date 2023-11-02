package com.example.vinilos.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumItemBinding
import com.example.vinilos.models.Album
import com.example.vinilos.models.Comment

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    var comments: Album? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CommentViewHolder.LAYOUT,
            parent,
            false
        )
        return CommentViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = comments
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

        class CommentViewHolder(val viewDataBinding: AlbumItemBinding) :
            RecyclerView.ViewHolder(viewDataBinding.root) {
            companion object {
                @LayoutRes
                val LAYOUT = R.layout.album_item
            }
        }
}
