package me.kwiecinski.kmm.compose.pokemon.pokemonlist

import me.kwiecinski.kmm.compose.pokemon.model.Pokemon

data class PokemonItemData(
    val pokemon: Pokemon,
    val isFavourite: Boolean
)