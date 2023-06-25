package me.kwiecinski.kmm.compose.pokemon.pokemonlist

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.kwiecinski.kmm.compose.pokemon.model.Pokemon
import me.kwiecinski.kmm.compose.pokemon.navigation.ViewModel
import me.kwiecinski.kmm.compose.pokemon.store.FavouritePokemon

class PokemonViewModel(
    private val pokemonPagingSource: PokemonPagingSource,
    private val favouritePokemonStore: KStore<FavouritePokemon>
) : ViewModel()  {

    private val viewModelScope = CoroutineScope(SupervisorJob() + coroutineContext)

    init {
        viewModelScope.launch {
            favouritePokemonStore.get()
        }
    }

    val pager: Pager<Int, Pokemon> = run {
        val pagingConfig = PagingConfig(pageSize = 20, initialLoadSize = 20)
        Pager(pagingConfig) {
            pokemonPagingSource
        }
    }
}