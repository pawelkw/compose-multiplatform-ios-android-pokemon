package me.kwiecinski.kmm.compose.pokemon.store

import kotlinx.serialization.Serializable

@Serializable
data class FavouritePokemon(
    val favouritePokemonIds: Set<Int> = emptySet(),
)