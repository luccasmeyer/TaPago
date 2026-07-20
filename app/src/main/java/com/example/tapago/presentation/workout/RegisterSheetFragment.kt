package com.example.tapago.presentation.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tapago.R
import com.example.tapago.common.navigateSafe
import com.example.tapago.common.popBackStackSafe
import com.example.tapago.common.snackbar
import com.example.tapago.databinding.FragmentRegisterSheetBinding
import com.example.tapago.domain.model.workout.Workout
import com.example.tapago.domain.model.workout.WorkoutExercise
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterSheetFragment : Fragment() {
    private var _binding: FragmentRegisterSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterSheetViewModel by viewModel()
    private lateinit var searchAdapter: ArrayAdapter<String>
    private val addedExercisesAdapter by lazy { AddedExerciseAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchDropdown()
        observeViewModel()
        createSheet()

        binding.topBarMt.setOnClickListener { popBackStackSafe() }
        binding.registerExerciseBt.setOnClickListener {
            navigateSafe(R.id.actionRegisterSheetToRegisterExercise)
        }

        setFragmentResultListener("register_request") { _, bundle ->
            val message = bundle.getString("message")
            if (message != null) {
                snackbar(message)
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val namesDosExercises = state.listExercise.map { it.nameExercise }

                    searchAdapter.clear()
                    searchAdapter.addAll(namesDosExercises)
                    searchAdapter.notifyDataSetChanged()

                    addedExercisesAdapter.submitList(state.addedExercises)
                }
            }
        }
    }

    private fun createSheet() {
        binding.saveSheetBtn.setOnClickListener {

            if (binding.nameSheetEt.text!!.isEmpty()) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Aviso")
                    .setMessage("Preencha o nome da ficha")
                    .setPositiveButton("OK", null).show()
                return@setOnClickListener
            }

            val currentExercises = viewModel.uiState.value.addedExercises

            val workout = Workout(
                nameSheet = binding.nameSheetEt.text.toString(),
                qtdExercise = currentExercises.size,
                listExercise = currentExercises
            )

            viewModel.createSheet(workout)
        }
    }

    private fun setupSearchDropdown() {
        searchAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            mutableListOf()
        )
        binding.searchExerciseEt.setAdapter(searchAdapter)

        binding.searchExerciseEt.doOnTextChanged { text, _, _, _ ->
            val query = text?.toString() ?: ""
            if (query.isNotEmpty()) {
                viewModel.searchExercise(query)
            }
        }

        binding.searchExerciseEt.setOnItemClickListener { _, _, position, _ ->
            val selectedName = searchAdapter.getItem(position)

            val exerciseSelection = viewModel.uiState.value.listExercise.find {
                it.nameExercise == selectedName
            }

            exerciseSelection?.let { exercise ->
                viewModel.addExerciseToSheet(exercise)
            }

            binding.searchExerciseEt.text?.clear()
            binding.searchExerciseEt.clearFocus()
        }
    }

    private fun setupRecyclerView() {
        binding.exercisesRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = addedExercisesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}