package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.DetalleArtistaFragmentBinding
import com.example.vinilos.models.Artista
import com.example.vinilos.ui.adapters.ArtistaDetalleAdapter
import com.example.vinilos.viewmodels.ArtistaDetalleViewModel


class ArtistaDetalleFragment : Fragment() {
    private var _binding: DetalleArtistaFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ArtistaDetalleViewModel
    private var viewModelAdapter: ArtistaDetalleAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetalleArtistaFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ArtistaDetalleAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.detalleArtistaRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_detalle_artista)
        val args: ArtistaDetalleFragmentArgs by navArgs()
        Log.d("Args", args.artistaId.toString())
        viewModel = ViewModelProvider(this, ArtistaDetalleViewModel.Factory(activity.application, args.artistaId)).get(ArtistaDetalleViewModel::class.java)
        viewModel.artista.observe(viewLifecycleOwner, Observer<Artista> {
            it.apply {
                viewModelAdapter!!.artista = this
                if(this != null){
                    binding.txtNoArtistas.visibility = View.GONE
                } else {
                    binding.txtNoArtistas.visibility = View.VISIBLE
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