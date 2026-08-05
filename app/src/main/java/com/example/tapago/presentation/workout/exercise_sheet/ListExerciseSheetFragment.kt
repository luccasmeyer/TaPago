package com.example.tapago.presentation.workout.exercise_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tapago.common.observe
import com.example.tapago.common.popBackStackSafe
import com.example.tapago.databinding.FragmentListExerciseSheetBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListExerciseSheetFragment : Fragment() {
    private var _binding: FragmentListExerciseSheetBinding? = null
    val binding get() = _binding!!
    private val viewModel: ListExerciseSheetViewModel by viewModel()
    private val args: ListExerciseSheetFragmentArgs by navArgs()
    private val listExerciseSheetAdapter by lazy { ListExerciseSheetAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListExerciseSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBarMt.setOnClickListener { popBackStackSafe() }
        setupRecyclerView()
        viewModel.getWorkout(args.idSheet)

        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.uiState) { state ->
            binding.nameSheetTv.text = state.workout?.nameSheet
            listExerciseSheetAdapter.submitList(state.workout?.listExercise)
        }
    }

    private fun setupRecyclerView() {
        binding.listExerciseSheetRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listExerciseSheetAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}