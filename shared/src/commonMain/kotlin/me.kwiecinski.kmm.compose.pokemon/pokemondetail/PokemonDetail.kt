package me.kwiecinski.kmm.compose.pokemon.pokemondetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
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
        modifier = modifier.fillMaxWidth(),
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
        Column(
            modifier = Modifier.padding(it)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = state.name, style = MaterialTheme.typography.headlineLarge)

            OutlinedButton(onClick = {}) {
                Text("Test")
            }
        }
    }
}