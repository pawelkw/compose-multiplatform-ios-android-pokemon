package me.kwiecinski.kmm.compose.pokemon.pokemondetail

data class PokemonDetailState(
    val name: String,
    val imageUrl: String,
) {
    companion object {
        val DEFAULT_STATE = PokemonDetailState(
            name = "",
            imageUrl = ""
        )
    }
}
