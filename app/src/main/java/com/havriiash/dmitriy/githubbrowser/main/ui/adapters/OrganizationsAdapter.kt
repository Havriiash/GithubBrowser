package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListOrganizationBinding
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

class OrganizationsAdapter(
        private val orgs: List<Organization>,
        private val clickListener: ItemClickListener<Organization>?
): RecyclerView.Adapter<OrganizationsAdapter.OrganizationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizationViewHolder {
        val binding: ItemListOrganizationBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_organization, parent, false)
        return OrganizationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrganizationViewHolder, position: Int) {
        holder.setInfo(orgs[position])
    }

    override fun getItemCount(): Int = orgs.size


    inner class OrganizationViewHolder private constructor(
            itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private lateinit var binding: ItemListOrganizationBinding

        constructor(binding: ItemListOrganizationBinding): this(binding.root) {
            this.binding = binding
        }

        fun setInfo(data: Organization) {
            itemView.setOnClickListener { clickListener?.onItemClick(data) }
            binding.organization = data
        }
    }
}