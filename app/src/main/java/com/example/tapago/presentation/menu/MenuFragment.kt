package com.example.tapago.presentation.menu

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.tapago.MainActivity
import com.example.tapago.R
import com.example.tapago.common.navigateSafe
import com.example.tapago.common.observe
import com.example.tapago.common.snackbar
import com.example.tapago.databinding.FragmentMenuAppBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment() {

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWorkout()

        navigateWorkout()

        observe(viewModel.uiState) { state ->

            if (state.isError) {
                snackbar(state.message.toString())
            }

            binding.nameSheetTodayTv.text = state.sheetDay?.nameSheet
        }
    }

    private fun navigateWorkout(){
        binding.startSheetTodayBt.setOnClickListener {
            navigateSafe(R.id.actionMenuToWorkout, bundleOf(
                "idSheet" to viewModel.uiState.value.sheetDay?.idSheet
            ))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}