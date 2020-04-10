package by.dz.kotlincoroutines.network

import by.dz.kotlincoroutines.models.Pokemon
import by.dz.kotlincoroutines.models.PokemonForm
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): Pokemon

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Long): Pokemon

    @GET("pokemon-form/{id}")
    suspend fun getPokemonFormById(@Path("id") id: Long): PokemonForm
}