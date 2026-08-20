package com.example.tapago.presentation.workout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tapago.R
import com.example.tapago.common.navigateSafe
import com.example.tapago.common.observe
import com.example.tapago.common.snackbar
import com.example.tapago.databinding.FragmentListSheetWorkoutBinding
import kotlinx.coroutines.launch

class ListSheetsFragment : Fragment() {
    private var _binding: FragmentListSheetWorkoutBinding? = null
    private val binding get() = _binding!!
    private val sheetAdapter by lazy {
        SheetAdapter { idSheet ->
            viewModel.beginWorkout(idSheet)
        }
    }

    private val viewModel: ListSheetsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListSheetWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSheet()
        setupRecyclerView()
        observeViewModel()
        navigateNewSheet()

        setFragmentResultListener("register_sheet") { _, bundle ->
            val message = bundle.getString("message")
            if (message != null) {
                snackbar(message)
            }
        }
    }

    private fun observeViewModel() {
        observe(viewModel.uiState) { state ->
            state.sheets?.let { listSheets ->
                sheetAdapter.submitList(listSheets)
            }

            if (state.isError) {
                snackbar(state.message!!)
            }

            state.navigateToSheetId?.let { idSheet ->
                navigateSafe(
                    R.id.actionWorkoutToExerciseSheet, bundleOf(
                        "idSheet" to idSheet
                    )
                )
                viewModel.onNavigationDone()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.listSheetWorkoutRv.apply {
            adapter = sheetAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun navigateNewSheet() {
        binding.addSheetFab.setOnClickListener {
            navigateSafe(R.id.actionWorkoutToRegisterWorkout)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.listSheetWorkoutRv.adapter = null
        _binding = null
    }
}