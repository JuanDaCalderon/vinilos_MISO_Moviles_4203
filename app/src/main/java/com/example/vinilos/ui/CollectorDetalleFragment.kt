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
import com.example.vinilos.databinding.DetalleCollectorFragmentBinding
import com.example.vinilos.models.Collector
import com.example.vinilos.ui.adapters.CollectorDetalleAdapter
import com.example.vinilos.viewmodels.CollectorDetalleViewModel


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
        recyclerView = binding.detalleCollectorRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_detalle_Collectors)
        val args: CollectorDetalleFragmentArgs by navArgs()
        Log.d("Args", args.colIectorId.toString())
        viewModel = ViewModelProvider(this, CollectorDetalleViewModel.Factory(activity.application, args.colIectorId)).get(CollectorDetalleViewModel::class.java)
        viewModel.collector.observe(viewLifecycleOwner, Observer<Collector> {
            it.apply {
                viewModelAdapter!!.collector = this
                if(this != null){
                    binding.txtNoCollector.visibility = View.GONE
                } else {
                    binding.txtNoCollector.visibility = View.VISIBLE
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