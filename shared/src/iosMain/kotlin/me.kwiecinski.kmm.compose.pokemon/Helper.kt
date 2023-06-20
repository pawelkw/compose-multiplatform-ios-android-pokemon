package me.kwiecinski.kmm.compose.pokemon

import me.kwiecinski.kmm.compose.pokemon.di.appModule
import org.koin.core.context.startKoin

fun initKoiniOS(){
    startKoin {
        modules(appModule())
    }
}