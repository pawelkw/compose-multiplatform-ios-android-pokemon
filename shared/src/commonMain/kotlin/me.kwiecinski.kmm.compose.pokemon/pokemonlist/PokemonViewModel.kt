package me.kwiecinski.kmm.compose.pokemon.pokemonlist

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import me.kwiecinski.kmm.compose.pokemon.model.Pokemon

class PokemonViewModel(
    private val pokemonPagingSource: PokemonPagingSource,
) {


    val pager: Pager<Int, Pokemon> = run {
        val pagingConfig = PagingConfig(pageSize = 20, initialLoadSize = 20)
        Pager(pagingConfig) {
            pokemonPagingSource
        }
    }
}