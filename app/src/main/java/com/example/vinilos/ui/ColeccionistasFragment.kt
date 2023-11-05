package com.example.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentColeccionistasBinding
import com.example.vinilos.viewmodels.ColeccionistasViewModel

class ColeccionistasFragment : Fragment() {
    private var _binding: FragmentColeccionistasBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ColeccionistasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(ColeccionistasViewModel::class.java)
        _binding = FragmentColeccionistasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_tracks)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}