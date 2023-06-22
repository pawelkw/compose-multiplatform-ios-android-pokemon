package me.kwiecinski.kmm.compose.pokemon.pokemondetail

data class PokemonDetailState(
    val name: String
) {
    companion object {
        val DEFAULT_STATE = PokemonDetailState(
            name = ""
        )
    }
}
