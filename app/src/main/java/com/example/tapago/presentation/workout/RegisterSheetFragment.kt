package com.example.tapago.presentation.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tapago.databinding.FragmentRegisterSheetBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterSheetFragment: Fragment() {
    private var _binding: FragmentRegisterSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterSheetViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterSheetBinding.inflate(inflater, container, false)
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