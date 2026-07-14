package com.example.tapago.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tapago.common.popBackStackSafe
import com.example.tapago.databinding.FragmentRegisterProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterProfileFragment: Fragment() {

    private var _binding: FragmentRegisterProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topBarMt.setOnClickListener { popBackStackSafe() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}