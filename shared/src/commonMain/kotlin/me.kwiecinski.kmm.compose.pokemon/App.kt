package me.kwiecinski.kmm.compose.pokemon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.DefaultComponentContext
import com.moriatsushi.insetsx.safeDrawing
import me.kwiecinski.kmm.compose.pokemon.home.HomeScreen
import me.kwiecinski.kmm.compose.pokemon.pokemonlist.PokemonList

@Composable
fun App() {
    MaterialTheme {
        Box(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {
            HomeScreen()
        }
    }
}