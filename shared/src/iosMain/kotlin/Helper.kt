import di.appModule
import org.koin.core.context.startKoin

fun initKoiniOS(){
    startKoin {
        modules(appModule())
    }
}