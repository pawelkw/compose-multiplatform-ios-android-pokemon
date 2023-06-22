package me.kwiecinski.kmm.compose.pokemon.di

import me.kwiecinski.kmm.compose.pokemon.pokemonlist.PokemonRepository
import me.kwiecinski.kmm.compose.pokemon.pokemonlist.PokemonViewModel
import me.kwiecinski.kmm.compose.pokemon.pokemondetail.PokemonDetailViewModel
import me.kwiecinski.kmm.compose.pokemon.network.httpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import me.kwiecinski.kmm.compose.pokemon.pokemonlist.PokemonPagingSource

fun appModule() = module {

    singleOf(::PokemonRepository) { bind<PokemonRepository>() }
    factoryOf(::PokemonViewModel)
    factoryOf(::PokemonDetailViewModel)

    single<DispatchersProvider> {  DefaultDispatchersProvider() }
    single<PokemonPagingSource> {  PokemonPagingSource(get()) }


    single {
        httpClient(get<DispatchersProvider>().io)
    }
}
