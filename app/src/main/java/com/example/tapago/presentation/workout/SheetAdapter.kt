package com.example.tapago.presentation.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tapago.databinding.LayoutSheetWorkoutItemBinding
import com.example.tapago.domain.model.Sheet // Import do seu modelo

class SheetAdapter(
    private val onItemClick: ((Int) -> Unit)? = null
) : ListAdapter<Sheet, SheetAdapter.SheetViewHolder>(SheetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SheetViewHolder {
        val binding = LayoutSheetWorkoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SheetViewHolder, position: Int) {
        val sheet = getItem(position)
        holder.bind(sheet)
    }

    inner class SheetViewHolder(
        private val binding: LayoutSheetWorkoutItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(sheet: Sheet) {
            binding.nameSheetTv.text = sheet.nameSheet

            binding.startWorkoutMb.setOnClickListener {
                onItemClick?.invoke(sheet.idSheet)
            }
        }
    }

    class SheetDiffCallback : DiffUtil.ItemCallback<Sheet>() {
        override fun areItemsTheSame(oldItem: Sheet, newItem: Sheet): Boolean {
            return oldItem.idSheet == newItem.idSheet
        }

        override fun areContentsTheSame(oldItem: Sheet, newItem: Sheet): Boolean {
            return oldItem == newItem
        }
    }
}