package by.dz.kotlincoroutines

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.dz.kotlincoroutines.models.Pokemon
import by.dz.kotlincoroutines.network.NetworkService
import by.dz.kotlincoroutines.ui.adapter.PokemonDetailsAdapter
import by.dz.kotlincoroutines.ui.layoutmanager.PokemonDetailsGridLayoutManager
import by.dz.kotlincoroutines.util.getJsonExtra
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val PARAM_POKEMON_MODEL = "pokemon"
    }

    private var adapter: PokemonDetailsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        intent.getJsonExtra(PARAM_POKEMON_MODEL, Pokemon::class.java)?.let {
            pokemonRecyclerView.layoutManager =
                PokemonDetailsGridLayoutManager(this, 2, it.getSprites().size)
            adapter = PokemonDetailsAdapter(it)
            pokemonRecyclerView.adapter = adapter
            pokemonRecyclerView.adapter?.notifyDataSetChanged()
            loadColor(it.id)
        }
    }

    private fun loadColor(pokemonId: Long) {
        lifecycleScope.launch(CoroutineExceptionHandler { _, th ->
            Toast.makeText(this, th.message, Toast.LENGTH_LONG).show()
        }) {
            val model = withContext(Dispatchers.Default) {
                NetworkService.retrofitService().getPokemonFormById(pokemonId)
            }
            adapter?.pokemonForm = model
            adapter?.notifyDataSetChanged()
        }
    }
}