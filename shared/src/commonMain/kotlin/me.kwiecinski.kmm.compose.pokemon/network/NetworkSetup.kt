package me.kwiecinski.kmm.compose.pokemon.network

import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineDispatcher

internal expect fun httpClient(ioDispatcher: CoroutineDispatcher): HttpClient
