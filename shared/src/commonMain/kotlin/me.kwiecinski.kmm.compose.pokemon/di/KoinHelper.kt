package me.kwiecinski.kmm.compose.pokemon.di

import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}