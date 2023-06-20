package pokemonlist

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import di.DispatchersProvider
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Pokemon
import model.PokemonDetails

class PokemonViewModel(private val pokemonRepository: PokemonRepository,
                       private val pokemonPagingSource: PokemonPagingSource,
                       dispatchersProvider: DispatchersProvider,) {


    val pager: Pager<Int, Pokemon> = run {
        val pagingConfig = PagingConfig(pageSize = 20, initialLoadSize = 20)
        Pager(pagingConfig) {
            pokemonPagingSource
        }
    }

    private val viewModelScope = CoroutineScope(SupervisorJob() + dispatchersProvider.main)

    private val _pokemonName = MutableStateFlow("")
    val pokemonName: StateFlow<String> = _pokemonName.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )
    init {
        viewModelScope.launch {

            // Example of web request
            val pokemon: PokemonDetails = pokemonRepository.getPokemon()
            _pokemonName.update { pokemon.name }
        }
    }
}