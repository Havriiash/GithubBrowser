package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListFilesExplorerBinding
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

class FilesExplorerAdapter(
        private val itemClickListener: ItemClickListener<String>?
) : RecyclerView.Adapter<FilesExplorerAdapter.FilesExplorerViewHolder>() {

    private val pathList: ArrayList<String> = arrayListOf("")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesExplorerViewHolder {
        val binding: ItemListFilesExplorerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_files_explorer, parent, false)
        return FilesExplorerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilesExplorerViewHolder, position: Int) {
        holder.setInfo(pathList[position])
    }

    override fun getItemCount(): Int = pathList.size

    fun addPath(path: String) {
        pathList.add(path)
        notifyItemInserted(pathList.size)
    }

    fun removeItemsToPath(path: String) {
        if (pathList.contains(path)) {
            val index = pathList.indexOf(path) + 1
            val delListPath: ArrayList<String> = ArrayList()
            for (i in index until pathList.size) {
                delListPath.add(pathList[i])
            }
            pathList.removeAll(delListPath)
            notifyDataSetChanged()
        }
    }


    inner class FilesExplorerViewHolder private constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private lateinit var binding: ItemListFilesExplorerBinding

        constructor(binding: ItemListFilesExplorerBinding) : this(binding.root) {
            this.binding = binding
        }

        fun setInfo(data: String) {
            itemView.setOnClickListener { itemClickListener?.onItemClick(data) }

            val slashIndex = data.lastIndexOf("/")
            if (slashIndex != -1) {
                binding.path = "..${data.substring(slashIndex)}"
            } else {
                binding.path = if (data.isEmpty()) " ./ " else "/$data"
            }
        }
    }
}