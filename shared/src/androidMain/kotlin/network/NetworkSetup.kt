package network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json

internal actual fun httpClient(ioDispatcher: CoroutineDispatcher): HttpClient {
    return HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
            })
        }
    }
}

