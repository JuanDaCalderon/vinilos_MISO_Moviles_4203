package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.FragmentDetalleAlbumBinding
import com.example.vinilos.modelos.Album
import com.example.vinilos.ui.adaptadores.AdaptadorDetalleAlbum
import com.example.vinilos.viewmodels.AlbumDetalleViewModel

class AlbumsDetalleFragment : Fragment() {

    private var _binding: FragmentDetalleAlbumBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumDetalleViewModel
    private var viewModelAdapter: AdaptadorDetalleAlbum? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = this.requireActivity().application)).get(AlbumDetalleViewModel::class.java)
        _binding = FragmentDetalleAlbumBinding.inflate(inflater, container, false)

        val view: View = binding.root
        viewModelAdapter = AdaptadorDetalleAlbum()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerViewDetalleAlbum
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = "Detalle Album"


        val args: AlbumsDetalleFragmentArgs by navArgs()
        Log.d("ARGS", args.albumId.toString())

        viewModel.albums.observe(viewLifecycleOwner, Observer<Album> {
            it.apply {
                Log.d("ALBUM DETALLE", this.toString())
                viewModelAdapter!!.album = this
                if(this==null){
                    binding.txtNoAlbum.visibility = View.VISIBLE
                }else{
                    binding.txtNoAlbum.visibility = View.GONE
                }
            }
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}