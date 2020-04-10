package by.dz.kotlincoroutines.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import by.dz.kotlincoroutines.R

object PokemonDetailsBindingAdapter {
    @JvmStatic
    @BindingAdapter("app:pokemonName")
    fun setPokemonName(view: TextView, value: String?) {
        view.text = view.context.getString(R.string.pokemon_name, value.unbox().capitalize())
    }

    @JvmStatic
    @BindingAdapter("app:pokemonOrder")
    fun setPokemonOrder(view: TextView, value: Int?) {
        view.text = view.context.getString(R.string.pokemon_order, value.unbox().toString())
    }

    @JvmStatic
    @BindingAdapter("app:pokemonWeight")
    fun setPokemonWeight(view: TextView, value: Int?) {
        view.text = view.context.getString(R.string.pokemon_weight, value.unbox().toString())
    }

    @JvmStatic
    @BindingAdapter("app:pokemonHeight")
    fun setPokemonHeight(view: TextView, value: Int?) {
        view.text = view.context.getString(R.string.pokemon_height, value.unbox().toString())
    }

    @JvmStatic
    @BindingAdapter("app:pokemonType")
    fun setPokemonType(view: TextView, value: List<String>?) {
        var types = ""
        value.unbox().forEach { types += "${it.capitalize()} " }
        view.text = view.context.getString(R.string.pokemon_type, types)
    }

    @JvmStatic
    @BindingAdapter("app:pokemonForm")
    fun setPokemonForm(view: TextView, value: String?) {
        view.text = view.context.getString(R.string.pokemon_form, value.unbox())
    }

}