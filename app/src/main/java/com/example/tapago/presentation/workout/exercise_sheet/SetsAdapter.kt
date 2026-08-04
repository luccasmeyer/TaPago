package com.example.tapago.presentation.workout.exercise_sheet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tapago.databinding.LayoutSetsExerciseItemBinding

class SetsAdapter : RecyclerView.Adapter<SetsAdapter.SetViewHolder>() {
    private var setsCount = 0

    fun submitSetsCount(count: Int) {
        this.setsCount = count
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
        holder.bind(position + 1)
    }

    override fun getItemCount(): Int = setsCount

    inner class SetViewHolder(private val binding: LayoutSetsExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(setNumber: Int) {
            binding.numberSetTv.text = setNumber.toString()
        }
    }
}