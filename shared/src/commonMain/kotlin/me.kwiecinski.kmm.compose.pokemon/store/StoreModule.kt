package me.kwiecinski.kmm.compose.pokemon.store

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun commonStoreModule() = module {
    single<KStore<FavouritePokemon>> {
        storeOf(get(named("fav_pokemon")), FavouritePokemon())
    }
}


val storeModule : Module
    get() =  module {
    includes(commonStoreModule() + platformStoreModule)
}

internal expect val platformStoreModule:Module

internal interface StoreModule {
    companion object {
        const val STORE_FAV_POKEMON = "fav_pokemon"
    }
}