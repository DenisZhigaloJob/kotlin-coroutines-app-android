package by.dz.kotlincoroutines.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import by.dz.kotlincoroutines.models.Pokemon

class PokemonListDiffUtilCallback(
    private val oldList: List<Pokemon>, private val newList: List<Pokemon>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.name == newItem.name &&
                oldItem.getImage() == newItem.getImage()
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

}