package com.myapplication

import android.app.Application
import me.kwiecinski.kmm.compose.pokemon.di.appModule
import me.kwiecinski.kmm.compose.pokemon.store.storeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokemonApplication)
            modules(appModule(), storeModule)
        }
    }
}