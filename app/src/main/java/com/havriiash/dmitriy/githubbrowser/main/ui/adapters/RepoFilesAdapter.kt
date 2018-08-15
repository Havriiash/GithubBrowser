package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListRepoFileBinding
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

class RepoFilesAdapter(
        private val files: List<Repo.File>,
        private val itemClickListener: ItemClickListener<Repo.File>?
): RecyclerView.Adapter<RepoFilesAdapter.RepoFilesViewHolder>() {

    private var sortedList: ArrayList<Repo.File> = ArrayList(files.size)

    init {
         sortByTypes()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoFilesViewHolder {
        val binding: ItemListRepoFileBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_repo_file, parent, false)
        return RepoFilesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoFilesViewHolder, position: Int) {
        holder.setInfo(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size

    private fun sortByTypes() {
        sortedList.addAll(files)
        sortedList.sortBy { file -> file.type }
    }


    inner class RepoFilesViewHolder private constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private lateinit var binding: ItemListRepoFileBinding

        constructor(binding: ItemListRepoFileBinding): this(binding.root) {
            this.binding = binding
        }

        fun setInfo(data: Repo.File) {
            itemView.setOnClickListener { itemClickListener?.onItemClick(data) }
            binding.file = data
        }
    }
}