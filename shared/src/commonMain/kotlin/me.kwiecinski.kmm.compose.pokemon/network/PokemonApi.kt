package me.kwiecinski.kmm.compose.pokemon.network

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import me.kwiecinski.kmm.compose.pokemon.model.PokemonDetails

interface PokemonApi {
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonDetails
}