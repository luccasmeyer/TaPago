package com.example.tapago.presentation.workout.exercise

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.tapago.common.observe
import com.example.tapago.common.popBackStackSafe
import com.example.tapago.common.snackbar
import com.example.tapago.data.entities.ExercisesEntity
import com.example.tapago.databinding.FragmentRegisterExerciseBinding
import com.example.tapago.domain.common.TypeExercise
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterExerciseFragment : Fragment() {
    private var _binding: FragmentRegisterExerciseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterExerciseViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBarMt.setOnClickListener { popBackStackSafe() }
        buildDropDown()
        registerExercise()

        observe(viewModel.uiState) { state ->
            if (state.message.isNotBlank()) {
                setFragmentResult(
                    "register_request",
                    bundleOf("message" to state.message)
                )
                popBackStackSafe()
            }
        }
    }

    fun registerExercise() {
        binding.saveExerciseBtn.setOnClickListener {
            val nameExercise = binding.nameSheetEt.text.toString()
            val groupMuscle = binding.selectMuscleGroupAcv.text.toString()

            if (nameExercise.isEmpty() || groupMuscle.isEmpty()) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Aviso")
                    .setMessage("Você preciso terminar o cadastro")
                    .setPositiveButton("OK", null).show()
            } else {
                val exercise = ExercisesEntity(
                    nameExercise = nameExercise,
                    typeExrcise = groupMuscle
                )

                viewModel.registerExercise(exercise)
            }
        }
    }

    private fun buildDropDown() {
        val listExercise = TypeExercise.getAllTranslatedNames()
        val adapterDropdown = ArrayAdapter(
            requireContext(), R.layout.simple_dropdown_item_1line, listExercise
        )

        binding.selectMuscleGroupAcv.setAdapter(adapterDropdown)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}