package com.example.tapago.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tapago.R
import com.example.tapago.common.navigateSafe
import com.example.tapago.common.popBackStackSafe
import com.example.tapago.data.entities.ProfileEntity
import com.example.tapago.databinding.FragmentRegisterProfileBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        registerProfile()
    }

    private fun registerProfile() {
        binding.registerProfileBt.setOnClickListener {
            val nameText = binding.nameProfileEt.text.toString()
            val weightText = binding.weightEt.text.toString()
            val heightText = binding.heightEt.text.toString()

            val weight = weightText.toDoubleOrNull() ?: 0.0
            val height = heightText.toDoubleOrNull() ?: 0.0

            when {
                nameText.isBlank() && weight == 0.0 && height == 0.0 -> {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Aviso")
                        .setMessage("Você precisa preencher as informações")
                        .setPositiveButton("OK", null).show()
                    return@setOnClickListener
                }
                nameText.isBlank() -> {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Aviso")
                        .setMessage("Você precisa preencher seu nome")
                        .setPositiveButton("OK", null).show()
                    return@setOnClickListener
                }
                weight == 0.0 -> {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Aviso")
                        .setMessage("Você precisa preencher seu peso")
                        .setPositiveButton("OK", null).show()
                    return@setOnClickListener
                }
                height == 0.0 -> {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Aviso")
                        .setMessage("Você precisa preencher sua altura")
                        .setPositiveButton("OK", null).show()
                    return@setOnClickListener
                }
            }

            val profileEntity = ProfileEntity(
                nameProfile = nameText,
                weightProfile = weight,
                heightProfile = height
            )

            viewModel.postProfile(profileEntity)
            navigateSafe(R.id.actionRegisterProfileToProfile)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}