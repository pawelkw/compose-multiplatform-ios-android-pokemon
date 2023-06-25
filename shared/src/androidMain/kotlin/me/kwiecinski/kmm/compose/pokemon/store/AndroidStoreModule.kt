package me.kwiecinski.kmm.compose.pokemon.store

import me.kwiecinski.kmm.compose.pokemon.store.StoreModule.Companion.STORE_FAV_POKEMON
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal actual val platformStoreModule: Module = module {
    single(named(STORE_FAV_POKEMON)) { fileStoragePathTo(get(), STORE_FAV_POKEMON) }
}