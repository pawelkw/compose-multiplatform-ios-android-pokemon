package me.kwiecinski.kmm.compose.pokemon

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.moriatsushi.insetsx.WindowInsetsUIViewController
import io.github.xxfast.decompose.LocalComponentContext
import platform.Foundation.NSHomeDirectory
import platform.UIKit.UIViewController

fun Main(): UIViewController = WindowInsetsUIViewController {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
//    appStorage = NSHomeDirectory()

    /**
     * TODO: Maybe we can use [LocalUIViewController], but there's no real way to hook into [ComposeWindow.viewDidLoad]
     * */

    /**
     * TODO: Maybe we can use [LocalUIViewController], but there's no real way to hook into [ComposeWindow.viewDidLoad]
     * */
    BoxWithConstraints {
        CompositionLocalProvider(
            LocalComponentContext provides rootComponentContext,
        ) {
            App()
        }
    }
}
