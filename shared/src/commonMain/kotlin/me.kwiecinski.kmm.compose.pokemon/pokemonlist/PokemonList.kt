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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadState
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemKey
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.kwiecinski.kmm.compose.pokemon.pokemondetail.PokemonDetail
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel = koinInject(),
    onPokemonClicked: (pokemonId: Int) -> Unit,
) {
    val lazyColumnState = rememberLazyListState()
    val lazyPagingItems = viewModel.pagerFlow.collectAsLazyPagingItems()
    var refreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(refreshing, {
        lazyPagingItems.refresh()
    })

    Scaffold(modifier.fillMaxWidth()) {
        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(modifier = Modifier.fillMaxHeight(), state = lazyColumnState) {
                when (lazyPagingItems.loadState.refresh) {
                    is LoadStateLoading -> { refreshing = true }
                    else -> { refreshing = false }
                }
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
                                data = item!!.pokemon.spriteUrl()
                            ),
                            contentDescription = "pokemon image"
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 20.dp),
                            text = item.pokemon.name.capitalize(),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            IconButton(
                                modifier = Modifier.align(alignment = Alignment.CenterEnd),
                                onClick = {
                                    viewModel.onAddFavouriteClicked(item.pokemon.pokemonId())
                                    lazyPagingItems.refresh()
                                }) {
                                Icon(
                                    imageVector = if (item.isFavourite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = "add/remove to/from favourites"
                                )
                            }
                        }
                    }

                }
            }
            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))

        }
    }
}