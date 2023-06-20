package me.kwiecinski.kmm.compose.pokemon.pokemonlist

import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import me.kwiecinski.kmm.compose.pokemon.model.AllPokemon
import me.kwiecinski.kmm.compose.pokemon.model.Pokemon

class PokemonPagingSource(
    private val httpClient: HttpClient
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, Pokemon> {
        val page = params.key ?: FIRST_PAGE_INDEX
        println("veyndan___ $page")
        val httpResponse = httpClient.get("https://pokeapi.co/api/v2/pokemon") {
            url {
                parameters.append("offset", (params.loadSize * page).toString())
                parameters.append("limit", params.loadSize.toString())
            }
        }
        return when {
            httpResponse.status.isSuccess() -> {
                val allPokemon = httpResponse.body<AllPokemon>()
                PagingSourceLoadResultPage(
                    data = allPokemon.results,
                    prevKey = (page - 1).takeIf { it >= FIRST_PAGE_INDEX },
                    nextKey = if (allPokemon.results.isNotEmpty()) page + 1 else null,
                ) as PagingSourceLoadResult<Int, Pokemon>
            }
            httpResponse.status == HttpStatusCode.Forbidden -> {
                PagingSourceLoadResultError<Int, Pokemon>(
                    Exception("Whoops! You just exceeded the rate limit."),
                ) as PagingSourceLoadResult<Int, Pokemon>
            }
            else -> {
                PagingSourceLoadResultError<Int, Pokemon>(
                    Exception("Received a ${httpResponse.status}."),
                ) as PagingSourceLoadResult<Int, Pokemon>
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? = null

    companion object {
        const val FIRST_PAGE_INDEX = 1
    }
}