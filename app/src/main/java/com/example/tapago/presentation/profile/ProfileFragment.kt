package com.example.tapago.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tapago.R
import androidx.fragment.app.Fragment
import com.example.tapago.common.navigateSafe
import com.example.tapago.common.observe
import com.example.tapago.common.popBackStackSafe
import com.example.tapago.common.showRegisterDialog
import com.example.tapago.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfile()
        observe(viewModel.uiState) { state ->
            if (!state.isLoading) {
                if (state.profile == null) {
                    showRegisterDialog(
                        onRegisterClick = { navigateSafe(R.id.actionRegisterProfile) },
                        onSkipClick = { popBackStackSafe() }
                    )
                }
            }

            state.profile?.let { profile ->
                binding.nameProfileTv.text = profile.name
                binding.weightInfoTv.text = profile.weight.toString()
                binding.heightInfoTv.text = profile.height.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}