package by.dz.kotlincoroutines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import by.dz.kotlincoroutines.databinding.ItemPokemonBinding
import by.dz.kotlincoroutines.models.Pokemon
import by.dz.kotlincoroutines.ui.adapter.PokemonListAdapter.PokemonViewHolder
import by.dz.kotlincoroutines.util.load

class PokemonListAdapter(listener: OnItemClickListener) :
    BaseRVAdapter<Pokemon, PokemonViewHolder, ItemPokemonBinding>(arrayListOf(), listener) {

    override fun getBinding(li: LayoutInflater, parent: ViewGroup, viewType: Int) =
        ItemPokemonBinding.inflate(li, parent, false)

    override fun onCreateViewHolder(dataBinding: ItemPokemonBinding) =
        PokemonViewHolder(dataBinding)

    class PokemonViewHolder(var binding: ItemPokemonBinding) : BaseViewHolder<Pokemon>(binding) {

        override fun bind(item: Pokemon, listener: OnItemClickListener?) {
            binding.pokemon = item
            binding.imageView.load(item.getImage())
            binding.nameTextView.text = item.name.capitalize()
            setItemClickListener(listener)
            binding.executePendingBindings()
        }
    }

    fun notifyChanges(newList: MutableList<Pokemon>) {
        val diff = DiffUtil.calculateDiff(PokemonListDiffUtilCallback(getData(), newList))
        setItems(newList)
        diff.dispatchUpdatesTo(this)
    }
}