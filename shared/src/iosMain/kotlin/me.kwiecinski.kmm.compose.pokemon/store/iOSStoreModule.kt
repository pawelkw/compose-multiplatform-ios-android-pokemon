package me.kwiecinski.kmm.compose.pokemon.store

import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal actual val platformStoreModule: Module = module {
    single(named(StoreModule.STORE_FAV_POKEMON)) { fileStoragePathTo(StoreModule.STORE_FAV_POKEMON) }
}