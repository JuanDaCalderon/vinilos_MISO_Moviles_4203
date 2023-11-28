package com.example.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.DetalleCollectorFragmentBinding
import com.example.vinilos.ui.adapters.CollectorDetalleAdapter
import com.example.vinilos.viewmodels.CollectorDetalleViewModel

class CollectorDetalleFragment : Fragment() {

    private var _binding: DetalleCollectorFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorDetalleViewModel
    private var viewModelAdapter: CollectorDetalleAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DetalleCollectorFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CollectorDetalleAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistasByCollectorRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter


        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        val collectorNameET = activity.findViewById<TextView>(R.id.collectorName)
        val collectorTelephoneET = activity.findViewById<TextView>(R.id.collectorTelephone)
        val collectorMailET = activity.findViewById<TextView>(R.id.collectorMail)

        activity.actionBar?.title = getString(R.string.title_detalle_coleccionistas)
        val args: CollectorDetalleFragmentArgs by navArgs()

        viewModel = ViewModelProvider(this, CollectorDetalleViewModel.Factory(activity.application, args.collectorId))[CollectorDetalleViewModel::class.java]

       viewModel.artistas.observe(viewLifecycleOwner) {
           it.apply {
               viewModelAdapter!!.artistas = this
           }
       }
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        viewModel.collectors.observe(viewLifecycleOwner) {
            it.apply {
                collectorNameET.text = viewModel.collectors.value?.name
                collectorTelephoneET.text =
                    getString(R.string.numero_de_telefono, viewModel.collectors.value?.telephone)
                collectorMailET.text =
                    getString(R.string.correo_electronico, viewModel.collectors.value?.email)
            }
        }
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