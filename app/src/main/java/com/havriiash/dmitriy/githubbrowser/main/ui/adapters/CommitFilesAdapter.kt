package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListCommitFileBinding
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

class CommitFilesAdapter(
        private val files: List<Commit.File>,
        private val itemClickListener: ItemClickListener<Commit.File>?
) : RecyclerView.Adapter<CommitFilesAdapter.CommitItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitItemViewHolder {
        val binding: ItemListCommitFileBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_commit_file, parent, false)
        return CommitFilesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommitItemViewHolder, position: Int) {
        holder.setInfo(files[position], position)
    }

    override fun getItemCount(): Int = files.size

//    override fun getItemViewType(position: Int): Int {
//        return if (sortedFilesList[position] == null) {
//            ITEM_HEADER
//        } else {
//            ITEM_DATA
//        }
//    }

//    private fun getDir(fileName: String): String? {
//        val slashIndex = fileName.lastIndexOf("/")
//        return if (slashIndex != -1) {
//            fileName.substring(0, slashIndex)
//        } else {
//            null
//        }
//    }


    abstract class CommitItemViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        abstract fun setInfo(data: Commit.File, position: Int)
    }

    inner class CommitFilesViewHolder private constructor(
            itemView: View
    ) : CommitItemViewHolder(itemView) {

        private lateinit var binding: ItemListCommitFileBinding

        constructor(binding: ItemListCommitFileBinding) : this(binding.root) {
            this.binding = binding
        }

        override fun setInfo(data: Commit.File, position: Int) {
            itemView.setOnClickListener { itemClickListener?.onItemClick(data) }
            binding.file = data
        }
    }

//    inner class CommitFilesHeaderViewHolder private constructor(
//            itemView: View
//    ) : CommitItemViewHolder(itemView) {
//
//        private lateinit var binding: ItemListHeaderBinding
//
//        constructor(binding: ItemListHeaderBinding) : this(binding.root) {
//            this.binding = binding
//        }
//
//        override fun setInfo(data: Commit.File?, position: Int) {
//            if (data == null) {
//                val slashIndex = files[position].fileName.lastIndexOf("/")
//                if (slashIndex != -1) {
//                    binding.title = files[position].fileName.substring(0, slashIndex)
//                } else {
//                    binding.title = files[position].fileName
//                }
//            }
//        }
//    }
}