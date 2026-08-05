package com.example.tapago.presentation.workout.exercise_sheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tapago.R
import com.example.tapago.databinding.LayoutExerciseSheetItemBinding
import com.example.tapago.domain.model.workout.WorkoutExercise
import androidx.core.view.isVisible

class ListExerciseSheetAdapter :
    ListAdapter<WorkoutExercise, ListExerciseSheetAdapter.WorkoutViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val binding = LayoutExerciseSheetItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WorkoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class WorkoutViewHolder(private val binding: LayoutExerciseSheetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val setsAdapter = SetsAdapter()

        init {
            binding.infoSetsRv.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = setsAdapter
            }
        }

        fun bind(item: WorkoutExercise) {
            binding.nameExerciseTv.text = item.nameExercise.replaceFirstChar { it.uppercase() }
            binding.groupMuscleTv.text = item.typeExercise
            binding.setsNumberTv.text = "${item.qtdSets} Series"

            setsAdapter.submitSetsCount(item.qtdSets)

            binding.startExerciseBt.setOnClickListener {
                val isCurrentlyVisible = binding.infoSetsRv.isVisible

                if (isCurrentlyVisible) {
                    binding.startExerciseBt.setIconResource(R.drawable.chevron_right_24dp)
                    binding.infoSetsRv.visibility = View.GONE
                } else {
                    binding.startExerciseBt.setIconResource(R.drawable.keyboard_arrow_down_24dp)
                    binding.infoSetsRv.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<WorkoutExercise>() {
        override fun areItemsTheSame(oldItem: WorkoutExercise, newItem: WorkoutExercise) =
            oldItem.nameExercise == newItem.nameExercise

        override fun areContentsTheSame(oldItem: WorkoutExercise, newItem: WorkoutExercise) =
            oldItem == newItem
    }
}