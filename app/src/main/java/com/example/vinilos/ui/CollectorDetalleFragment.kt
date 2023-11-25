package com.example.vinilos.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumFragmentBinding
import com.example.vinilos.databinding.CollectorFragmentBinding
import com.example.vinilos.databinding.DetalleCollectorFragmentBinding
import com.example.vinilos.models.Album
import com.example.vinilos.models.Artista
import com.example.vinilos.ui.adapters.AlbumsAdapter
import com.example.vinilos.ui.adapters.ArtistasAdapter
import com.example.vinilos.ui.adapters.CollectorDetalleAdapter
import com.example.vinilos.viewmodels.AlbumViewModel
import com.example.vinilos.viewmodels.ArtistasViewModel
import com.example.vinilos.viewmodels.CollectorDetalleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CollectorDetalleFragment : Fragment() {

    private var _binding: DetalleCollectorFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorDetalleViewModel
    private var viewModelAdapter: CollectorDetalleAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetalleCollectorFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CollectorDetalleAdapter()
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistasByCollectorRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_detalle_coleccionistas)
        viewModel = ViewModelProvider(this, CollectorDetalleViewModel.Factory(activity.application,100)).get(CollectorDetalleViewModel::class.java)
        viewModel.artistas.observe(viewLifecycleOwner, Observer<List<Artista>> {
            it.apply {
                viewModelAdapter!!.artistas = this
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