package com.example.tapago.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    private fun registerProfile(){
        binding.registerProfileBt.setOnClickListener {

            val profileEntity = ProfileEntity(
                nameProfile = binding.nameProfileEt.text.toString(),
                weightProfile = binding.weightEt.text.toString().toDouble(),
                heightProfile = binding.heightEt.text.toString().toDouble()
            )

            if(profileEntity.nameProfile.isBlank() && profileEntity.weightProfile.toInt() == 0 && profileEntity.heightProfile.toInt() == 0){
                MaterialAlertDialogBuilder(requireContext()).setTitle("Aviso")
                    .setMessage("Você precisa preencher as informações")
                    .setPositiveButton("OK", null).show()
            } else {
                when{
                    profileEntity.nameProfile.isBlank() -> {
                        MaterialAlertDialogBuilder(requireContext()).setTitle("Aviso")
                            .setMessage("Você precisa preencher seu nome")
                            .setPositiveButton("OK", null).show()
                    }
                    profileEntity.weightProfile.toInt() == 0 -> {
                        MaterialAlertDialogBuilder(requireContext()).setTitle("Aviso")
                            .setMessage("Você precisa preencher seu peso")
                            .setPositiveButton("OK", null).show()
                    }
                    profileEntity.heightProfile.toInt() == 0 -> {
                        MaterialAlertDialogBuilder(requireContext()).setTitle("Aviso")
                            .setMessage("Você precisa preencher sua altura")
                            .setPositiveButton("OK", null).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}