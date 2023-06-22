package me.kwiecinski.kmm.compose.pokemon.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val url: String,
    val name: String,
) {
    fun spriteUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonNumber()}.png"
    }

    fun pokemonId(): Int {
        return pokemonNumber().toInt()
    }

    private fun pokemonNumber() = url.split("/").last { it.isNotBlank() }

}
