package com.example.vinilos.ui.coleccionistas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.vinilos.databinding.FragmentColeccionistasBinding

class ColeccionistasFragment : Fragment() {

    private var _binding: FragmentColeccionistasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val coleccionsitaViewModel =
            ViewModelProvider(this).get(ColeccionistasViewModel::class.java)

        _binding = FragmentColeccionistasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textColeccionistas
        coleccionsitaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val crearAlbumButton:Button =

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}