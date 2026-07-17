package com.example.tapago.presentation.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.buildObjectIntMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.tapago.R
import com.example.tapago.common.navigateSafe
import com.example.tapago.common.popBackStackSafe
import com.example.tapago.common.snackbar
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

        binding.topBarMt.setOnClickListener { popBackStackSafe() }
        navigateRegisterExercise()

        setFragmentResultListener("register_request") { requestKey, bundle ->
            val message = bundle.getString("message")
            if(message != null){
                snackbar(message)
            }
        }
    }

    private fun navigateRegisterExercise(){
        binding.registerExerciseBt.setOnClickListener {
            navigateSafe(R.id.actionRegisterSheetToRegisterExercise)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}