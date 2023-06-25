package me.kwiecinski.kmm.compose.pokemon.store

import platform.Foundation.NSHomeDirectory

fun fileStoragePathTo(filename: String): String = "${NSHomeDirectory()}/$filename.store"