package me.kwiecinski.kmm.compose.pokemon.model

import kotlinx.serialization.Serializable

@Serializable
data class AllPokemon(
    val results: List<Pokemon>
)
