package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListPatchBinding

class CommitPatchAdapter(
        private val patches: List<String>
) : RecyclerView.Adapter<CommitPatchAdapter.CommitPatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitPatchViewHolder {
        val binding: ItemListPatchBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_patch, parent, false)
        return CommitPatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommitPatchViewHolder, position: Int) {
        holder.setInfo(patches[position])
    }

    override fun getItemCount(): Int = patches.size


    inner class CommitPatchViewHolder private constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private lateinit var binding: ItemListPatchBinding
        private lateinit var textView: TextView

        constructor(binding: ItemListPatchBinding) : this(binding.root) {
            this.binding = binding
            textView = itemView.findViewById(R.id.item_list_patch_text)
        }

        fun setInfo(data: String) {
            binding.patchItem = data
            when {
                data.startsWith("@@") -> {
                    itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.patch_info))
                    textView.gravity = Gravity.CENTER
                }
                data.startsWith("-") -> {
                    itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.patch_minus))
                    textView.gravity = Gravity.START
                }
                data.startsWith("+") -> {
                    itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.patch_plus))
                    textView.gravity = Gravity.START
                }
                else -> itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.transparent))
            }
        }
    }
}