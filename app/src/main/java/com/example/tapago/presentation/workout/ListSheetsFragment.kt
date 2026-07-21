package com.example.tapago.presentation.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tapago.R
import com.example.tapago.common.navigateSafe
import com.example.tapago.common.snackbar
import com.example.tapago.databinding.FragmentListSheetWorkoutBinding
import kotlinx.coroutines.launch

class ListSheetsFragment : Fragment() {
    private var _binding: FragmentListSheetWorkoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var sheetAdapter: SheetAdapter

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

        setupRecyclerView()
        observeViewModel()
        navigateNewSheet()
        viewModel.getSheet()

        setFragmentResultListener("register_sheet") {_, bundle ->
            val message = bundle.getString("message")
            if(message != null){
                snackbar(message)
            }
        }
    }

    private fun setupRecyclerView() {
        sheetAdapter = SheetAdapter { sheetClicada ->

        }
        binding.listSheetWorkoutRv.apply {
            adapter = sheetAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collect { state ->
                    state.sheets?.let { listaFichas ->
                        sheetAdapter.submitList(listaFichas)
                    }
                }
            }
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