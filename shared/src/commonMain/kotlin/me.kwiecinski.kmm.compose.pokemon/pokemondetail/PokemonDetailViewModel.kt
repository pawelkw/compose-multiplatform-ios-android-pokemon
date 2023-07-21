package me.kwiecinski.kmm.compose.pokemon.pokemondetail

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
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
            _state.update { it.copy(name = pokemon.name.capitalize(Locale.current),
                imageUrl = HIGH_RES_IMAGE_TEMPLATE_URL.replace("[POKEMON_ID]", pokemon.id.toString().padStart(3, '0'))) }
        }
    }

    companion object {
        const val HIGH_RES_IMAGE_TEMPLATE_URL = "https://github.com/HybridShivam/Pokemon/blob/master/assets/imagesHQ/[POKEMON_ID].png?raw=true"
    }
}
