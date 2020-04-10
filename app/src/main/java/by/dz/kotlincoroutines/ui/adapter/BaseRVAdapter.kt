package by.dz.kotlincoroutines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRVAdapter<T : Any, VH : BaseViewHolder<T>, DB : ViewDataBinding>(
    private var items: MutableList<T>, var listener: OnItemClickListener?
) : RecyclerView.Adapter<VH>() {

    constructor(items: MutableList<T>) : this(items, object :
        OnItemClickListener {
        override fun onItemClick(position: Int) {
            //Default listener
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        onCreateViewHolder(getBinding(LayoutInflater.from(parent.context), parent, viewType))

    abstract fun getBinding(li: LayoutInflater, parent: ViewGroup, viewType: Int): DB

    abstract fun onCreateViewHolder(dataBinding: DB): VH

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position), listener)

    override fun getItemId(i: Int): Long = i.toLong()

    open fun getItem(position: Int): T = items[position]

    override fun getItemCount(): Int = if (items.isEmpty()) 0 else items.size

    open fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    fun setItems(items: MutableList<T>) {
        this.items.clear()
        this.items.addAll(items)
    }

    fun addItems(position: Int? = 0, items: MutableList<T>) {
        this.items.addAll(position ?: 0, items)
    }

    fun addItem(position: Int? = 0, item: T) {
        this.items.add(position ?: 0, item)
    }

    fun setItem(position: Int, obj: T) {
        this.items[position] = obj
    }

    fun getData(): MutableList<T> = items

    open fun replaceData(arrayList: MutableList<T>) {
        setItems(arrayList)
        notifyDataSetChanged()
    }

}