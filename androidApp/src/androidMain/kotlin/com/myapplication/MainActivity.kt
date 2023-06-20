package com.myapplication

import me.kwiecinski.kmm.compose.pokemon.MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import me.kwiecinski.kmm.compose.pokemon.di.initKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin()
        setContent {
            MainView()
        }
    }
}