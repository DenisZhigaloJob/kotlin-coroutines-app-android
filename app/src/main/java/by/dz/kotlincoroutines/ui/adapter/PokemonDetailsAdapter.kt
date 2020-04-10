package by.dz.kotlincoroutines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import by.dz.kotlincoroutines.R
import by.dz.kotlincoroutines.databinding.ItemPokemonDescriptionBinding
import by.dz.kotlincoroutines.databinding.ItemPokemonFormBinding
import by.dz.kotlincoroutines.databinding.ItemPokemonImageBinding
import by.dz.kotlincoroutines.models.Pokemon
import by.dz.kotlincoroutines.models.PokemonForm
import by.dz.kotlincoroutines.util.load

class PokemonDetailsAdapter(private var pokemon: Pokemon) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var pokemonForm: PokemonForm? = null

    companion object {
        private const val ITEM_IMAGE = R.layout.item_pokemon_image
        private const val ITEM_DETAILS = R.layout.item_pokemon_description
        private const val ITEM_FORM = R.layout.item_pokemon_form
    }

    override fun getItemCount() = pokemon.getSprites().size + if (pokemonForm == null) 1 else 2

    override fun getItemViewType(position: Int) =
        if (position < pokemon.getSprites().size) ITEM_IMAGE else
            if (position == itemCount - 1 && pokemonForm != null) ITEM_FORM else ITEM_DETAILS

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        onCreateViewHolder(getBinding(LayoutInflater.from(parent.context), parent, viewType))

    private fun getBinding(li: LayoutInflater, parent: ViewGroup, viewType: Int) = when (viewType) {
        ITEM_IMAGE -> ItemPokemonImageBinding.inflate(li, parent, false)
        ITEM_FORM -> ItemPokemonFormBinding.inflate(li, parent, false)
        else -> ItemPokemonDescriptionBinding.inflate(li, parent, false)
    }

    private fun onCreateViewHolder(dataBinding: ViewDataBinding) = when (dataBinding) {
        is ItemPokemonImageBinding -> PokemonViewHolder.PokemonImageViewHolder(dataBinding)
        is ItemPokemonFormBinding -> PokemonViewHolder.PokemonFormViewHolder(dataBinding)
        else -> PokemonViewHolder.PokemonDescriptionViewHolder(dataBinding as ItemPokemonDescriptionBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PokemonViewHolder.PokemonImageViewHolder) {
            holder.bind(pokemon.getSprites()[position])
            return
        }
        if (holder is PokemonViewHolder.PokemonFormViewHolder) {
            pokemonForm?.also {
                holder.bind(it)
            }
            return
        }
        if (holder is PokemonViewHolder.PokemonDescriptionViewHolder) {
            holder.bind(pokemon)
            return
        }
    }

    sealed class PokemonViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        class PokemonImageViewHolder(private val binding: ItemPokemonImageBinding) :
            PokemonViewHolder(binding) {

            fun bind(item: String) {
                binding.imageView.load(item)
                binding.executePendingBindings()
            }

        }

        class PokemonDescriptionViewHolder(private val binding: ItemPokemonDescriptionBinding) :
            PokemonViewHolder(binding) {

            fun bind(item: Pokemon) {
                binding.pokemon = item
                binding.executePendingBindings()
            }

        }

        class PokemonFormViewHolder(private val binding: ItemPokemonFormBinding) :
            PokemonViewHolder(binding) {

            fun bind(item: PokemonForm) {
                binding.form = item
                binding.executePendingBindings()
            }

        }

    }

}