package me.kwiecinski.kmm.compose.pokemon.pokemondetail

sealed class PokemonDetailIntent {
    data class Initial(val pokemonId: Int) : PokemonDetailIntent()

}
