package by.dz.kotlincoroutines

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.dz.kotlincoroutines.models.Pokemon
import by.dz.kotlincoroutines.network.NetworkService
import by.dz.kotlincoroutines.ui.adapter.OnItemClickListener
import by.dz.kotlincoroutines.ui.adapter.PokemonListAdapter
import by.dz.kotlincoroutines.util.putExtraJson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private var adapter = PokemonListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonRecyclerView.layoutManager = GridLayoutManager(this, 2)
        pokemonRecyclerView.adapter = adapter
        loadTop()
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { doSearch(it) }
                return true
            }

            override fun onQueryTextChange(newText: String) = false

        })
    }

    private fun loadTop() {
        lifecycleScope.launch(CoroutineExceptionHandler { _, tr ->
            tr.message?.let { showError(it) }
        }) {
            val deferred = mutableListOf<Deferred<Pokemon>>()
            val ids = withContext(Dispatchers.Default) { getIds() }
            ids.forEach { id ->
                deferred.add(async(Dispatchers.Default + CoroutineExceptionHandler { _, tr ->
                    tr.message?.let { showError(it) }
                }) {
                    NetworkService.retrofitService().getPokemonById(id)
                })
            }
            onLoadTopList(
                deferred[0].await(), deferred[1].await(), deferred[2].await(),
                deferred[3].await(), deferred[4].await()
            )
        }
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    private fun onLoadTopList(vararg newList: Pokemon) {
        adapter.notifyChanges(newList.toMutableList())
    }

    private fun getIds(): Set<Long> {
        val ids = mutableSetOf<Long>()
        while (ids.size < 5) {
            ids += (1..807L).shuffled().first()
        }
        return ids
    }

    private fun doSearch(name: String) {
        lifecycleScope.launch(CoroutineExceptionHandler { _, _ ->
            hideLoading()
            Toast.makeText(this, R.string.error_no_pokemon, Toast.LENGTH_LONG).show()
        }) {
            showLoading()
            val model = withContext(Dispatchers.Default) {
                if (name.toLongOrNull() == null)
                    NetworkService.retrofitService().getPokemonByName(name)
                else
                    NetworkService.retrofitService().getPokemonById(name.toLong())
            }
            hideLoading()
            openPokemonDetailsActivity(model)
        }
    }

    private fun showLoading() {
        loadingContainer.isVisible = true
    }

    private fun hideLoading() {
        loadingContainer.isVisible = false
    }

    override fun onItemClick(position: Int) {
        openPokemonDetailsActivity(adapter.getItem(position))
    }

    private fun openPokemonDetailsActivity(pokemon: Pokemon) {
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtraJson(DetailsActivity.PARAM_POKEMON_MODEL, pokemon)
        })
    }
}
