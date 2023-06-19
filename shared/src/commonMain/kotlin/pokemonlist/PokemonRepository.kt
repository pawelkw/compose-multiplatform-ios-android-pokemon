package pokemonlist

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import model.Pokemon
import model.PokemonDetails

class PokemonRepository(private val httpClient: HttpClient) {
    suspend fun getPokemon(): PokemonDetails {
        return httpClient.get("https://pokeapi.co/api/v2/pokemon/ditto").body()
    }
}