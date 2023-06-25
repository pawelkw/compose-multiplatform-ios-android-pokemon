package me.kwiecinski.kmm.compose.pokemon.pokemonlist

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import app.cash.paging.map
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import me.kwiecinski.kmm.compose.pokemon.model.Pokemon
import me.kwiecinski.kmm.compose.pokemon.navigation.ViewModel
import me.kwiecinski.kmm.compose.pokemon.store.FavouritePokemon

class PokemonViewModel(
    private val pokemonPagingSource: PokemonPagingSource,
    private val favouritePokemonStore: KStore<FavouritePokemon>,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + coroutineContext)
    private val triggerLocalRefreshFlow = MutableStateFlow<Boolean>(false)

    init {
        viewModelScope.launch {
            favouritePokemonStore.get()
        }
    }

    fun onAddFavouriteClicked(pokemonId: Int) {
        viewModelScope.launch {
            favouritePokemonStore.update {
                if (it!!.favouritePokemonIds.contains(pokemonId)) {
                    it.copy(favouritePokemonIds = it.favouritePokemonIds.toMutableSet() - pokemonId)
                } else {
                    it.copy(favouritePokemonIds = it.favouritePokemonIds.toMutableSet() + pokemonId)
                }
            }
            triggerLocalRefreshFlow.tryEmit(true)
        }
    }

    val pagerFlow: Flow<PagingData<PokemonItemData>> = run {
        Pager<Int, Pokemon>(PagingConfig(pageSize = 20, initialLoadSize = 20)) {
            pokemonPagingSource

        }.flow
            .map { pagingData ->
                val favPokemonIds = favouritePokemonStore.get()!!.favouritePokemonIds
                pagingData.map { pokemon ->
                    PokemonItemData(pokemon, favPokemonIds.contains(pokemon.pokemonId()))
                }
            }
    }
}