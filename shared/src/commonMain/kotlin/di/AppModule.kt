package di

import pokemonlist.PokemonRepository
import pokemonlist.PokemonViewModel
import network.httpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pokemonlist.PokemonPagingSource

fun appModule() = module {
    singleOf(::PokemonRepository) { bind<PokemonRepository>() }
    factoryOf(::PokemonViewModel)

    single<DispatchersProvider> {  DefaultDispatchersProvider() }
    single<PokemonPagingSource> {  PokemonPagingSource(get()) }

    single {
        httpClient(get<DispatchersProvider>().io)
    }
}
