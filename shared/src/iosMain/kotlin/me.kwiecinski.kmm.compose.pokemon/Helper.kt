package me.kwiecinski.kmm.compose.pokemon

import me.kwiecinski.kmm.compose.pokemon.di.appModule
import me.kwiecinski.kmm.compose.pokemon.store.commonStoreModule
import me.kwiecinski.kmm.compose.pokemon.store.storeModule
import org.koin.core.context.startKoin

fun initKoiniOS(){
    startKoin {
        modules(appModule(), storeModule)
    }
}