package by.dz.kotlincoroutines.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : Any>(binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    protected open fun setItemClickListener(listener: OnItemClickListener?) {
        if (listener != null) {
            itemView.setOnClickListener { listener.onItemClick(layoutPosition) }
        }
    }

    fun getContext() = itemView.context

    abstract fun bind(item: T, listener: OnItemClickListener?)

}