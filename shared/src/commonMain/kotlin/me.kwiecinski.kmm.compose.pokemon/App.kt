package me.kwiecinski.kmm.compose.pokemon

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.DefaultComponentContext
import me.kwiecinski.kmm.compose.pokemon.home.HomeScreen
import me.kwiecinski.kmm.compose.pokemon.pokemonlist.PokemonList

@Composable
fun App() {
    MaterialTheme {
        HomeScreen()
    }
}