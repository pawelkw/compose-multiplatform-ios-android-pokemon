package model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val url: String,
    val name: String,
) {
    fun spriteUrl(): String {
        val pokemonNumber = url.split("/").last { it.isNotBlank() }
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonNumber}.png"
    }
}
