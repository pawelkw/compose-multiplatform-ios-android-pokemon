package me.kwiecinski.kmm.compose.pokemon.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetails(
    val id: Int,
    val name: String,
)
