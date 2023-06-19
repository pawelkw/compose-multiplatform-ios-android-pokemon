package model

import kotlinx.serialization.Serializable

@Serializable
data class AllPokemon(
    val results: List<Pokemon>
)
