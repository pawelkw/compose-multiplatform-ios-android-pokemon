package network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json

internal actual fun httpClient(ioDispatcher: CoroutineDispatcher): HttpClient {

    return HttpClient(Darwin) {
        engine {
            configureRequest {
                setTimeoutInterval(60.0)
                setAllowsCellularAccess(true)
            }
        }
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
            })
        }
    }
}