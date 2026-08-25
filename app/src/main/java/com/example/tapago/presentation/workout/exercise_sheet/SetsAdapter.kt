package com.example.tapago.presentation.workout.exercise_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tapago.R
import com.example.tapago.databinding.LayoutSetsExerciseItemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

data class ExerciseSetState(
    var reps: Int = 0,
    var weight: Double = 0.0
)

class SetsAdapter : RecyclerView.Adapter<SetsAdapter.SetViewHolder>() {
    private var setsList = mutableListOf<ExerciseSetState>()

    fun submitSetsCount(count: Int) {
        setsList.clear()
        for (i in 0 until count) {
            setsList.add(ExerciseSetState())
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val binding = LayoutSetsExerciseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        holder.bind(position + 1, setsList[position])
    }

    override fun getItemCount(): Int = setsList.size

    inner class SetViewHolder(private val binding: LayoutSetsExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(setNumber: Int, setState: ExerciseSetState) {
            binding.numberSetsTv.text = setNumber.toString()
            binding.repsNumberBt.text = "${setState.reps} Reps"
            binding.weightNumberBt.text = "${setState.weight} Kg"

            binding.editSetExercise.setOnClickListener {
                val context = binding.root.context
                val dialogView = LayoutInflater.from(context)
                    .inflate(R.layout.layout_modal_edit_set, null)

                val dialog = MaterialAlertDialogBuilder(context)
                    .setView(dialogView)
                    .create()

                val btnCancel = dialogView.findViewById<Button>(R.id.close_dialog_bt)
                val btnPlusRep = dialogView.findViewById<Button>(R.id.rep_plus_bt)
                val btnLessRep = dialogView.findViewById<Button>(R.id.rep_less_bt)
                val labelNumRep = dialogView.findViewById<TextView>(R.id.rep_tv)

                val btnPlusWeight = dialogView.findViewById<Button>(R.id.weight_plus_bt)
                val btnLessWeight = dialogView.findViewById<Button>(R.id.weight_less_bt)
                val labelWeight = dialogView.findViewById<TextView>(R.id.weight_tv)

                var tempReps = setState.reps
                var tempWeight = setState.weight

                labelNumRep.text = "$tempReps"
                labelWeight.text = "$tempWeight"

                btnPlusRep.setOnClickListener {
                    tempReps += 1
                    labelNumRep.text = "$tempReps"
                }
                btnLessRep.setOnClickListener {
                    if (tempReps > 0) tempReps -= 1
                    labelNumRep.text = "$tempReps"
                }

                btnPlusWeight.setOnClickListener {
                    tempWeight += 0.5
                    labelWeight.text = "$tempWeight"
                }
                btnLessWeight.setOnClickListener {
                    if (tempWeight > 0.0) tempWeight -= 0.5
                    labelWeight.text = "$tempWeight"
                }

                btnCancel.setOnClickListener {
                    setState.reps = tempReps
                    setState.weight = tempWeight

                    binding.repsNumberBt.text = "${setState.reps} Reps"
                    binding.weightNumberBt.text = "${setState.weight} Kg"

                    dialog.dismiss()
                }

                dialog.show()
            }
        }
    }
}