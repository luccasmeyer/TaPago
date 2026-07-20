package com.example.tapago.presentation.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.tapago.databinding.LayoutExerceiseAddedItemBinding
import com.example.tapago.domain.model.Exercise
import com.example.tapago.domain.model.workout.WorkoutExercise

class AddedExerciseAdapter: ListAdapter<WorkoutExercise, AddedExerciseAdapter.ExerciseViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = LayoutExerceiseAddedItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ExerciseViewHolder(private val binding: LayoutExerceiseAddedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: WorkoutExercise) {
            binding.exerciseNameTv.text = exercise.nameExercise
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<WorkoutExercise>() {
        override fun areItemsTheSame(oldItem: WorkoutExercise, newItem: WorkoutExercise) =
            oldItem.nameExercise == newItem.nameExercise

        override fun areContentsTheSame(oldItem: WorkoutExercise, newItem: WorkoutExercise) =
            oldItem == newItem
    }
}
