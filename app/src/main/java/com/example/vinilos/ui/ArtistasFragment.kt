package com.example.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentArtistasBinding
import com.example.vinilos.viewmodels.ArtistasViewModel

class ArtistasFragment : Fragment() {
    private var _binding: FragmentArtistasBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ArtistasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(ArtistasViewModel::class.java)
        _binding = FragmentArtistasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_artistas)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}