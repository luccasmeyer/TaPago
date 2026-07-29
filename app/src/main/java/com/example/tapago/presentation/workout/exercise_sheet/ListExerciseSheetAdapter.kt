package com.example.tapago.presentation.workout.exercise_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tapago.databinding.LayoutExerciseSheetItemBinding
import com.example.tapago.domain.model.workout.WorkoutExercise

class ListExerciseSheetAdapter : ListAdapter<WorkoutExercise, ListExerciseSheetAdapter.WorkoutViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val binding = LayoutExerciseSheetItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WorkoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class WorkoutViewHolder(private val binding: LayoutExerciseSheetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WorkoutExercise) {

            val qtdSets = item.listSets.size + 1

            binding.nameExerciseTv.text = item.nameExercise
            binding.groupMuscleTv.text = item.typeExercise
            binding.setsNumberTv.text = "${qtdSets} Series"
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<WorkoutExercise>() {
        override fun areItemsTheSame(oldItem: WorkoutExercise, newItem: WorkoutExercise): Boolean {
            return oldItem.nameExercise == newItem.nameExercise
        }

        override fun areContentsTheSame(oldItem: WorkoutExercise, newItem: WorkoutExercise): Boolean {
            return oldItem == newItem
        }
    }
}