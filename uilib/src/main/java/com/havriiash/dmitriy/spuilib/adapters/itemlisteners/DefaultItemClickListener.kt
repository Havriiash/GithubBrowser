package com.havriiash.dmitriy.spuilib.adapters.itemlisteners

class DefaultItemClickListener<D>(
        private val handler: (data:D) -> Unit
) : ItemClickListener<D> {
    override fun onItemClick(data: D) {
        handler.invoke(data)
    }
}

