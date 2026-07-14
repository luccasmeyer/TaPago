package com.example.tapago.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tapago.databinding.FragmentMenuAppBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment: Fragment() {

    private var _binding: FragmentMenuAppBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MenuViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}