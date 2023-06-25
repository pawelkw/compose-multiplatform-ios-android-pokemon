package me.kwiecinski.kmm.compose.pokemon.pokemonlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemKey
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.kwiecinski.kmm.compose.pokemon.pokemondetail.PokemonDetail
import org.koin.compose.koinInject

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel = koinInject(),
    onPokemonClicked: (pokemonId: Int) -> Unit,
) {
    val lazyColumnState = rememberLazyListState()
    val lazyPagingItems = viewModel.pagerFlow.collectAsLazyPagingItems()

    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(modifier = Modifier.fillMaxHeight(), state = lazyColumnState) {
            items(
                count = lazyPagingItems.itemCount,
                key = lazyPagingItems.itemKey { it.pokemon.url }
            ) { index ->
                val item = lazyPagingItems[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onPokemonClicked(item!!.pokemon.pokemonId())
                        }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KamelImage(
                        modifier = Modifier.size(80.dp),
                        resource = asyncPainterResource(
                            data = item!!.pokemon.spriteUrl(),
                            key = item.pokemon.url
                        ),
                        contentDescription = "pokemon image"
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 20.dp),
                        text = item.pokemon.name.capitalize(),
                        style = MaterialTheme.typography.h5,
                    )
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            modifier = Modifier.align(alignment = Alignment.CenterEnd),
                            onClick = { viewModel.onAddFavouriteClicked(item.pokemon.pokemonId())
                                lazyPagingItems.refresh()
                            }) {
                            Icon(
                                imageVector = if(item.isFavourite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "add/remove to/from favourites"
                            )
                        }
                    }
                }

            }
        }
    }
}