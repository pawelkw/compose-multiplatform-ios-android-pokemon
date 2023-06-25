package me.kwiecinski.kmm.compose.pokemon.store

import android.content.Context

fun fileStoragePathTo(context: Context, filename: String): String = "${context.filesDir.path}/$filename.store"