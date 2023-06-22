package me.kwiecinski.kmm.compose.pokemon.pokemondetail

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.kwiecinski.kmm.compose.pokemon.di.DispatchersProvider
import me.kwiecinski.kmm.compose.pokemon.model.PokemonDetails
import me.kwiecinski.kmm.compose.pokemon.navigation.ViewModel
import me.kwiecinski.kmm.compose.pokemon.pokemonlist.PokemonRepository

class PokemonDetailViewModel(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + coroutineContext)

    private val _state = MutableStateFlow(PokemonDetailState.DEFAULT_STATE)
    val state: StateFlow<PokemonDetailState> =
        _state.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PokemonDetailState.DEFAULT_STATE
        )

    fun handleIntent(intent: PokemonDetailIntent) {
        when (intent) {
            is PokemonDetailIntent.Initial -> fetchPokemon(intent.pokemonId)
        }
    }

    private fun fetchPokemon(pokemonId: Int) {
        viewModelScope.launch {

            // Example of web request
            val pokemon: PokemonDetails = pokemonRepository.getPokemon(pokemonId)
            _state.update { it.copy(name = pokemon.name) }
        }
    }
}
