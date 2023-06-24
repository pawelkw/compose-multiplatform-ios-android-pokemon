package me.kwiecinski.kmm.compose.pokemon.pokemondetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun PokemonDetail(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel = koinInject(),
    pokemonId: Int,
    onBackClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    remember(pokemonId) {
        viewModel.handleIntent(PokemonDetailIntent.Initial(pokemonId))
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pokemon Details", textAlign = TextAlign.Center)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(text = state.name, style = MaterialTheme.typography.h4)
        }
    }
}