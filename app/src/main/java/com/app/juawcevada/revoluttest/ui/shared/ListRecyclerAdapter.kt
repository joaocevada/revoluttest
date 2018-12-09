package com.app.juawcevada.revoluttest.ui.shared

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView


/**
 * PagedList Recycler view to be used with data bound views.
 */


abstract class ListRecyclerAdapter<DataType, BindingType : ViewDataBinding>
    : RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder<BindingType>>() {

    abstract val differ: AsyncListDiffer<DataType>

    fun submitList(list: List<DataType>?) {
        differ.submitList(list)
    }

    fun getItem(position: Int): DataType = differ.currentList[position]

    override fun getItemCount(): Int = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<BindingType> {
        val binding = createBinding(parent)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<BindingType>, position: Int) {
        getItem(position)?.let {
            bind(holder.binding, it, position)
            holder.binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder<BindingType>, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    protected abstract fun bind(binding: BindingType, item: DataType, position: Int)

    protected abstract fun createBinding(parent: ViewGroup): BindingType

    class ViewHolder<out DataType : ViewDataBinding>(
            val binding: DataType
    ) : RecyclerView.ViewHolder(binding.root)
}
