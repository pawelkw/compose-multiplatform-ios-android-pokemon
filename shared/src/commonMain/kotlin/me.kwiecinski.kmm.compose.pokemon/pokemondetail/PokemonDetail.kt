package me.kwiecinski.kmm.compose.pokemon.pokemondetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import me.kwiecinski.kmm.compose.pokemon.pokemonlist.PokemonViewModel
import org.koin.compose.koinInject

@Composable
fun PokemonDetail(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel = koinInject(),
    pokemonId: Int
) {
    val state by viewModel.state.collectAsState()

    remember(pokemonId) {
        viewModel.handleIntent(PokemonDetailIntent.Initial(pokemonId))
    }

    Text(state.name)
}